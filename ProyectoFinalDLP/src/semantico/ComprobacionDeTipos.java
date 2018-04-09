package semantico;

import java.util.ArrayList;
import java.util.List;

import ast.Position;
import ast.definitions.DefCampo;
import ast.definitions.DefFuncion;
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
import ast.types.ArrayTipo;
import ast.types.CharTipo;
import ast.types.TipoFuncion;
import ast.types.TipoStruct;
import ast.types.IntTipo;
import ast.types.RealTipo;
import ast.types.Type;
import ast.types.VoidTipo;
import main.GestorErrores;
import visitor.DefaultVisitor;

public class ComprobacionDeTipos extends DefaultVisitor {

	public ComprobacionDeTipos(GestorErrores gestor) {
		this.gestorErrores = gestor;
	}

	
	
	/*
	 * Poner aquí los visit necesarios.
	 * Si se ha usado VGen solo hay que copiarlos de la clase 'visitor/_PlantillaParaVisitors.txt'.
	 */


	//	class DefFuncion { String ident;  Type tipo;  List<Definition> defParametros;  List<Definition> defVarLocal;  List<Statement> sentencias; }
	public Object visit(DefFuncion node, Object param) {

		node.getTipo().accept(this, param);
		visitChildren(node.getDefParametros(), param);
		if(!esVoid(node.getTipo()) && !esSimple(((TipoFuncion)node.getTipo()).getTipoRetorno()))
			gestorErrores.error("Fase tipos","Error: Retorno de tipo no simple "+node.getTipo(), node.getStart());
		visitChildren(node.getDefVarLocal(), param);
		visitChildren(node.getSentencias(), node.getTipo());
		
		return null;
	}

	//	class IfElse { Expression condicion;  List<Statement> sentenciasIf;  List<Statement> sentenciasElse; }
	public Object visit(IfElse node, Object param) {

		super.visit(node, param);
		if(!esLogico(node.getCondicion().getTipo()))
			gestorErrores.error("Fase tipos","La condicion debe ser de tipo entero "+node.getCondicion(), node.getStart());
		return null;
	}

	//	class While { Expression condicion;  List<Statement> sentencias; }
	public Object visit(While node, Object param) {

		super.visit(node, param);
		if(!esLogico(node.getCondicion().getTipo()))
			gestorErrores.error("Fase tipos","La condicion debe ser de tipo entero "+node.getCondicion(), node.getStart());
		return null;
	}

	//	class Read { Expression expresion; }
	public Object visit(Read node, Object param) {

		super.visit(node, param);
		if (!esSimple(node.getExpresion().getTipo()))
			gestorErrores.error("Fase tipos","Error: debe de ser de tipo simple", node.getStart());
		return null;
	}

	//	class Print { Expression expresion; }
	public Object visit(Print node, Object param) {

		super.visit(node, param);

		if (node.getExpresion().getTipo() != null && !esSimple(node.getExpresion().getTipo()))
			gestorErrores.error("Fase tipos","debe ser de tipo simple ", node.getStart());

		return null;
	}

	//	class Printsp { Expression expresion; }
	public Object visit(Printsp node, Object param) {

		super.visit(node, param);

		if (node.getExpresion().getTipo() != null && !esSimple(node.getExpresion().getTipo()))
			gestorErrores.error("Fase tipos","debe ser de tipo simple ", node.getStart());

		return null;
	}

	//	class Println { Expression expresion; }
	public Object visit(Println node, Object param) {

		super.visit(node, param);

		if (node.getExpresion().getTipo() != null && !esSimple(node.getExpresion().getTipo()))
			gestorErrores.error("Fase tipos","debe ser de tipo simple ", node.getStart());

		return null;
	}

	//	class Asignacion { Expression leftExpr;  Expression rightExpr; }
	public Object visit(Asignacion node, Object param) {

		super.visit(node, param);
		if (!esSimple(node.getLeftExpr().getTipo()))
			gestorErrores.error("Fase tipos","la izquierda tiene que ser de tipo simple ", node.getStart());
		else if (node.getLeftExpr().getTipo() != node.getRightExpr().getTipo())
			gestorErrores.error("Fase tipos","valores de distinto tipo ", node.getStart());
		return null;
	}

	//	class Return { Expression expresion; }
	public Object visit(Return node, Object param) {

		super.visit(node, param);
		if (getTipoFuncion((Type) param) == null)
			gestorErrores.error("Fase tipos","el return no debe funcionar en tipoVoid", node.getStart());
		else if (!(getTipoFuncion((Type) param) instanceof VoidTipo) && node.getExpresion() == null)
			gestorErrores.error("Fase tipos","debe haber un valor de retorno ", node.getStart());
		else if (node.getExpresion() != null && !mismoTipo(node.getExpresion().getTipo(), getTipoFuncion((Type) param)))
			gestorErrores.error("Fase tipos","el retorno no coincide con "
					+ "el retorno de la funcion ", node.getStart());

		return null;
	}

