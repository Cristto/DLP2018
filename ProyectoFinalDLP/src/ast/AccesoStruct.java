/**
 * @generated VGen 1.3.3
 */

package ast;

import visitor.*;

//	accesoStruct:expression -> ident:expression  campo:String

public class AccesoStruct extends AbstractExpression {

	public AccesoStruct(Expression ident, String campo) {
		this.ident = ident;
		this.campo = campo;

		searchForPositions(ident);	// Obtener linea/columna a partir de los hijos
	}

	public AccesoStruct(Object ident, Object campo) {
		this.ident = (Expression) ident;
		this.campo = (campo instanceof Token) ? ((Token)campo).getLexeme() : (String) campo;

		searchForPositions(ident, campo);	// Obtener linea/columna a partir de los hijos
	}

	public Expression getIdent() {
		return ident;
	}
	public void setIdent(Expression ident) {
		this.ident = ident;
	}

	public String getCampo() {
		return campo;
	}
	public void setCampo(String campo) {
		this.campo = campo;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private Expression ident;
	private String campo;
}

