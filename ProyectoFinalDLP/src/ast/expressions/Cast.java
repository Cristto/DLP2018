/**
 * @generated VGen 1.3.3
 */

package ast.expressions;

import ast.types.Type;
import visitor.*;

//	cast:expression -> tipo:type  expresion:expression

public class Cast extends AbstractExpression {

	public Cast(Type tipo, Expression expresion) {
		this.tipoDinamico = tipo;
		this.expresion = expresion;

		searchForPositions(tipo, expresion);	// Obtener linea/columna a partir de los hijos
	}

	public Cast(Object tipo, Object expresion) {
		this.tipoDinamico = (Type) tipo;
		this.expresion = (Expression) expresion;

		searchForPositions(tipo, expresion);	// Obtener linea/columna a partir de los hijos
	}
	
	public Type getTipoDinamico() {
		return tipoDinamico;
	}
	
	public void setTipoDinamico(Type tipoDinamico) {
		this.tipoDinamico = tipoDinamico;
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

	private Type tipoDinamico;
	private Expression expresion;
}