	//	class Invocacion { String ident;  List<Expression> argumentos; }
	public Object visit(Invocacion node, Object param) {
		super.visit(node, param);

		if(node.getDefFuncion().getTipo() instanceof TipoFuncion) {
			if (!isArgumentosValido(node.getDefFuncion().getDefParametros(), node.getArgumentos()))
				gestorErrores.error("Fase tipos","los argumentos y los parametros no coinciden", node.getStart());

		} else {

			gestorErrores.error("Fase tipos","No es de tipo funcion", node.getStart());
		}
		return null;
	}

	//	class ExpresionAritmetica { Expression leftExpr;  String operador;  Expression rightExpr; }
	public Object visit(ExpresionAritmetica node, Object param) {

		super.visit(node, param);
		if (!mismoTipo(node.getLeftExpr().getTipo(), node.getRightExpr().getTipo()))
			gestorErrores.error("Fase tipos","Los operandos deben ser del mismo tipo ", node.getStart());
		else if (!esAritmetico(node.getLeftExpr().getTipo()))
			gestorErrores.error("Fase tipos","Los operandos deben ser de tipo entero o real ", node.getStart());
		node.setTipo(node.getLeftExpr().getTipo());
		return null;
	}

	//	class Comparacion { Expression leftExpr;  String operador;  Expression rightExpr; }
	public Object visit(Comparacion node, Object param) {

		super.visit(node, param);
		if (!mismoTipo(node.getLeftExpr().getTipo(), node.getRightExpr().getTipo()))
			gestorErrores.error("Fase tipos","Las expresiones deben ser del mismo tipo ", node.getStart());
		else if (!esAritmetico(node.getLeftExpr().getTipo()))
			gestorErrores.error("Fase tipos","Las expresiones deben ser de tipo entero o real ", node.getStart());
		node.setTipo(IntTipo.getInstance());
		return null;
	}

	//	class ExpresionLogica { Expression leftExpr;  String operador;  Expression rightExpr; }
	public Object visit(ExpresionLogica node, Object param) {

		super.visit(node, param);
		if (!esLogico(node.getLeftExpr().getTipo()) || !(esLogico(node.getRightExpr().getTipo())))
			gestorErrores.error("Fase tipos","La expresion debe ser de tipo entero", node.getStart());
		node.setTipo(node.getLeftExpr().getTipo());
		return null;
	}

	//	class MenosUnario { Expression expresion; }
	public Object visit(MenosUnario node, Object param) {

		super.visit(node, param);
		if (!esAritmetico(node.getExpresion().getTipo()))
			gestorErrores.error("Fase tipos","la expresion debe ser un entero", node.getStart());
		else
		    node.setTipo(node.getExpresion().getTipo());
		return null;
	}

	//	class Not { Expression expresion; }
	public Object visit(Not node, Object param) {

		super.visit(node, param);
		if (!esLogico(node.getExpresion().getTipo())) {
			gestorErrores.error("Fase tipos","debe ser de tipo entero", node.getStart());
		}
		node.setTipo(IntTipo.getInstance());
		return null;
	}

	//	class AccesoArray { Expression ident;  Expression index; }
	public Object visit(AccesoArray node, Object param) {

		super.visit(node, param);
		Type type = node.getIdent().getTipo();
		if (type != null && type instanceof ArrayTipo) {
		    ArrayTipo tipoArray = (ArrayTipo) node.getIdent().getTipo();
		    node.setTipo(tipoArray.getTipo());
		} else
			gestorErrores.error("Fase tipos","debe ser de tipo array", node.getStart());
		if (!(node.getIndex().getTipo() instanceof IntTipo))
			gestorErrores.error("Fase tipos","el indice debe ser de tipo"
					+ "entero", node.getStart());
		return null;
	}

	//	class AccesoStruct { Expression ident;  String campo; }
	public Object visit(AccesoStruct node, Object param) {
		boolean existe = false;
		super.visit(node, param);
		if (!(node.getIdent().getTipo() instanceof TipoStruct))
			gestorErrores.error("Fase tipos","debe ser de tipo struct", node.getStart());
		else {
			TipoStruct tipo = (TipoStruct) node.getIdent().getTipo();
		    List<Definition> camposStruct = tipo.getDefstruct().getCampos();
		    for (Definition cs : camposStruct) {
		    	if (cs.getNombre().equals(node.getCampo())) {
		    		existe = true;
		    		node.setTipo(cs.getTipo());
		    		break;
		    	}
		    }
		    if (!existe)
		    	gestorErrores.error("Fase tipos","el campo "+node.getIdent()+" no definido", node.getStart());
		}
		return null;
	}

