/**
 * @generated VGen 1.3.3
 */

package ast;

public abstract class AbstractDefinition extends AbstractTraceable implements Definition {
	
	private Type tipo;
	
	public AbstractDefinition(Type tipo) {
		this.tipo = tipo;
	
	    }
	
	@Override
    public Type getTipo() {
	return tipo;
    }
	
	 @Override
	    public void setTipo(Type tipo) {
		this.tipo = tipo;

	    }

}

