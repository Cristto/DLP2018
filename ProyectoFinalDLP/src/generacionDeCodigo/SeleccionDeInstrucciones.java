package generacionDeCodigo;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ast.Program;
import ast.definitions.DefFuncion;
import ast.definitions.DefStruct;
import ast.definitions.DefVariable;
import ast.definitions.Definition;
import ast.expressions.AccesoArray;
import ast.expressions.AccesoStruct;
import ast.expressions.Cast;
import ast.expressions.Comparacion;
import ast.expressions.ExpresionAritmetica;
import ast.expressions.ExpresionLogica;
import ast.expressions.LitChar;
import ast.expressions.LitInt;
import ast.expressions.LitReal;
import ast.expressions.LlamadaFuncion;
import ast.expressions.MenosUnario;
import ast.expressions.Not;
import ast.expressions.Variable;
import ast.statements.Asignacion;
import ast.statements.IfElse;
import ast.statements.Invocacion;
import ast.statements.Print;
import ast.statements.Read;
import ast.statements.Return;
import ast.statements.While;
import ast.types.Tipo;
import ast.types.TipoArray;
import ast.types.TipoChar;
import ast.types.TipoFuncion;
import ast.types.TipoInt;
import ast.types.TipoReal;
import ast.types.TipoStruct;
import ast.types.TipoVoid;
import generacionDeCodigo.tipos.Funcion;
import visitor.DefaultVisitor;

public class SeleccionDeInstrucciones extends DefaultVisitor {
	
	public SeleccionDeInstrucciones(Writer writer, String sourceFile) {
		this.writer = new PrintWriter(writer);
		this.sourceFile = sourceFile;

		this.instruccion = new HashMap<>();
		this.locales = new ArrayList<DefVariable>();

		this.numIf = 0;
		this.numIfElse = 0;
		this.numWhile = 0;

		instruccion.put("&&", "and");
		instruccion.put("||", "or");
		instruccion.put("!", "not");

		instruccion.put("+", "add");
		instruccion.put("-", "sub");
		instruccion.put("/", "div");
		instruccion.put("*", "mul");

		instruccion.put(">", "gt");
		instruccion.put("<", "lt");
		instruccion.put("!=", "ne");
		instruccion.put("==", "eq");
		instruccion.put(">=", "ge");
		instruccion.put("<=", "le");

	    }

	    @Override
	    public Object visit(Program node, Object param) {
		boolean main = false;
		out("#source \"" + sourceFile + "\"");
		for (Definition def : node.getDefiniciones()) {
		    if (def.getClass() == DefFuncion.class) {
			DefFuncion func = (DefFuncion) def;
			if (func.getNombre().equals("main"))
			    main = true;
		    }
		}
		if (main)
		    out("call main");

		out("halt");
		super.visit(node, Funcion.SUFIJO);

		return null;
	    }

	    @Override
	    public Object visit(DefVariable node, Object param) {
		out("#VAR " + node.getNombre() + ":" + node.getTipo().getNombreMapl());
		return null;
	    }

	    @Override
	    public Object visit(DefFuncion node, Object param) {
		this.params = new HashMap<String, DefVariable>();
		out(node.getNombre() + ":");
		out("enter " + totalLocales(node.getDefVarLocal(), param));
		locales.addAll(node.getDefVarLocal());
		for (DefVariable defVar : node.getParametros()) {
		    params.put(defVar.getNombre(), defVar);
		}
		visitChildren(node.getSentencias(), node);
		if (node.getTipo() instanceof TipoVoid)
		    out("RET 0," + totalLocales(node.getDefVarLocal(), param) + "," + totalParams(node.getParametros(), param));
		return null;
	    }

	    @Override
	    public Object visit(IfElse node, Object param) {
		int local = numIfElse++;
		out("\n#line " + node.getEnd().getLine());

		if (node.getCondicion() != null)
		    node.getCondicion().accept(this, Funcion.VALOR);

		out("jz else" + local);
		visitChildren(node.getSentenciasIf(), param);

		out("jmp fin" + local);
		out("else" + local + ":");

		visitChildren(node.getSentenciasElse(), param);
		out("fin" + local + ":");
		return null;
	    }

	    @Override
	    public Object visit(While node, Object param) {
		int local = numWhile++;
		out("bucle" + local + ":");

		if (node.getCondicion() != null)
		    node.getCondicion().accept(this, Funcion.VALOR);

		out("JZ finBucle" + local);
		visitChildren(node.getSentencias(), param);
		out("JMP bucle" + local);
		out("finBucle" + local + ":");

		return null;
	    }

