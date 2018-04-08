/**
 * @generated VGen 1.3.3
 */

package ast.expressions;

import visitor.*;

//	accesoArray:expression -> ident:expression  index:expression

public class AccesoArray extends AbstractExpression {

	public AccesoArray(Expression ident, Expression index) {
		this.ident = ident;
		this.index = index;

		searchForPositions(ident, index);	// Obtener linea/columna a partir de los hijos
	}

	public AccesoArray(Object ident, Object index) {
		this.ident = (Expression) ident;
		this.index = (Expression) index;

		searchForPositions(ident, index);	// Obtener linea/columna a partir de los hijos
	}

	public Expression getIdent() {
		return ident;
	}
	public void setIdent(Expression ident) {
		this.ident = ident;
	}

	public Expression getIndex() {
		return index;
	}
	public void setIndex(Expression index) {
		this.index = index;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private Expression ident;
	private Expression index;
}

