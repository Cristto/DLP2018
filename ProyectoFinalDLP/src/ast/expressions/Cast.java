/**
 * @generated VGen 1.3.3
 */

package ast.expressions;

import ast.types.Tipo;
import visitor.*;

//	cast:expression -> tipo:type  expresion:expression

public class Cast extends AbstractExpression {

	public Cast(Tipo tipo, Expression expresion) {
		this.tipoDinamico = tipo;
		this.expresion = expresion;

		searchForPositions(tipo, expresion);	// Obtener linea/columna a partir de los hijos
	}

	public Cast(Object tipo, Object expresion) {
		this.tipoDinamico = (Tipo) tipo;
		this.expresion = (Expression) expresion;

		searchForPositions(tipo, expresion);	// Obtener linea/columna a partir de los hijos
	}
	
	public Tipo getTipoDinamico() {
		return tipoDinamico;
	}
	
	public void setTipoDinamico(Tipo tipoDinamico) {
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

	private Tipo tipoDinamico;
	private Expression expresion;
}

