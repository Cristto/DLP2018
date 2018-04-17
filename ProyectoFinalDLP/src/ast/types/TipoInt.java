/**
 * @generated VGen 1.3.3
 */

package ast.types;

import visitor.*;

//	intTipo:type -> 

public class TipoInt extends AbstractType {
	
private static TipoInt instance = null;
	
	private TipoInt() {
	}
	
	public static TipoInt getInstance() {
		if(instance == null)
			instance = new TipoInt();
		return instance;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	@Override
	public String getNombreMapl() {
		return "int";
	}

}

