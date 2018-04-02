/**
 * @generated VGen 1.3.3
 */

package ast;

public abstract class AbstractExpression extends AbstractTraceable implements Expression {

	private boolean lvalue;
	private Type tipo;
	
	@Override
    public boolean isLvalue() {
	return lvalue;
    }

    @Override
    public void setLvalue(boolean lvalue) {
	this.lvalue = lvalue;
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

