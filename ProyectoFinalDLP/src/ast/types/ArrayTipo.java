/**
 * @generated VGen 1.3.3
 */

package ast.types;

import ast.Token;
import visitor.*;

//	arrayTipo:type -> size:int  tipo:type

public class ArrayTipo extends AbstractType {

	public ArrayTipo(int size, Type tipo) {
		this.size = size;
		this.tipo = tipo;

		searchForPositions(tipo);	// Obtener linea/columna a partir de los hijos
	}

	public ArrayTipo(Object size, Object tipo) {
		this.size = (size instanceof Token) ? Integer.parseInt(((Token)size).getLexeme()) : (Integer) size;
		this.tipo = (Type) tipo;

		searchForPositions(size, tipo);	// Obtener linea/columna a partir de los hijos
	}

	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
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

	private int size;
	private Type tipo;
}
