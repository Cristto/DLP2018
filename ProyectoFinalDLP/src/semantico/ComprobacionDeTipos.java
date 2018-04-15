package semantico;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.List;

import ast.Position;
import ast.definitions.DefCampo;
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
import ast.expressions.Expression;
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
import ast.statements.Println;
import ast.statements.Printsp;
import ast.statements.Read;
import ast.statements.Return;
import ast.statements.While;
import ast.types.TipoArray;
import ast.types.TipoError;
import ast.types.CharTipo;
import ast.types.TipoFuncion;
import ast.types.TipoStruct;
import ast.types.IntTipo;
import ast.types.RealTipo;
import ast.types.Tipo;
import ast.types.TipoVoid;
import main.GestorErrores;
import visitor.DefaultVisitor;

public class ComprobacionDeTipos extends DefaultVisitor {

	public ComprobacionDeTipos(GestorErrores gestor) {
		this.gestorErrores = gestor;
	}

	/*
	 * Poner aquí los visit necesarios. Si se ha usado VGen solo hay que
	 * copiarlos de la clase 'visitor/_PlantillaParaVisitors.txt'.
	 */

	// class DefFuncion { String ident; Type tipo; List<Definition>
	// defParametros; List<Definition> defVarLocal; List<Statement> sentencias;
	// }
	public Object visit(DefFuncion node, Object param) {
		node.getTipo().accept(this, param);
		if (!esSimple(((TipoFuncion) node.getTipo()).getTipoRetorno())
				&& !esVoid(((TipoFuncion) node.getTipo()).getTipoRetorno())) {
			gestorErrores.error("Fase tipos", "el tipo de retorno no es de tipo simple", node.getStart());
		}
		visitChildren(node.getDefVarLocal(), param);
		visitChildren(node.getSentencias(), getTipoRetorno(node.getTipo()));

		return null;
	}

	// class IfElse { Expression condicion; List<Statement> sentenciasIf;
	// List<Statement> sentenciasElse; }
	public Object visit(IfElse node, Object param) {

		super.visit(node, param);
		if (!esLogico(node.getCondicion().getTipo()))
			gestorErrores.error("Fase tipos", "La condicion debe ser de tipo entero " + node.getCondicion(),
					node.getStart());
		return null;
	}

	// class While { Expression condicion; List<Statement> sentencias; }
	public Object visit(While node, Object param) {

		super.visit(node, param);
		if (!esLogico(node.getCondicion().getTipo()))
			gestorErrores.error("Fase tipos", "La condicion debe ser de tipo entero " + node.getCondicion(),
					node.getStart());
		return null;
	}

	// class Read { Expression expresion; }
	public Object visit(Read node, Object param) {

		super.visit(node, param);
		if (!esSimple(node.getExpresion().getTipo()))
			gestorErrores.error("Fase tipos", "Error: debe de ser de tipo simple", node.getStart());
		return null;
	}

	// class Print { Expression expresion; }
	public Object visit(Print node, Object param) {

		super.visit(node, param);
		if (!esSimple(node.getExpresion().getTipo()))
			gestorErrores.error("Fase tipos", "debe ser de tipo simple ", node.getStart());

		return null;
	}

	// class Printsp { Expression expresion; }
	public Object visit(Printsp node, Object param) {

		super.visit(node, param);
		if (!esSimple(node.getExpresion().getTipo()))
			gestorErrores.error("Fase tipos", "debe ser de tipo simple ", node.getStart());

		return null;
	}

	// class Println { Expression expresion; }
	public Object visit(Println node, Object param) {

		super.visit(node, param);
		if (!esSimple(node.getExpresion().getTipo()))
			gestorErrores.error("Fase tipos", "debe ser de tipo simple ", node.getStart());

		return null;
	}

