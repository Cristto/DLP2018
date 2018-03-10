package semantico;

import ast.DefFuncion;
import ast.Invocacion;
import ast.LlamadaFuncion;
import ast.Position;
import main.GestorErrores;
import semantico.context.ContextMap;
import visitor.DefaultVisitor;


public class Identificacion extends DefaultVisitor {
	
	private GestorErrores gestorErrores;
	private ContextMap<String, DefFuncion> funciones = new ContextMap<String,DefFuncion>();

	public Identificacion(GestorErrores gestor) {
		this.gestorErrores = gestor;
		
	}

	
	
	/*
	 * Poner aquí los visit necesarios.
	 * Si se ha usado VGen solo hay que copiarlos de la clase 'visitor/_PlantillaParaVisitors.txt'.
	 */

	// public Object visit(Programa prog, Object param) {
	// ...
	// }
	
	public Object visit(DefFuncion node, Object param) {

		if(funciones.getFromAny(node.getIdent()) == null)
			funciones.put(node.getIdent(), node);			
		else
			gestorErrores.error("Identificación", "funcion repetida", node.getStart());
		
		funciones.set();
		super.visit(node, param);
		funciones.reset();
		return null;
	}
	
	public Object visit(Invocacion node, Object param){
		if(funciones.getFromAny(node.getIdent()) == null)
			gestorErrores.error("Identificación", "Procedimiento no definido", node.getStart());
		else			
			node.setDefFuncion(funciones.getFromAny(node.getIdent()));
		
		super.visit(node, param);
		return null;
	}
	
	public Object visit(LlamadaFuncion node, Object param){
		if(funciones.getFromAny(node.getIdent()) == null)
			gestorErrores.error("Identificación", "Función no definida", node.getStart());
		else			
			node.setDefFuncion(funciones.getFromAny(node.getIdent()));
		
		super.visit(node, param);
		return null;
	}
	
	
	
	/**
	 * Método auxiliar opcional para ayudar a implementar los predicados de la Gramática Atribuida.
	 * 
	 * Ejemplo de uso:
	 * 	predicado(variables.get(nombre), "La variable no ha sido definida", expr.getStart());
	 * 	predicado(variables.get(nombre), "La variable no ha sido definida", null);
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
			gestorErrores.error("Identificación", mensajeError, posicionError);
	}


	
}
