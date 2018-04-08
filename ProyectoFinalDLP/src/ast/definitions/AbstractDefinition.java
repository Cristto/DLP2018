/**
 * @generated VGen 1.3.3
 */

package ast.definitions;

import ast.AbstractTraceable;
import ast.types.Type;

public abstract class AbstractDefinition extends AbstractTraceable implements Definition {

	private String nombre;
	private Type tipo;

	protected AbstractDefinition(String nombre, Type tipo) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
	}

	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public Type getTipo() {
		return tipo;
	}
}
