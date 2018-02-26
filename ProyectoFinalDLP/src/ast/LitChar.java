/**
 * @generated VGen 1.3.3
 */

package ast;

import visitor.*;

//	litChar:expression -> valor:char

public class LitChar extends AbstractExpression {

	public LitChar(char valor) {
		this.valor = valor;
	}

	public LitChar(Object valor) {
		this.valor = (valor instanceof Token) ? ((Token)valor).getLexeme().charAt(0) : (Character) valor;

		searchForPositions(valor);	// Obtener linea/columna a partir de los hijos
	}

	public char getValor() {
		return valor;
	}
	public void setValor(char valor) {
		this.valor = valor;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private char valor;
}

