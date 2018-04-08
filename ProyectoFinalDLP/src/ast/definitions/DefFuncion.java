/**
 * @generated VGen 1.3.3
 */

package ast.definitions;

import java.util.*;

import ast.Token;
import ast.statements.Statement;
import ast.types.Type;
import visitor.*;

//	defFuncion:definition -> ident:String  tipo:type  defParametros:definition*  defVarLocal:definition*  sentencias:statement*

public class DefFuncion extends AbstractDefinition {

	public DefFuncion(String ident, Type retorno, List<DefVariable> defParametros, List<DefVariable> defVarLocal, List<Statement> sentencias) {

		super(ident, retorno);
		this.defParametros = defParametros;
		this.defVarLocal = defVarLocal;
		this.sentencias = sentencias;

		searchForPositions(retorno, defParametros, defVarLocal, sentencias);	// Obtener linea/columna a partir de los hijos
	}

	@SuppressWarnings("unchecked")
	public DefFuncion(Object ident, Object tipo, Object defParametros, Object defVarLocal, Object sentencias) {
		super((ident instanceof Token) ? ((Token) ident).getLexeme() : (String) ident, (Type) tipo);
		this.defParametros = (List<DefVariable>) defParametros;
		this.defVarLocal = (List<DefVariable>) defVarLocal;
		this.sentencias = (List<Statement>) sentencias;

		searchForPositions(ident, tipo, defParametros, defVarLocal, sentencias);	// Obtener linea/columna a partir de los hijos
	}

	public List<DefVariable> getDefParametros() {
		return defParametros;
	}
	public void setDefParametros(List<DefVariable> defParametros) {
		this.defParametros = defParametros;
	}

	public List<DefVariable> getDefVarLocal() {
		return defVarLocal;
	}
	public void setDefVarLocal(List<DefVariable> defVarLocal) {
		this.defVarLocal = defVarLocal;
	}

	public List<Statement> getSentencias() {
		return sentencias;
	}
	public void setSentencias(List<Statement> sentencias) {
		this.sentencias = sentencias;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private List<DefVariable> defParametros;
	private List<DefVariable> defVarLocal;
	private List<Statement> sentencias;
}

