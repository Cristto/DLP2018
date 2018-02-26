/**
 * @generated VGen 1.3.3
 */

package ast;

import visitor.*;

//	realTipo:type -> 

public class RealTipo extends AbstractType {

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

}