	// class Asignacion { Expression leftExpr; Expression rightExpr; }
	public Object visit(Asignacion node, Object param) {

		super.visit(node, param);
		if (!esSimple(node.getLeftExpr().getTipo()))
			gestorErrores.error("Fase tipos", "la izquierda tiene que ser de tipo simple ", node.getStart());
		else if (!mismoTipo(node.getLeftExpr().getTipo(), node.getRightExpr().getTipo()))
			gestorErrores.error("Fase tipos", "valores de distinto tipo ", node.getStart());
		return null;
	}

	// class Return { Expression expresion; }
	public Object visit(Return node, Object param) {

		super.visit(node, param);
		if (node.getExpresion() == null) {
			if (!esVoid((Tipo) param)) {
				gestorErrores.error("Fase tipos", "el return no debe funcionar en tipoVoid", node.getStart());
			}
		} else {
			if (!mismoTipo((Tipo) param, node.getExpresion().getTipo())) {
				gestorErrores.error("Fase tipos", "el retorno no coincide con el retorno de la funcion ",
						node.getStart());
			}
		}

		return null;
	}

	// class Invocacion { String ident; List<Expression> argumentos; }
	public Object visit(Invocacion node, Object param) {
		super.visit(node, param);

		if (getTipoRetorno(node.getDefFuncion().getTipo()) != null) {
			if (!isArgumentosValido(((TipoFuncion) node.getDefFuncion().getTipo()).getDefParametros(),
					node.getArgumentos()))
				gestorErrores.error("Fase tipos", "los argumentos y los parametros no coinciden", node.getStart());

		} else {
			gestorErrores.error("Fase tipos", "No es de tipo funcion", node.getStart());
		}
		return null;
	}

	// class ExpresionAritmetica { Expression leftExpr; String operador;
	// Expression rightExpr; }
	public Object visit(ExpresionAritmetica node, Object param) {

		super.visit(node, param);
		if (esAritmetico(node.getLeftExpr().getTipo())) {
			if (mismoTipo(node.getLeftExpr().getTipo(), node.getRightExpr().getTipo())) {
				node.setTipo(node.getLeftExpr().getTipo());
			} else {
				node.setTipo(new TipoError());
				gestorErrores.error("Fase tipos", "Los operandos deben ser del mismo tipo ", node.getStart());
			}
		} else {
			node.setTipo(new TipoError());
			gestorErrores.error("Fase tipos", "Los operandos deben ser de tipo entero o real ", node.getStart());
		}
		return null;
	}

	// class Comparacion { Expression leftExpr; String operador; Expression
	// rightExpr; }
	public Object visit(Comparacion node, Object param) {

		super.visit(node, param);
		if (esComparable(node.getLeftExpr().getTipo())) {
			if (mismoTipo(node.getLeftExpr().getTipo(), node.getRightExpr().getTipo())) {
				node.setTipo(IntTipo.getInstance());
			} else {
				node.setTipo(new TipoError());
				gestorErrores.error("Fase tipos", "Los operandos deben ser del mismo tipo ", node.getStart());
			}
		} else {
			node.setTipo(new TipoError());
			gestorErrores.error("Fase tipos", "Los operandos deben ser de tipo entero o real ", node.getStart());
		}
		return null;
	}

	// class ExpresionLogica { Expression leftExpr; String operador; Expression
	// rightExpr; }
	public Object visit(ExpresionLogica node, Object param) {

		super.visit(node, param);
		if (esLogico(node.getLeftExpr().getTipo())) {
			if (mismoTipo(node.getLeftExpr().getTipo(), node.getRightExpr().getTipo())) {
				node.setTipo(IntTipo.getInstance());
			} else {
				node.setTipo(new TipoError());
				gestorErrores.error("Fase tipos", "Los operandos deben ser del mismo tipo ", node.getStart());
			}
		} else {
			node.setTipo(new TipoError());
			gestorErrores.error("Fase tipos", "Los operandos deben ser de tipo entero o real ", node.getStart());
		}
		return null;
	}

