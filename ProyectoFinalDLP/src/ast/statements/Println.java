/**
 * @generated VGen 1.3.3
 */

package ast.statements;

import ast.expressions.Expression;
import visitor.*;

//	println:statement -> expresion:expression

public class Println extends AbstractStatement {

	public Println(Expression expresion) {
		this.expresion = expresion;

		searchForPositions(expresion);	// Obtener linea/columna a partir de los hijos
	}

	public Println(Object expresion) {
		this.expresion = (Expression) expresion;

		searchForPositions(expresion);	// Obtener linea/columna a partir de los hijos
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

	private Expression expresion;
}

