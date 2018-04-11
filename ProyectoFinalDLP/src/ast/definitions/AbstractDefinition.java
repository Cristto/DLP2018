/**
 * @generated VGen 1.3.3
 */

package ast.definitions;

import ast.AbstractTraceable;
import ast.types.Tipo;

public abstract class AbstractDefinition extends AbstractTraceable implements Definition {

	private String nombre;
	private Tipo tipo;

	protected AbstractDefinition(String nombre, Tipo tipo) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
	}

	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public Tipo getTipo() {
		return tipo;
	}
}
