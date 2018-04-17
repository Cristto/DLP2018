/**
 * @generated VGen 1.3.3
 */

package ast.types;

import ast.Token;
import visitor.*;

//	arrayTipo:type -> size:int  tipo:type

public class TipoArray extends AbstractType {

	public TipoArray(int indice, Tipo tipo) {
		this.size = indice;
		this.tipo = tipo;

		searchForPositions(tipo);	// Obtener linea/columna a partir de los hijos
	}

	public TipoArray(Object size, Object tipo) {
		this.size = (size instanceof Token) ? Integer.parseInt(((Token)size).getLexeme()) : (Integer) size;
		this.tipo = (Tipo) tipo;

		searchForPositions(size, tipo);	// Obtener linea/columna a partir de los hijos
	}

	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}

	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private int size;
	private Tipo tipo;
	@Override
	public String getNombreMapl() {
		return size + "*" + tipo.getNombreMapl();
	}
}

