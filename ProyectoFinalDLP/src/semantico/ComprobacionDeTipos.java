package semantico;

import java.util.ArrayList;
import java.util.List;

import ast.AccesoArray;
import ast.AccesoStruct;
import ast.ArrayTipo;
import ast.Asignacion;
import ast.Cast;
import ast.CharTipo;
import ast.Comparacion;
import ast.DefCampo;
import ast.DefFuncion;
import ast.DefVariable;
import ast.ExpresionAritmetica;
import ast.ExpresionLogica;
import ast.Expression;
import ast.FuncionTipo;
import ast.IdentTipo;
import ast.IfElse;
import ast.IntTipo;
import ast.Invocacion;
import ast.LitChar;
import ast.LitInt;
import ast.LitReal;
import ast.LlamadaFuncion;
import ast.MenosUnario;
import ast.Not;
import ast.Position;
import ast.Print;
import ast.Println;
import ast.Printsp;
import ast.Read;
import ast.RealTipo;
import ast.Return;
import ast.Type;
import ast.Variable;
import ast.VoidTipo;
import ast.While;
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

		if(!esVoid(node.getTipo()) && !esSimple(((FuncionTipo)node.getTipo()).getTipoRetorno()))
			gestorErrores.error("Fase tipos","Error: Retorno de tipo no simple "+node.getTipo(), node.getStart());
		for (int i = 0; i < node.getDefParametros().size(); i++) {
			if(!esSimple(node.getDefParametros().get(i).getTipo()))
				gestorErrores.error("Fase tipos","Error: Los parametros deben"
						+ "de tipo primitivo "+node.getTipo(), node.getStart());
				
		}
		visitChildren(node.getDefVarLocal(), param);
		visitChildren(node.getSentencias(), node.getTipo());
		
		return null;
	}

	//	class IfElse { Expression condicion;  List<Statement> sentenciasIf;  List<Statement> sentenciasElse; }
	public Object visit(IfElse node, Object param) {

		node.getCondicion().accept(this, param);
		node.getCondicion().setTipo(node.getCondicion().getTipo());
		if(!esLogico(node.getCondicion().getTipo()))
			gestorErrores.error("Fase tipos","La condicion debe ser de tipo entero "+node.getCondicion(), node.getStart());
		visitChildren(node.getSentenciasIf(), param);
		visitChildren(node.getSentenciasElse(), param);
		return null;
	}

	//	class While { Expression condicion;  List<Statement> sentencias; }
	public Object visit(While node, Object param) {

		super.visit(node, param);
		if(!esLogico(node.getCondicion().getTipo()))
			gestorErrores.error("Fase tipos","La condicion debe ser de tipo entero "+node.getCondicion(), node.getStart());
		
		visitChildren(node.getSentencias(), param);
		return null;
	}

	//	class Read { Expression expresion; }
	public Object visit(Read node, Object param) {

		super.visit(node, param);
		if (!esSimple(node.getExpresion().getTipo()))
			gestorErrores.error("Fase tipos","Error: debe de ser de tipo simple", node.getStart());
		if (!node.getExpresion().isLvalue())
			gestorErrores.error("Fase tipos","debe ser modificable ", node.getStart());
		return false;
	}

	//	class Print { Expression expresion; }
	public Object visit(Print node, Object param) {

		super.visit(node, param);

		if (node.getExpresion().getTipo() != null && !esSimple(node.getExpresion().getTipo()))
			gestorErrores.error("Fase tipos","debe ser de tipo simple ", node.getStart());

		return false;
	}

	//	class Printsp { Expression expresion; }
	public Object visit(Printsp node, Object param) {

		super.visit(node, param);

		if (node.getExpresion().getTipo() != null && !esSimple(node.getExpresion().getTipo()))
			gestorErrores.error("Fase tipos","debe ser de tipo simple ", node.getStart());

		return false;
	}

	//	class Println { Expression expresion; }
	public Object visit(Println node, Object param) {

		super.visit(node, param);

		if (node.getExpresion().getTipo() != null && !esSimple(node.getExpresion().getTipo()))
			gestorErrores.error("Fase tipos","debe ser de tipo simple ", node.getStart());

		return false;
	}

	//	class Asignacion { Expression leftExpr;  Expression rightExpr; }
	public Object visit(Asignacion node, Object param) {

		super.visit(node, param);
		if (!esSimple(node.getLeftExpr().getTipo()))
			gestorErrores.error("Fase tipos","la izquierda tiene que ser de tipo simple ", node.getStart());
		else if (!node.getLeftExpr().isLvalue())
			gestorErrores.error("Fase tipos","la izquierda de la asignacion "
					+ "no es modificable ", node.getStart());
		else if (node.getLeftExpr().getTipo() != node.getRightExpr().getTipo())
			gestorErrores.error("Fase tipos","valores de distinto tipo ", node.getStart());
		return false;
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

		return true;
	}

	//	class Invocacion { String ident;  List<Expression> argumentos; }
	public Object visit(Invocacion node, Object param) {

		List<DefVariable> parametros = node.getDefFuncion().getDefParametros();
		List<Expression> argumentos = node.getArgumentos();
		super.visit(node, param);
		if (!isArgumentosValido(parametros, argumentos))
			gestorErrores.error("Fase tipos","Los parametros y los argumentos no coinciden ", node.getStart());

		return false;
	}

	//	class ExpresionAritmetica { Expression leftExpr;  String operador;  Expression rightExpr; }
	public Object visit(ExpresionAritmetica node, Object param) {

		super.visit(node, param);
		if (!mismoTipo(node.getLeftExpr().getTipo(), node.getRightExpr().getTipo()))
			gestorErrores.error("Fase tipos","Los operandos deben ser del mismo tipo ", node.getStart());
		else if (!esAritmetico(node.getLeftExpr().getTipo()) || !esAritmetico(node.getRightExpr().getTipo()))
			gestorErrores.error("Fase tipos","Los operandos deben ser de tipo entero o real ", node.getStart());
		node.setTipo(node.getLeftExpr().getTipo());
		return null;
	}

	//	class Comparacion { Expression leftExpr;  String operador;  Expression rightExpr; }
	public Object visit(Comparacion node, Object param) {

		super.visit(node, param);
		if (!mismoTipo(node.getLeftExpr().getTipo(), node.getRightExpr().getTipo()))
			gestorErrores.error("Fase tipos","Las expresiones deben ser del mismo tipo ", node.getStart());
		else if (!esAritmetico(node.getLeftExpr().getTipo()) || !esAritmetico(node.getRightExpr().getTipo()))
			gestorErrores.error("Fase tipos","Las expresiones deben ser de tipo entero o real ", node.getStart());
		node.setTipo(new IntTipo());
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
		node.setTipo(new IntTipo());
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

		super.visit(node, param);
		if (!(node.getIdent().getTipo() instanceof IdentTipo))
			gestorErrores.error("Fase tipos","debe ser de tipo struct", node.getStart());
		else {
			IdentTipo tipo = (IdentTipo) node.getIdent().getTipo();
		    List<String> nombreCampos = new ArrayList<>();
		    List<DefCampo> camposStruct = tipo.getDefstruct().getCampos();
		    for (DefCampo cs : camposStruct) {
			nombreCampos.add(cs.getIdent());
			if (cs.getIdent().equals(node.getIdent()))
			    node.setTipo(cs.getTipo());
		    }
		    if (!nombreCampos.contains(node.getIdent()))
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

		List<DefVariable> parametros = node.getDefFuncion().getDefParametros();
		List<Expression> argumentos = node.getArgumentos();
		Type retorno = node.getDefFuncion().getTipo();
		super.visit(node, param);
		if (!isArgumentosValido(parametros, argumentos))
			gestorErrores.error("Fase tipos","los argumentos y los parametros no coinciden", node.getStart());

		if (retorno == null)
			gestorErrores.error("Fase tipos","La funcion no tiene retorno", node.getStart());
		else
		    node.setTipo(getTipoFuncion((node.getDefFuncion().getTipo())));
		return null;
	}

	//	class Cast { Type tipo;  Expression expresion; }
	public Object visit(Cast node, Object param) {

		super.visit(node, param);
		node.setTipo(node.getTipo());
		if (!esSimple(node.getTipo()))
			gestorErrores.error("Fase tipos","se tiene que castear con un tipo simple", node.getStart());
		if (!esSimple(node.getExpresion().getTipo()))
			gestorErrores.error("Fase tipos","las expresiones que se castean"
					+ "deben ser de tipo simple", node.getStart());
		if (mismoTipo(node.getTipo(), node.getExpresion().getTipo()))
			gestorErrores.error("Fase tipos","tienen que ser de distinto tipo", node.getStart());
		return null;
	}

	//	class LitInt { int valor; }
	public Object visit(LitInt node, Object param) {
		node.setTipo(new IntTipo());

		return null;
	}

	//	class LitReal { float valor; }
	public Object visit(LitReal node, Object param) {
		node.setTipo(new RealTipo());

		return null;
	}

	//	class LitChar { char valor; }
	public Object visit(LitChar node, Object param) {
		node.setTipo(new CharTipo());

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
		if(tipo instanceof FuncionTipo)
			return ((FuncionTipo) tipo).getTipoRetorno();
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
