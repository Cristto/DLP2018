/**
 * @generated VGen 1.3.3
 */

package ast.statements;

import ast.expressions.Expression;
import visitor.*;

//	asignacion:statement -> leftExpr:expression  rightExpr:expression

public class Asignacion extends AbstractStatement {

	public Asignacion(Expression leftExpr, Expression rightExpr) {
		this.leftExpr = leftExpr;
		this.rightExpr = rightExpr;

		searchForPositions(leftExpr, rightExpr);	// Obtener linea/columna a partir de los hijos
	}

	public Asignacion(Object leftExpr, Object rightExpr) {
		this.leftExpr = (Expression) leftExpr;
		this.rightExpr = (Expression) rightExpr;

		searchForPositions(leftExpr, rightExpr);	// Obtener linea/columna a partir de los hijos
	}

	public Expression getLeftExpr() {
		return leftExpr;
	}
	public void setLeftExpr(Expression leftExpr) {
		this.leftExpr = leftExpr;
	}

	public Expression getRightExpr() {
		return rightExpr;
	}
	public void setRightExpr(Expression rightExpr) {
		this.rightExpr = rightExpr;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private Expression leftExpr;
	private Expression rightExpr;
}

