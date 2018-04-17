/**
 * @generated VGen 1.3.3
 */

package ast.types;

import visitor.*;

//	charTipo:type -> 

public class TipoChar extends AbstractType {
	
	private static TipoChar instance = null;
	
	private TipoChar() {
	}
	
	public static TipoChar getInstance() {
		if(instance == null)
			instance = new TipoChar();
		return instance;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	@Override
	public String getNombreMapl() {
		// TODO Auto-generated method stub
		return "byte";
	}
	
	

}

