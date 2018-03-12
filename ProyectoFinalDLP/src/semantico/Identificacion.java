package semantico;

import ast.ArrayTipo;
import ast.DefFuncion;
import ast.DefVariable;
import ast.Invocacion;
import ast.LlamadaFuncion;
import ast.Position;
import ast.Variable;
import main.GestorErrores;
import semantico.context.ContextMap;
import visitor.DefaultVisitor;


public class Identificacion extends DefaultVisitor {
	
	private GestorErrores gestorErrores;
	private ContextMap<String, DefFuncion> funciones = new ContextMap<String,DefFuncion>();
	private ContextMap<String, DefVariable> variables = new ContextMap<String,DefVariable>();

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
		
		predicado(funciones.getFromAny(node.getIdent()) == null, "funcion repetida", node.getStart());
		funciones.put(node.getIdent(), node);
		
		funciones.set();
		super.visit(node, param);
		funciones.reset();
		return null;
	}
	
	public Object visit(Invocacion node, Object param){
		
		predicado(funciones.getFromAny(node.getIdent()) != null, "Procedimiento no definido", node.getStart());
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
				
		super.visit(node, param);
		
		if(node.getAmbito()==1)
			predicado(variables.getFromAny(node.getIdent())==null,"Variable global "+node.getIdent()+" repetida",node.getStart());
		else if(node.getAmbito()==2)
			predicado(variables.getFromTop(node.getIdent())==null,"Parametro "+node.getIdent()+" repetido",node.getStart());		
		else
			predicado(variables.getFromTop(node.getIdent())==null,"Variable local "+node.getIdent()+" ya definida",node.getStart());
		
		
		variables.put(node.getIdent(), node);
		
		return null;
	}
	
	public Object visit(Variable node, Object param){
		if (variables.getFromAny(node.getIdent()) == null)
			gestorErrores.error("Identificaci�n", "variable no definida", node.getStart());
		else
		    node.setDefVariable(variables.getFromAny(node.getIdent()));

		return null;
	}
	
	public Object visit(ArrayTipo node, Object param) {
		node.getTipo().accept(this, param);
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
