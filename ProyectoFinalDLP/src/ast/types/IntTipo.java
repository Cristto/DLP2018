/**
 * @generated VGen 1.3.3
 */

package ast.types;

import visitor.*;

//	intTipo:type -> 

public class IntTipo extends AbstractType {

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

}