	// class MenosUnario { Expression expresion; }
	public Object visit(MenosUnario node, Object param) {

		super.visit(node, param);
		if (!esAritmetico(node.getExpresion().getTipo())) {
			node.setTipo(new TipoError());
			gestorErrores.error("Fase tipos", "la expresion debe ser un entero", node.getStart());
		} else
			node.setTipo(node.getExpresion().getTipo());
		return null;
	}

	// class Not { Expression expresion; }
	public Object visit(Not node, Object param) {

		super.visit(node, param);
		if (!esLogico(node.getExpresion().getTipo())) {
			node.setTipo(new TipoError());
			gestorErrores.error("Fase tipos", "debe ser de tipo entero", node.getStart());
		} else
			node.setTipo(IntTipo.getInstance());
		return null;
	}

	// class AccesoArray { Expression ident; Expression index; }
	public Object visit(AccesoArray node, Object param) {

		super.visit(node, param);
		if (node.getIdent().getTipo() instanceof TipoArray) {
			if (node.getIndex().getTipo() instanceof IntTipo) {
				node.setTipo(((TipoArray)node.getIdent().getTipo()).getTipo());
			} else {
				node.setTipo(new TipoError());
				gestorErrores.error("Fase tipos", "el indice debe ser de tipo" + "entero", node.getStart());
			}
		} else {
			node.setTipo(new TipoError());
			gestorErrores.error("Fase tipos", "debe ser de tipo array", node.getStart());
		}
		return null;
	}

	// class AccesoStruct { Expression ident; String campo; }
	public Object visit(AccesoStruct node, Object param) {
		super.visit(node, param);
		if (node.getIdent().getTipo() instanceof TipoStruct) {
			node.setTipo(
					getCampo((DefStruct) ((TipoStruct) node.getIdent().getTipo()).getDefstruct(), node.getCampo()));
		} else {
			node.setTipo(new TipoError());
			gestorErrores.error("Fase tipos", "debe ser de tipo array", node.getStart());
		}
		return null;
	}

	// class Variable { String ident; }
	public Object visit(Variable node, Object param) {
		node.setTipo(node.getDefVariable().getTipo());
		return null;
	}

	// class LlamadaFuncion { String ident; List<Expression> argumentos; }
	public Object visit(LlamadaFuncion node, Object param) {
		super.visit(node, param);

		if (getTipoRetorno(node.getDefFuncion().getTipo()) != null) {
			if (!isArgumentosValido(((TipoFuncion) node.getDefFuncion().getTipo()).getDefParametros(),
					node.getArgumentos()))
				gestorErrores.error("Fase tipos", "los argumentos y los parametros no coinciden", node.getStart());
			else
				node.setTipo(getTipoRetorno(node.getDefFuncion().getTipo()));

		} else {
			gestorErrores.error("Fase tipos", "No es de tipo funcion", node.getStart());
		}
		return null;
	}

	// class Cast { Type tipo; Expression expresion; }
	public Object visit(Cast node, Object param) {

		super.visit(node, param);
		node.setTipo(node.getTipoDinamico());
		if (!esSimple(node.getTipo()))
			gestorErrores.error("Fase tipos", "se tiene que castear con un tipo simple", node.getStart());
		if (!esSimple(node.getExpresion().getTipo()))
			gestorErrores.error("Fase tipos", "las expresiones que se castean" + "deben ser de tipo simple",
					node.getStart());
		if (mismoTipo(node.getExpresion().getTipo(), node.getTipoDinamico()))
			gestorErrores.error("Fase tipos", "los tipos del cast han de ser diferentes",
					node.getStart());
		// Si hay errores el tipo del cast deberia ser tipoError
		/*
		 * if (mismoTipo(node.getTipo(), node.getExpresion().getTipo()))
		 * gestorErrores.error("Fase tipos","tienen que ser de distinto tipo",
		 * node.getStart());
		 */
		return null;
	}

