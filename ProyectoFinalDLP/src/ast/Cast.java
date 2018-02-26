/**
 * @generated VGen 1.3.3
 */

package ast;

import visitor.*;

//	cast:expression -> tipo:type  expresion:expression

public class Cast extends AbstractExpression {

	public Cast(Type tipo, Expression expresion) {
		this.tipo = tipo;
		this.expresion = expresion;

		searchForPositions(tipo, expresion);	// Obtener linea/columna a partir de los hijos
	}

	public Cast(Object tipo, Object expresion) {
		this.tipo = (Type) tipo;
		this.expresion = (Expression) expresion;

		searchForPositions(tipo, expresion);	// Obtener linea/columna a partir de los hijos
	}

	public Type getTipo() {
		return tipo;
	}
	public void setTipo(Type tipo) {
		this.tipo = tipo;
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

	private Type tipo;
	private Expression expresion;
}