	//	class Variable { String ident; }
	public Object visit(Variable node, Object param) {
		node.setTipo(node.getDefVariable().getTipo());
		return null;
	}

	//	class LlamadaFuncion { String ident;  List<Expression> argumentos; }
	public Object visit(LlamadaFuncion node, Object param) {
		super.visit(node, param);
		if(node.getDefFuncion().getTipo() instanceof TipoFuncion) {
		if (!isArgumentosValido(node.getDefFuncion().getDefParametros(), node.getArgumentos()))
			gestorErrores.error("Fase tipos","los argumentos y los parametros no coinciden", node.getStart());

		    node.setTipo(((TipoFuncion) node.getDefFuncion().getTipo()).getTipoRetorno());
		} else {

			gestorErrores.error("Fase tipos","No es de tipo funcion", node.getStart());
		}
		return null;
	}

	//	class Cast { Type tipo;  Expression expresion; }
	public Object visit(Cast node, Object param) {

		super.visit(node, param);
		node.setTipo(node.getTipoDinamico());
		if (!esSimple(node.getTipo()))
			gestorErrores.error("Fase tipos","se tiene que castear con un tipo simple", node.getStart());
		if (!esSimple(node.getExpresion().getTipo()))
			gestorErrores.error("Fase tipos","las expresiones que se castean"
					+ "deben ser de tipo simple", node.getStart());
		//Si hay errores el tipo del cast deberia ser tipoError
		/*if (mismoTipo(node.getTipo(), node.getExpresion().getTipo()))
			gestorErrores.error("Fase tipos","tienen que ser de distinto tipo", node.getStart());*/
		return null;
	}

	//	class LitInt { int valor; }
	public Object visit(LitInt node, Object param) {
		node.setTipo(IntTipo.getInstance());

		return null;
	}

	//	class LitReal { float valor; }
	public Object visit(LitReal node, Object param) {
		node.setTipo(RealTipo.getInstance());

		return null;
	}

	//	class LitChar { char valor; }
	public Object visit(LitChar node, Object param) {
		node.setTipo(CharTipo.getInstance());

		return null;
	}

	
	private boolean esSimple(Type tipo){
		if(tipo instanceof IntTipo || tipo instanceof RealTipo || tipo instanceof CharTipo)
			return true;
		else
			return false;
	}

	private boolean esVoid(Type tipo){
		if(tipo instanceof VoidTipo)
			return true;
		else
			return false;
	}
	
	private boolean esAritmetico(Type tipo){
		if(tipo instanceof IntTipo || tipo instanceof RealTipo)
			return true;
		else
			return false;
	}
	
	private boolean esLogico(Type tipo){
		if(tipo instanceof IntTipo)
			return true;
		else return false;
	}
	
	private Type getTipoFuncion(Type tipo){
		if(tipo instanceof TipoFuncion)
			return ((TipoFuncion) tipo).getTipoRetorno();
		else
			return tipo;
	}
	
	private boolean mismoTipo(Type a, Type b) {
		return (a.getClass() == b.getClass());
	    }
	
	 public boolean isArgumentosValido(List<DefVariable> parametros, List<Expression> argumentos) {
			if (parametros.size() != argumentos.size())
			    return false;
			else {
			    for (int i = 0; i < parametros.size(); i++) {
				if (!mismoTipo(argumentos.get(i).getTipo(), parametros.get(i).getTipo())) {
				    return false;
				}
			    }
			    return true;
			}
		    }
	
	/**
	 * Método auxiliar opcional para ayudar a implementar los predicados de la Gramática Atribuida.
	 * 
	 * Ejemplo de uso (suponiendo implementado el método "esPrimitivo"):
	 * 	predicado(esPrimitivo(expr.tipo), "La expresión debe ser de un tipo primitivo", expr.getStart());
	 * 	predicado(esPrimitivo(expr.tipo), "La expresión debe ser de un tipo primitivo", null);
	 * 
	 * NOTA: El método getStart() indica la linea/columna del fichero fuente de donde se leyó el nodo.
	 * Si se usa VGen dicho método será generado en todos los nodos AST. Si no se quiere usar getStart() se puede pasar null.
	 * 
	 * @param condicion Debe cumplirse para que no se produzca un error
	 * @param mensajeError Se imprime si no se cumple la condición
	 * @param posicionError Fila y columna del fichero donde se ha producido el error. Es opcional (acepta null)
	 */
	private void predicado(boolean condicion, String mensajeError, Position posicionError) {
		if (!condicion)
			gestorErrores.error("Comprobación de tipos", mensajeError, posicionError);
	}
	
	
	private GestorErrores gestorErrores;
}