	// class LitInt { int valor; }
	public Object visit(LitInt node, Object param) {
		node.setTipo(IntTipo.getInstance());

		return null;
	}

	// class LitReal { float valor; }
	public Object visit(LitReal node, Object param) {
		node.setTipo(RealTipo.getInstance());

		return null;
	}

	// class LitChar { char valor; }
	public Object visit(LitChar node, Object param) {
		node.setTipo(CharTipo.getInstance());

		return null;
	}

	@Override
	public Object visit(TipoFuncion node, Object param) {
		if (node.getTipoRetorno() != null)
			node.getTipoRetorno().accept(this, param);
		for (Definition defParam : node.getDefParametros()) {
			defParam.accept(this, param);
			if (!esSimple(defParam.getTipo())) {
				gestorErrores.error("Fase tipos", "los parametros deben de ser de tipo Simple", defParam.getStart());
			}
		}
		return null;
	}

	private boolean esSimple(Tipo tipo) {
		if (tipo instanceof IntTipo || tipo instanceof RealTipo || tipo instanceof CharTipo)
			return true;
		else
			return false;
	}

	private boolean esVoid(Tipo tipo) {
		if (tipo instanceof TipoVoid)
			return true;
		else
			return false;
	}

	private boolean esAritmetico(Tipo tipo) {
		if (tipo instanceof IntTipo || tipo instanceof RealTipo)
			return true;
		else
			return false;
	}

	private boolean esComparable(Tipo tipo) {
		if (tipo instanceof IntTipo || tipo instanceof RealTipo)
			return true;
		else
			return false;
	}

	private boolean esLogico(Tipo tipo) {
		if (tipo instanceof IntTipo)
			return true;
		else
			return false;
	}

	private Tipo getTipoRetorno(Tipo tipo) {
		if (tipo instanceof TipoFuncion)
			return ((TipoFuncion) tipo).getTipoRetorno();
		else
			return null;
	}

	private boolean mismoTipo(Tipo a, Tipo b) {
		return (a.getClass() == b.getClass());
	}

	private boolean isArgumentosValido(List<Definition> list, List<Expression> argumentos) {
		if (list.size() != argumentos.size())
			return false;
		else {
			for (int i = 0; i < list.size(); i++) {
				if (!mismoTipo(argumentos.get(i).getTipo(), list.get(i).getTipo())) {
					return false;
				}
			}
			return true;
		}
	}

	private Tipo getCampo(DefStruct def, String field) {
		for (Definition fieldDef : def.getCampos()) {
			if (fieldDef.getNombre().equals(field))
				return fieldDef.getTipo();
		}
		return new TipoError();
	}

	/**
	 * Método auxiliar opcional para ayudar a implementar los predicados de la
	 * Gramática Atribuida.
	 * 
	 * Ejemplo de uso (suponiendo implementado el método "esPrimitivo"):
	 * predicado(esPrimitivo(expr.tipo),
	 * "La expresión debe ser de un tipo primitivo", expr.getStart());
	 * predicado(esPrimitivo(expr.tipo),
	 * "La expresión debe ser de un tipo primitivo", null);
	 * 
	 * NOTA: El método getStart() indica la linea/columna del fichero fuente de
	 * donde se leyó el nodo. Si se usa VGen dicho método será generado en todos
	 * los nodos AST. Si no se quiere usar getStart() se puede pasar null.
	 * 
	 * @param condicion
	 *            Debe cumplirse para que no se produzca un error
	 * @param mensajeError
	 *            Se imprime si no se cumple la condición
	 * @param posicionError
	 *            Fila y columna del fichero donde se ha producido el error. Es
	 *            opcional (acepta null)
	 */
	private void predicado(boolean condicion, String mensajeError, Position posicionError) {
		if (!condicion)
			gestorErrores.error("Comprobación de tipos", mensajeError, posicionError);
	}

	private GestorErrores gestorErrores;
}
