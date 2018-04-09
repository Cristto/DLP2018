/**
 * @generated VGen 1.3.3
 */

package ast.types;

import visitor.*;

//	voidTipo:type -> 

public class TipoVoid extends AbstractType {
	
	public TipoVoid(){
		
		searchForPositions();
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

}

