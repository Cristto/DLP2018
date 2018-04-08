/**
 * @generated VGen 1.3.3
 */

package ast.expressions;

import java.util.*;

import ast.Token;
import ast.definitions.DefFuncion;
import visitor.*;

//	llamadaFuncion:expression -> ident:String  argumentos:expression*

public class LlamadaFuncion extends AbstractExpression {

	public LlamadaFuncion(String ident, List<Expression> argumentos) {
		this.ident = ident;
		this.argumentos = argumentos;

		searchForPositions(argumentos);	// Obtener linea/columna a partir de los hijos
	}

	@SuppressWarnings("unchecked")
	public LlamadaFuncion(Object ident, Object argumentos) {
		this.ident = (ident instanceof Token) ? ((Token)ident).getLexeme() : (String) ident;
		this.argumentos = (List<Expression>) argumentos;

		searchForPositions(ident, argumentos);	// Obtener linea/columna a partir de los hijos
	}

	public String getIdent() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
	}

	public List<Expression> getArgumentos() {
		return argumentos;
	}
	public void setArgumentos(List<Expression> argumentos) {
		this.argumentos = argumentos;
	}
	
	public DefFuncion getDefFuncion() {
		return defFuncion;
	}
	public void setDefFuncion(DefFuncion defFuncion) {
		this.defFuncion = defFuncion;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private String ident;
	private List<Expression> argumentos;
	private DefFuncion defFuncion;
}

