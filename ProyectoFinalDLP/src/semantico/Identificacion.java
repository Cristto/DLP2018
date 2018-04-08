package semantico;

import ast.Position;
import ast.definitions.DefCampo;
import ast.definitions.DefFuncion;
import ast.definitions.DefStruct;
import ast.definitions.DefVariable;
import ast.definitions.Definition;
import ast.expressions.LlamadaFuncion;
import ast.expressions.Variable;
import ast.statements.Invocacion;
import ast.statements.Return;
import ast.types.ArrayTipo;
import ast.types.TipoStruct;
import ast.types.VoidTipo;
import main.GestorErrores;
import semantico.context.ContextMap;
import visitor.DefaultVisitor;


public class Identificacion extends DefaultVisitor {
	
	private GestorErrores gestorErrores;
	private ContextMap<String, DefFuncion> funciones = new ContextMap<String,DefFuncion>();
	//todos en la pila de variables
	private ContextMap<String, DefVariable> variables = new ContextMap<String,DefVariable>();
	private ContextMap<String, DefStruct> structs = new ContextMap<String,DefStruct>();
	private ContextMap<String, DefCampo> campos = new ContextMap<String,DefCampo>();

	public Identificacion(GestorErrores gestor) {
		this.gestorErrores = gestor;
		
	}

	
	
	/*
	 * Poner aqu� los visit necesarios.
	 * Si se ha usado VGen solo hay que copiarlos de la clase 'visitor/_PlantillaParaVisitors.txt'.
	 */

	// public Object visit(Programa prog, Object param) {
	// ...
	// }
	
	public Object visit(DefFuncion node, Object param) {
		
		//predicado(funciones.getFromAny(node.getIdent()) == null, "funcion repetida", node.getStart());
		//funciones.put(node.getIdent(), node);
		
		if (funciones.getFromAny(node.getNombre()) != null) {
		    gestorErrores.error("Identificacion", "Funcion " + node.getNombre() + " ya definida", node.getStart());
		} else {
		    funciones.put(node.getNombre(), node);

		}
		
		//funciones.set();
		variables.set();
		super.visit(node, param);
		//funciones.reset();
		variables.reset();
		return null;
	}
	
	public Object visit(Invocacion node, Object param){
		
		//predicado(funciones.getFromAny(node.getIdent()) != null, "Procedimiento no definido", node.getStart());
		//node.setDefFuncion(funciones.getFromAny(node.getIdent()));
		if (funciones.getFromAny(node.getIdent()) == null)
			gestorErrores.error("Identificacion" , "Funcion " + node.getIdent() + " no definida", node.getStart());
		else
		    node.setDefFuncion(funciones.getFromAny(node.getIdent()));
		
		
		super.visit(node, param);
		return null;
	}
	
	public Object visit(LlamadaFuncion node, Object param){
		if(funciones.getFromAny(node.getIdent()) == null)
			gestorErrores.error("Identificaci�n", "Funci�n no definida", node.getStart());
		else			
			node.setDefFuncion(funciones.getFromAny(node.getIdent()));
		
		super.visit(node, param);
		return null;
	}
	
	
	public Object visit(DefVariable node, Object param){				
			
		
		Definition defVar = variables.getFromTop(node.getNombre());
		
		if(defVar ==  null){
			variables.put(node.getNombre(), node);
			super.visit(node, param);
		}
		else
			gestorErrores.error("Identificacion", node.getNombre() + " ya esta definida", node.getStart());
		
		
		
		return null;
	}
	
	public Object visit(Variable node, Object param){
		
		DefVariable defVar = variables.getFromAny(node.getIdent());
		if (defVar == null)
			gestorErrores.error("Identificaci�n", node.getIdent()+" variable no definida", node.getStart());
		else
		    node.setDefVariable(defVar);

		//super.visit(node,param);
		return null;
	}
	
	public Object visit(DefStruct node, Object param) {
		
		DefStruct defStruct = structs.getFromAny(node.getNombre());
		
		if(defStruct == null)
			structs.put(node.getNombre(), node);
		else
			gestorErrores.error("Identificaci�n", node.getNombre()+" ya definida", node.getStart());
		
		//predicado(structs.getFromAny(node.getIdent()) == null,"estructura repetida",node.getStart());
		//structs.put(node.getIdent(), node);

		/*IdentTipo tipo = new IdentTipo(node.getIdent());
		tipo.setDefstruct(node);
		node.setTipo(tipo);*/

		
		variables.set();
		super.visit(node, param);
		variables.reset();
		return null;
		
	}
	
	
	public Object visit(DefCampo node, Object param){
		
				
		DefCampo defCampo = campos.getFromTop(node.getNombre());
		
		if(defCampo == null)
			campos.put(node.getNombre(), node);
		else
			gestorErrores.error("Identificacion","el campo "+ node.getNombre()+" ya esta repetido", node.getStart());
			
		//campos.set();
		super.visit(node, param);
		//campos.reset();
		return null;
	}
	
	public Object visit(ArrayTipo node, Object param) {
		node.getTipo().accept(this, param);
		return null;
	}
	
	public Object visit(TipoStruct node, Object param) {
		
		if(structs.getFromAny(node.getValor()) == null)
			gestorErrores.error("Identidicacion", node.getValor()+" no definido", node.getStart());
		else
			node.setDefstruct(structs.getFromAny(node.getValor()));
		
		//predicado(structs.getFromAny(node.getValor()) != null,"no definido",node.getStart());
		//node.setDefstruct(structs.getFromAny(node.getValor()));

		return null;
	}
	
	public Object visit(Return node, Object param) {
		if (node.getExpresion() != null)
		    node.getExpresion().accept(this, param);
		else
		    node.setTipo(new VoidTipo());
		return null;
	    }
	
	
	
	/**
	 * M�todo auxiliar opcional para ayudar a implementar los predicados de la Gram�tica Atribuida.
	 * 
	 * Ejemplo de uso:
	 * 	predicado(variables.get(nombre), "La variable no ha sido definida", expr.getStart());
	 * 	predicado(variables.get(nombre), "La variable no ha sido definida", null);
	 * 
	 * NOTA: El m�todo getStart() indica la linea/columna del fichero fuente de donde se ley� el nodo.
	 * Si se usa VGen dicho m�todo ser� generado en todos los nodos AST. Si no se quiere usar getStart() se puede pasar null.
	 * 
	 * @param condicion Debe cumplirse para que no se produzca un error
	 * @param mensajeError Se imprime si no se cumple la condici�n
	 * @param posicionError Fila y columna del fichero donde se ha producido el error. Es opcional (acepta null)
	 */
	private void predicado(boolean condicion, String mensajeError, Position posicionError) {
		if (!condicion)
			gestorErrores.error("Identificaci�n", mensajeError, posicionError);
	}


	
}
