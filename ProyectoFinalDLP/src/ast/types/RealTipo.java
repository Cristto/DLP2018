/**
 * @generated VGen 1.3.3
 */

package ast.types;

import visitor.*;

//	realTipo:type -> 

public class RealTipo extends AbstractType {
	
private static RealTipo instance = null;
	
	private RealTipo() {
	}
	
	public static RealTipo getInstance() {
		if(instance == null)
			instance = new RealTipo();
		return instance;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

}

