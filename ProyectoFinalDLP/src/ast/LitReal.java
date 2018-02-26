/**
 * @generated VGen 1.3.3
 */

package ast;

import visitor.*;

//	litReal:expression -> valor:float

public class LitReal extends AbstractExpression {

	public LitReal(float valor) {
		this.valor = valor;
	}

	public LitReal(Object valor) {
		this.valor = (valor instanceof Token) ? Float.parseFloat(((Token)valor).getLexeme()) : (Float) valor;

		searchForPositions(valor);	// Obtener linea/columna a partir de los hijos
	}

	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private float valor;
}

