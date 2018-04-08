/**
 * @generated VGen 1.3.3
 */

package ast.definitions;

import java.util.List;

import ast.Token;
import ast.types.Type;
import visitor.Visitor;

//	defStruct:definition -> ident:String  campos:definition*

public class DefStruct extends AbstractDefinition {

	public DefStruct(String ident, Type tipo, List<Definition> campos) {
		super(ident, tipo);
		
		this.campos = campos;

		searchForPositions(campos);	// Obtener linea/columna a partir de los hijos
	}

	@SuppressWarnings("unchecked")
	public DefStruct(Object ident, Object tipo, Object campos) {
		super((ident instanceof Token) ? ((Token) ident).getLexeme() : (String) ident, (Type) tipo);
		this.campos = (List<Definition>) campos;

		searchForPositions(ident, campos);	// Obtener linea/columna a partir de los hijos
	}

	public List<Definition> getCampos() {
		return campos;
	}
	public void setCampos(List<Definition> campos) {
		this.campos = campos;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private List<Definition> campos;

}

