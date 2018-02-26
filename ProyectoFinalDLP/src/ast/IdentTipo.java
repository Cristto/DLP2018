/**
 * @generated VGen 1.3.3
 */

package ast;

import visitor.*;

//	IdentTipo:type -> valor:String

public class IdentTipo extends AbstractType {

	public IdentTipo(String valor) {
		this.valor = valor;
	}

	public IdentTipo(Object valor) {
		this.valor = (valor instanceof Token) ? ((Token)valor).getLexeme() : (String) valor;

		searchForPositions(valor);	// Obtener linea/columna a partir de los hijos
	}

	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private String valor;
}

