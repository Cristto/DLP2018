/**
 * @generated VGen 1.3.3
 */

package ast;

import visitor.*;

//	defCampo:definition -> ident:String  tipo:type

public class DefCampo extends AbstractDefinition {

	public DefCampo(String ident, Type tipo) {
		this.ident = ident;
		this.tipo = tipo;

		searchForPositions(tipo);	// Obtener linea/columna a partir de los hijos
	}

	public DefCampo(Object ident, Object tipo) {
		this.ident = (ident instanceof Token) ? ((Token)ident).getLexeme() : (String) ident;
		this.tipo = (Type) tipo;

		searchForPositions(ident, tipo);	// Obtener linea/columna a partir de los hijos
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

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private String ident;
	private Type tipo;
}

