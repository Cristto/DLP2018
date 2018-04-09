/**
 * @generated VGen 1.3.3
 */

package ast.types;

import visitor.*;

//	intTipo:type -> 

public class IntTipo extends AbstractType {
	
private static IntTipo instance = null;
	
	private IntTipo() {
	}
	
	public static IntTipo getInstance() {
		if(instance == null)
			instance = new IntTipo();
		return instance;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

}