	    @Override
	    public Object visit(Return node, Object param) {
		DefFuncion funcion = (DefFuncion) param;
		if (node.getExpresion() != null) {
		    node.getExpresion().accept(this, Funcion.VALOR);
		    out("RET " + node.getExpresion().getTipo().accept(new GestionDeMemoria(), Funcion.TAM) + ","
			    + totalLocales(funcion.getDefVarLocal(), param));
		} else {
		    out("RET 0, " + totalLocales(funcion.getVariables(), param) + ","
			    + totalParams(funcion.getParametros(), param));
		}
		return null;
	    }

	    @Override
	    public Object visit(AccesoStruct node, Object param) {
		if (param == Funcion.DIRECCION) {
		    if (node.getCampo() != null)
			node.getIdent().accept(this, Funcion.DIRECCION);
		    DefStruct s = (((TipoStruct) node.getIdent().getTipo()).getDefstruct());
		    int desplazamiento = 0;
		    for (CamposStruct c : s.getCampos()) {
			if (!c.getNombre().equals(node.getNombre()))
			    desplazamiento += (int) c.getTipo().accept(new GestionDeMemoria(), Funcion.TAM);
			else
			    break;
		    }
		    out("push " + desplazamiento);
		    out("add");
		} else {
		    visit(node, Funcion.DIRECCION);
		    out("load", node.getTipo());
		}
		return null;
	    }

	    @Override
	    public Object visit(Print node, Object param) {
		out("\n#line " + node.getEnd().getLine());
		node.getExpresion().accept(this, Funcion.VALOR);
		out("out", node.getExpresion().getTipo());
		return null;
	    }

	    @Override
	    public Object visit(Read node, Object param) {
		out("\n#line " + node.getEnd().getLine());
		if (node.getExpresion() != null)
		    node.getExpresion().accept(this, Funcion.DIRECCION);
		out("in" + node.getExpresion().getTipo().accept(this, Funcion.SUFIJO));
		out("store", node.getExpresion().getTipo());
		return null;
	    }

	    @Override
	    public Object visit(Asignacion node, Object param) {
		out("\n#line " + node.getEnd().getLine());
		node.getLeftExpr().accept(this, Funcion.DIRECCION);
		node.getRightExpr().accept(this, Funcion.VALOR);
		out("store", node.getLeftExpr().getTipo());
		return null;
	    }

	    @Override
	    public Object visit(ExpresionAritmetica node, Object param) {
		assert (param == Funcion.VALOR);
		node.getLeftExpr().accept(this, Funcion.VALOR);
		node.getRightExpr().accept(this, Funcion.VALOR);
		out(instruccion.get(node.getOperador()), node.getTipo());
		return null;
	    }

	    @Override
	    public Object visit(Comparacion node, Object param) {
		assert (param == Funcion.VALOR);
		node.getLeftExpr().accept(this, Funcion.VALOR);
		node.getRightExpr().accept(this, Funcion.VALOR);
		out(instruccion.get(node.getOperador()), node.getLeftExpr().getTipo());
		return null;
	    }

	    @Override
	    public Object visit(ExpresionLogica node, Object param) {
		assert (param == Funcion.VALOR);
		node.getLeftExpr().accept(this, Funcion.VALOR);
		node.getRightExpr().accept(this, Funcion.VALOR);
		out(instruccion.get(node.getOperador()));
		return null;
	    }

	    @Override
	    public Object visit(AccesoArray node, Object param) {
		if (param == Funcion.DIRECCION) {
		    node.getIdent().accept(this, Funcion.DIRECCION);
		    node.getIndex().accept(this, Funcion.VALOR);
		    out("push " + node.getTipo().accept(new GestionDeMemoria(), Funcion.TAM));
		    out(instruccion.get("*"));
		    out(instruccion.get("+"));
		} else {
		    visit(node, Funcion.DIRECCION);
		    out("load", node.getTipo());
		}
		return null;
	    }

	    @Override
	    public Object visit(Not node, Object param) {
		if (node.getExpresion() != null)
		    node.getExpresion().accept(this, Funcion.VALOR);
		out(instruccion.get("!"));
		return null;
	    }

