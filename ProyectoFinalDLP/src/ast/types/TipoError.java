package ast.types;

import ast.Position;
import visitor.Visitor;

public class TipoError implements Tipo{

	@Override
	public Object accept(Visitor visitor, Object param) {
	
		return null;
	}

	@Override
	public Position getStart() {
		
		return null;
	}

	@Override
	public Position getEnd() {
		
		return null;
	}

	@Override
	public String getNombreMapl() {
		// TODO Auto-generated method stub
		return null;
	}

}
