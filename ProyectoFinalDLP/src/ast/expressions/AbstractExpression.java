/**
 * @generated VGen 1.3.3
 */

package ast.expressions;

import ast.AbstractTraceable;
import ast.types.Tipo;

public abstract class AbstractExpression extends AbstractTraceable implements Expression {

	private boolean lvalue;
	private Tipo tipo;
	
	@Override
    public boolean isLvalue() {
	return lvalue;
    }

    @Override
    public void setLvalue(boolean lvalue) {
	this.lvalue = lvalue;
    }
    
    @Override
    public Tipo getTipo() {
    	return tipo;
    }
    
    @Override
    public void setTipo(Tipo tipo) {
    	this.tipo = tipo;
    	
    }
	
    
}

