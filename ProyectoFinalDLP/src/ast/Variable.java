/**
 * @generated VGen 1.3.3
 */

package ast;

import visitor.*;

//	variable:expression -> ident:String

public class Variable extends AbstractExpression {

	public Variable(String ident) {
		this.ident = ident;
	}

	public Variable(Object ident) {
		this.ident = (ident instanceof Token) ? ((Token)ident).getLexeme() : (String) ident;

		searchForPositions(ident);	// Obtener linea/columna a partir de los hijos
	}

	public String getIdent() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
	}
	
	public DefVariable getDefVariable() {
		return defVariable;
	}
	
	public void setDefVariable(DefVariable defVariable) {
		this.defVariable = defVariable;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private String ident;
	private DefVariable defVariable;
}

