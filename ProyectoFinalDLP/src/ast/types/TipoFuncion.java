/**
 * @generated VGen 1.3.3
 */

package ast.types;

import java.util.List;

import ast.definitions.Definition;
import visitor.*;

//	funcionTipo:type -> tipoRetorno:type

public class TipoFuncion extends AbstractType {

	public TipoFuncion(Tipo tipoRetorno, List<Definition> defParametros) {
		this.tipoRetorno = tipoRetorno;
		this.defParametros = defParametros;

		searchForPositions(tipoRetorno);	// Obtener linea/columna a partir de los hijos
	}

	@SuppressWarnings("unchecked")
	public TipoFuncion(Object tipoRetorno, Object defParametros) {
		this.tipoRetorno = (Tipo) tipoRetorno;
		this.defParametros = (List<Definition>) defParametros;

		searchForPositions(tipoRetorno);	// Obtener linea/columna a partir de los hijos
	}

	public Tipo getTipoRetorno() {
		return tipoRetorno;
	}
	public void setTipoRetorno(Tipo tipoRetorno) {
		this.tipoRetorno = tipoRetorno;
	}
	public List<Definition> getDefParametros() {
		return defParametros;
	}
	public void setDefParametros(List<Definition> defParametros) {
		this.defParametros = defParametros;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private Tipo tipoRetorno;
	private List<Definition> defParametros;
}

