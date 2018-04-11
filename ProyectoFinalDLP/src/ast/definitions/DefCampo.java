/**
 * @generated VGen 1.3.3
 */

package ast.definitions;

import ast.Token;
import ast.types.Tipo;
import visitor.*;

//	defCampo:definition -> ident:String  tipo:type

public class DefCampo extends AbstractDefinition {

	public DefCampo(String ident, Tipo tipo) {
		super(ident, tipo);

		searchForPositions(tipo); // Obtener linea/columna a partir de los hijos
	}

	public DefCampo(Object ident, Object tipo) {
		super((ident instanceof Token) ? ((Token) ident).getLexeme() : (String) ident, (Tipo) tipo);

		searchForPositions(ident, tipo); // Obtener linea/columna a partir de
											// los hijos
	}

	@Override
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}
}
