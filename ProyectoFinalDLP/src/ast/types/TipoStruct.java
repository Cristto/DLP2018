/**
 * @generated VGen 1.3.3
 */

package ast.types;

import ast.Token;
import ast.definitions.DefStruct;
import visitor.Visitor;

//	IdentTipo:type -> valor:String

public class TipoStruct extends AbstractType {

	public TipoStruct(String valor) {
		this.valor = valor;
	}

	public TipoStruct(Object valor) {
		this.valor = (valor instanceof Token) ? ((Token)valor).getLexeme() : (String) valor;

		searchForPositions(valor);	// Obtener linea/columna a partir de los hijos
	}

	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public DefStruct getDefstruct() {
		return defstruct;
	}
	
	public void setDefstruct(DefStruct defstruct) {
		this.defstruct = defstruct;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private String valor;
	private DefStruct defstruct;
}

