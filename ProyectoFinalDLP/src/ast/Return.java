/**
 * @generated VGen 1.3.3
 */

package ast;

import visitor.Visitor;

//	return:statement -> expresion:expression

public class Return extends AbstractStatement {

	public Return(Expression expresion) {
		this.expresion = expresion;

		searchForPositions(expresion);	// Obtener linea/columna a partir de los hijos
	}
	
	public Return() {
		

		searchForPositions();	// Obtener linea/columna a partir de los hijos
	}

	public Return(Object expresion) {
		this.expresion = (Expression) expresion;

		searchForPositions(expresion);	// Obtener linea/columna a partir de los hijos
	}

	public Expression getExpresion() {
		return expresion;
	}
	public void setExpresion(Expression expresion) {
		this.expresion = expresion;
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

	private Expression expresion;
	private Type tipo;
}

