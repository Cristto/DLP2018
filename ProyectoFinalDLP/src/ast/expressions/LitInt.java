/**
 * @generated VGen 1.3.3
 */

package ast.expressions;

import ast.Token;
import visitor.*;

//	litInt:expression -> valor:int

public class LitInt extends AbstractExpression {

	public LitInt(int valor) {
		this.valor = valor;
	}

	public LitInt(Object valor) {
		this.valor = (valor instanceof Token) ? Integer.parseInt(((Token)valor).getLexeme()) : (Integer) valor;

		searchForPositions(valor);	// Obtener linea/columna a partir de los hijos
	}

	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private int valor;
}

