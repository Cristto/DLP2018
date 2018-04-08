/**
 * @generated VGen 1.3.3
 */

package ast.types;

import visitor.*;

//	charTipo:type -> 

public class CharTipo extends AbstractType {

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

}

