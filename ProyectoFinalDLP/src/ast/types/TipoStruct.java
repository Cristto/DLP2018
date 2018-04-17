/**
 * @generated VGen 1.3.3
 */

package ast.types;

import ast.Token;
import ast.definitions.Definition;
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
	
	public Definition getDefstruct() {
		return defstruct;
	}
	
	public void setDefstruct(Definition def) {
		this.defstruct = def;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private String valor;
	private Definition defstruct;
	
	
	@Override
	public String getNombreMapl() {
		// TODO Auto-generated method stub
		return null;
	}
}

