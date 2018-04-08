/**
 * @generated VGen 1.3.3
 */

package ast.expressions;

import ast.AST;
import ast.types.Type;

public interface Expression extends AST {

	boolean isLvalue();

	void setLvalue(boolean lvalue);
	
	public Type getTipo();
	public void setTipo(Type tipo);

}

