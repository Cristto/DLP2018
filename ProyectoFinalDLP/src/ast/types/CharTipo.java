/**
 * @generated VGen 1.3.3
 */

package ast.types;

import visitor.*;

//	charTipo:type -> 

public class CharTipo extends AbstractType {
	
	private static CharTipo instance = null;
	
	private CharTipo() {
	}
	
	public static CharTipo getInstance() {
		if(instance == null)
			instance = new CharTipo();
		return instance;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

}

