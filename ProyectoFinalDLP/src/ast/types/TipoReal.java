/**
 * @generated VGen 1.3.3
 */

package ast.types;

import visitor.*;

//	realTipo:type -> 

public class TipoReal extends AbstractType {
	
private static TipoReal instance = null;
	
	private TipoReal() {
	}
	
	public static TipoReal getInstance() {
		if(instance == null)
			instance = new TipoReal();
		return instance;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	@Override
	public String getNombreMapl() {
		return "double";
	}

}

