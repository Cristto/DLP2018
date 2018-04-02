/**
 * @generated VGen 1.3.3
 */

package ast;

import java.util.*;
import visitor.*;

//	defFuncion:definition -> ident:String  tipo:type  defParametros:definition*  defVarLocal:definition*  sentencias:statement*

public class DefFuncion extends AbstractDefinition {

	public DefFuncion(String ident, Type tipo, List<DefVariable> defParametros, List<DefVariable> defVarLocal, List<Statement> sentencias) {
		this.tipo = tipo;
		this.ident = ident;
		this.defParametros = defParametros;
		this.defVarLocal = defVarLocal;
		this.sentencias = sentencias;

		searchForPositions(tipo, defParametros, defVarLocal, sentencias);	// Obtener linea/columna a partir de los hijos
	}

	@SuppressWarnings("unchecked")
	public DefFuncion(Object ident, Object tipo, Object defParametros, Object defVarLocal, Object sentencias) {
		this.tipo = (Type) tipo;
		this.ident = (ident instanceof Token) ? ((Token)ident).getLexeme() : (String) ident;
		this.defParametros = (List<DefVariable>) defParametros;
		this.defVarLocal = (List<DefVariable>) defVarLocal;
		this.sentencias = (List<Statement>) sentencias;

		searchForPositions(ident, tipo, defParametros, defVarLocal, sentencias);	// Obtener linea/columna a partir de los hijos
	}

	public String getIdent() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
	}

	public Type getTipo() {
		return tipo;
	}
	public void setTipo(Type tipo) {
		this.tipo = tipo;
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

	private String ident;
	private Type tipo;
	private List<DefVariable> defParametros;
	private List<DefVariable> defVarLocal;
	private List<Statement> sentencias;
}

