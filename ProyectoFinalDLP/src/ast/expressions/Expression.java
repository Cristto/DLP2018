/**
 * @generated VGen 1.3.3
 */

package ast.expressions;

import ast.AST;
import ast.types.Tipo;

public interface Expression extends AST {

	boolean isLvalue();

	void setLvalue(boolean lvalue);
	
	public Tipo getTipo();
	public void setTipo(Tipo tipo);

}

