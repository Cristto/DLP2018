/**
 * @generated VGen 1.3.3
 */

package ast;

import java.util.List;

import visitor.Visitor;

//	defStruct:definition -> ident:String  campos:definition*

public class DefStruct extends AbstractDefinition {

	public DefStruct(String ident, List<Definition> campos) {
		super(new IdentTipo(ident));
		
		this.campos = campos;

		searchForPositions(campos);	// Obtener linea/columna a partir de los hijos
	}

	@SuppressWarnings("unchecked")
	public DefStruct(Object ident, Object campos) {
		super(new IdentTipo( (ident instanceof Token) ? ((Token)ident).getLexeme() : (String) ident));
		this.campos = (List<Definition>) campos;

		searchForPositions(ident, campos);	// Obtener linea/columna a partir de los hijos
	}

	public String getIdent() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
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

	private String ident;
	private List<Definition> campos;

}

