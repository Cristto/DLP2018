/**
 * @generated VGen 1.3.3
 */

package ast.definitions;

import ast.Token;
import ast.types.Tipo;
import visitor.*;

//	defVariable:definition -> ident:String  tipo:type

public class DefVariable extends AbstractDefinition {

	public DefVariable(String ident, Tipo tipo) {
	
		super(ident ,tipo);

		searchForPositions();	// Obtener linea/columna a partir de los hijos
	}

	public DefVariable(Object ident, Object tipo) {
		
		super((ident instanceof Token) ? ((Token) ident).getLexeme() : (String) ident, (Tipo) tipo);

		searchForPositions(ident, tipo);	// Obtener linea/columna a partir de los hijos
	}
	
	public int getAmbito() {
		return ambito;
	}
	public void setAmbito(int ambito) {
		this.ambito = ambito;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private int ambito;
}

