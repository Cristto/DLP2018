/**
 * @generated VGen 1.3.3
 */

package ast.statements;

import java.util.*;

import ast.Token;
import ast.definitions.DefFuncion;
import ast.expressions.Expression;
import visitor.*;

//	invocacion:statement -> ident:String  argumentos:expression*

public class Invocacion extends AbstractStatement {

	public Invocacion(String ident, List<Expression> argumentos) {
		this.ident = ident;
		this.argumentos = argumentos;

		searchForPositions(argumentos);	// Obtener linea/columna a partir de los hijos
	}

	@SuppressWarnings("unchecked")
	public Invocacion(Object ident, Object argumentos) {
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
	
	public DefFuncion getDefFuncion() {
		return defFuncion;
	}
	
	public void setDefFuncion(DefFuncion defFuncion) {
		this.defFuncion = defFuncion;
	}

	public List<Expression> getArgumentos() {
		return argumentos;
	}
	public void setArgumentos(List<Expression> argumentos) {
		this.argumentos = argumentos;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private String ident;
	private List<Expression> argumentos;
	private DefFuncion defFuncion;
}

