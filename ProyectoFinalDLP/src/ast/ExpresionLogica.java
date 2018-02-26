/**
 * @generated VGen 1.3.3
 */

package ast;

import visitor.*;

//	expresionLogica:expression -> leftExpr:expression  operador:String  rightExpr:expression

public class ExpresionLogica extends AbstractExpression {

	public ExpresionLogica(Expression leftExpr, String operador, Expression rightExpr) {
		this.leftExpr = leftExpr;
		this.operador = operador;
		this.rightExpr = rightExpr;

		searchForPositions(leftExpr, rightExpr);	// Obtener linea/columna a partir de los hijos
	}

	public ExpresionLogica(Object leftExpr, Object operador, Object rightExpr) {
		this.leftExpr = (Expression) leftExpr;
		this.operador = (operador instanceof Token) ? ((Token)operador).getLexeme() : (String) operador;
		this.rightExpr = (Expression) rightExpr;

		searchForPositions(leftExpr, operador, rightExpr);	// Obtener linea/columna a partir de los hijos
	}

	public Expression getLeftExpr() {
		return leftExpr;
	}
	public void setLeftExpr(Expression leftExpr) {
		this.leftExpr = leftExpr;
	}

	public String getOperador() {
		return operador;
	}
	public void setOperador(String operador) {
		this.operador = operador;
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
	private String operador;
	private Expression rightExpr;
}

