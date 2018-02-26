/**
 * @generated VGen 1.3.3
 */

package ast;

import java.util.*;
import visitor.*;

//	defFuncion:definition -> ident:String  tipo:type  defParametros:definition*  defVarLocal:definition*  sentencias:statement*

public class DefFuncion extends AbstractDefinition {

	public DefFuncion(String ident, Type tipo, List<Definition> defParametros, List<Definition> defVarLocal, List<Statement> sentencias) {
		this.ident = ident;
		this.tipo = tipo;
		this.defParametros = defParametros;
		this.defVarLocal = defVarLocal;
		this.sentencias = sentencias;

		searchForPositions(tipo, defParametros, defVarLocal, sentencias);	// Obtener linea/columna a partir de los hijos
	}

	@SuppressWarnings("unchecked")
	public DefFuncion(Object ident, Object tipo, Object defParametros, Object defVarLocal, Object sentencias) {
		this.ident = (ident instanceof Token) ? ((Token)ident).getLexeme() : (String) ident;
		this.tipo = (Type) tipo;
		this.defParametros = (List<Definition>) defParametros;
		this.defVarLocal = (List<Definition>) defVarLocal;
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

	public List<Definition> getDefParametros() {
		return defParametros;
	}
	public void setDefParametros(List<Definition> defParametros) {
		this.defParametros = defParametros;
	}

	public List<Definition> getDefVarLocal() {
		return defVarLocal;
	}
	public void setDefVarLocal(List<Definition> defVarLocal) {
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
	private List<Definition> defParametros;
	private List<Definition> defVarLocal;
	private List<Statement> sentencias;
}

