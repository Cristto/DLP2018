package semantico;

import ast.definitions.DefCampo;
import ast.definitions.DefFuncion;
import ast.definitions.DefStruct;
import ast.definitions.DefVariable;
import ast.definitions.Definition;
import ast.expressions.LlamadaFuncion;
import ast.expressions.Variable;
import ast.statements.Invocacion;
import ast.types.TipoStruct;
import main.GestorErrores;
import semantico.context.ContextMap;
import visitor.DefaultVisitor;


public class Identificacion extends DefaultVisitor {
	
	private GestorErrores gestorErrores;
	//todos en la pila de variables
	private ContextMap<String, Definition> defVars;
	private ContextMap<String, DefFuncion> defFuncs;

	public Identificacion(GestorErrores gestor) {
		this.gestorErrores = gestor;
		defVars = new ContextMap<String,Definition>();
		defFuncs = new ContextMap<String,DefFuncion>();
	}	
	
	/*
	 * Poner aquí los visit necesarios.
	 * Si se ha usado VGen solo hay que copiarlos de la clase 'visitor/_PlantillaParaVisitors.txt'.
	 */

	// public Object visit(Programa prog, Object param) {
	// ...
	// }
	
	public Object visit(DefFuncion node, Object param) {
		
		if (defFuncs.getFromTop(node.getNombre()) != null) {
		    gestorErrores.error("Identificacion", "Funcion " + node.getNombre() + " ya definida", node.getStart());
		} else {
			defFuncs.put(node.getNombre(), node);
		}
		
		defVars.set();
		super.visit(node, param);
		defVars.reset();
		return null;
	}
	
	public Object visit(Invocacion node, Object param){
		DefFuncion def = defFuncs.getFromAny(node.getIdent());
		if (def == null)
			gestorErrores.error("Identificacion" , "Funcion " + node.getIdent() + " no definida", node.getStart());
		else
		    node.setDefFuncion(def);
		
		super.visit(node, param);
		return null;
	}
	
	public Object visit(LlamadaFuncion node, Object param){
		DefFuncion def = defFuncs.getFromAny(node.getIdent());
		if(def == null)
			gestorErrores.error("Identificación", "Función no definida", node.getStart());
		else			
			node.setDefFuncion(def);
		
		super.visit(node, param);
		return null;
	}
	
	
	public Object visit(DefVariable node, Object param){
		
		if(defVars.getFromTop(node.getNombre()) ==  null){
			defVars.put(node.getNombre(), node);
			super.visit(node, param);
		}
		else
			gestorErrores.error("Identificacion", node.getNombre() + " ya esta definida", node.getStart());
		
		return null;
	}
	
	public Object visit(Variable node, Object param){
		
		Definition def = defVars.getFromAny(node.getIdent());
		if (def == null)
			gestorErrores.error("Identificación", node.getIdent()+" variable no definida", node.getStart());
		else
		    node.setDefVariable(def);

		return null;
	}
	
	public Object visit(DefStruct node, Object param) {		
		if(defVars.getFromTop(node.getNombre()) == null) {
			defVars.put(node.getNombre(), node);
			super.visit(node, param);
		}
		else
			gestorErrores.error("Identificación", node.getNombre()+" ya definida", node.getStart());

		return null;
		
	}
	
	
	public Object visit(DefCampo node, Object param){
		
		if(defVars.getFromTop(node.getNombre()) == null) {
			defVars.put(node.getNombre(), node);
			super.visit(node, param);
		}
		else
			gestorErrores.error("Identificacion","el campo "+ node.getNombre()+" ya esta repetido", node.getStart());
			
		return null;
	}
	
	public Object visit(TipoStruct node, Object param) {
		
		Definition def = defVars.getFromAny(node.getValor());
		
		if(def == null)
			gestorErrores.error("Identidicacion", node.getValor()+" no definido", node.getStart());
		else
			node.setDefstruct(def);

		return null;
	}


	
}
