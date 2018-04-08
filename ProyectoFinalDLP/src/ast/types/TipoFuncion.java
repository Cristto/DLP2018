/**
 * @generated VGen 1.3.3
 */

package ast.types;

import visitor.*;

//	funcionTipo:type -> tipoRetorno:type

public class TipoFuncion extends AbstractType {

	public TipoFuncion(Type tipoRetorno) {
		this.tipoRetorno = tipoRetorno;

		searchForPositions(tipoRetorno);	// Obtener linea/columna a partir de los hijos
	}

	public TipoFuncion(Object tipoRetorno) {
		this.tipoRetorno = (Type) tipoRetorno;

		searchForPositions(tipoRetorno);	// Obtener linea/columna a partir de los hijos
	}

	public Type getTipoRetorno() {
		return tipoRetorno;
	}
	public void setTipoRetorno(Type tipoRetorno) {
		this.tipoRetorno = tipoRetorno;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private Type tipoRetorno;
}

