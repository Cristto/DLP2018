/**
 * @generated VGen 1.3.3
 */

package ast;

import visitor.*;

//	voidTipo:type -> 

public class VoidTipo extends AbstractType {
	
	public VoidTipo(){
		
		searchForPositions();
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

}

