/**
 * @generated VGen 1.3.3
 */

package ast;

import visitor.*;

//	not:expression -> operador:String  expresion:expression

public class Not extends AbstractExpression {

	public Not(String operador, Expression expresion) {
		this.operador = operador;
		this.expresion = expresion;

		searchForPositions(expresion);	// Obtener linea/columna a partir de los hijos
	}

	public Not(Object operador, Object expresion) {
		this.operador = (operador instanceof Token) ? ((Token)operador).getLexeme() : (String) operador;
		this.expresion = (Expression) expresion;

		searchForPositions(operador, expresion);	// Obtener linea/columna a partir de los hijos
	}

	public String getOperador() {
		return operador;
	}
	public void setOperador(String operador) {
		this.operador = operador;
	}

	public Expression getExpresion() {
		return expresion;
	}
	public void setExpresion(Expression expresion) {
		this.expresion = expresion;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private String operador;
	private Expression expresion;
}