	    @Override
	    public Object visit(MenosUnario node, Object param) {
		out("push 0");
		node.getExpresion().accept(this, Funcion.VALOR);
		out(instruccion.get("-"));
		return null;
	    }

	    @Override
	    public Object visit(Cast node, Object param) {
		node.getExpresion().accept(this, Funcion.VALOR);
		out(node.getExpresion().getTipo().accept(this, Funcion.SUFIJO) + "2"
			+ node.getTipo().accept(this, Funcion.SUFIJO));
		return null;
	    }

	    @Override
	    public Object visit(LlamadaFuncion node, Object param) {
		visitChildren(node.getArgumentos(), Funcion.VALOR);
		out("call " + node.getIdent());

		return null;
	    }

	    @Override
	    public Object visit(Invocacion node, Object param) {
		out("#line " + node.getEnd().getLine());
		visitChildren(node.getArgumentos(), Funcion.VALOR);
		out("call " + node.getIdent());
		if (node.getDefFuncion().getTipo().getClass() != TipoVoid.class) {
		    TipoFuncion tipoFuncion = (TipoFuncion) node.getDefFuncion().getTipo();
		    out("pop", tipoFuncion.getTipoRetorno());
		}

		return null;
	    }

	    @Override
	    public Object visit(Variable node, Object param) {
		if (param == Funcion.VALOR) {
		    visit(node, Funcion.DIRECCION);
		    out("load", node.getTipo());
		} else {
		    if (locales.contains(node.getDefVariable())) {
			out("push bp");
			out("push " + node.getDefinicion().getDireccion());
			out("add");
		    } else if (params.containsKey(node.getIdent())) {
			out("push bp");
			out("push " + params.get(node.getIdent()).getAmbito());
			out("add");
		    } else
			out("pusha " + node.getDefinicion().getDireccion());

		}
		return null;
	    }

	    @Override
	    public Object visit(LitReal node, Object param) {
		assert (param == Funcion.VALOR);
		out("pushf " + node.getValor());
		return null;
	    }

	    @Override
	    public Object visit(LitInt node, Object param) {
		assert (param == Funcion.VALOR);
		out("pushi " + node.getValor());
		return null;
	    }

	    @Override
	    public Object visit(LitChar node, Object param) {
		assert (param == Funcion.VALOR);
		out("pushb " + (int) node.getValor());
		return null;
	    }

	    // Sufijos ---------------------------------------------
	    @Override
	    public Object visit(TipoInt node, Object param) {
		assert (param == Funcion.SUFIJO);
		return "i";
	    }

	    @Override
	    public Object visit(TipoReal node, Object param) {
		assert (param == Funcion.SUFIJO);
		return "f";
	    }

	    @Override
	    public Object visit(TipoChar node, Object param) {
		assert (param == Funcion.SUFIJO);
		return "b";
	    }

	    @Override
	    public Object visit(TipoFuncion node, Object param) {
		assert (param == Funcion.SUFIJO);
		return ' ';
	    }

	    @Override
	    public Object visit(TipoStruct node, Object param) {
		assert (param == Funcion.SUFIJO);
		return ' ';
	    }

	    @Override
	    public Object visit(TipoArray node, Object param) {
		assert (param == Funcion.SUFIJO);
		return node.getTipo().accept(this, Funcion.SUFIJO);
	    }

	    // Método auxiliar recomendado -------------
	    private void out(String instruccion) {
		writer.println(instruccion);
	    }

	    private void out(String instruccion, Tipo tipo) {
		writer.println(instruccion + tipo.accept(this, Funcion.SUFIJO));
	    }

	    // Tamaños totales
	    private int totalLocales(List<DefVariable> vars, Object param) {
		int total = 0;
		for (DefVariable def : vars) {
		    total += (int) def.getTipo().accept(new GestionDeMemoria(), Funcion.TAM);
		}
		return total;
	    }

	    private int totalParams(List<DefVariable> params, Object param) {
		int total = 0;
		for (DefVariable def : params) {
		    total += (int) def.getTipo().accept(new GestionDeMemoria(), Funcion.TAM);
		}
		return total;
	    }

	    private PrintWriter writer;
	    private String sourceFile;

	    private Map<String, String> instruccion;
	    private List<DefVariable> locales;
	    private Map<String, DefVariable> params;

	    private int numWhile;
	    private int numIfElse;
	    private int numIf;	
	
	
	
	
	// Método auxiliar recomendado -------------
	private void genera(String instruccion) {
		writer.println(instruccion);
	}

}
