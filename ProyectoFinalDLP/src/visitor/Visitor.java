/**
 * @generated VGen 1.3.3
 */

package visitor;

import ast.*;

public interface Visitor {
	public Object visit(Program node, Object param);
	public Object visit(DefVariable node, Object param);
	public Object visit(DefFuncion node, Object param);
	public Object visit(DefStruct node, Object param);
	public Object visit(DefCampo node, Object param);
	public Object visit(IfElse node, Object param);
	public Object visit(While node, Object param);
	public Object visit(Read node, Object param);
	public Object visit(Print node, Object param);
	public Object visit(Printsp node, Object param);
	public Object visit(Println node, Object param);
	public Object visit(Asignacion node, Object param);
	public Object visit(Return node, Object param);
	public Object visit(Invocacion node, Object param);
	public Object visit(ExpresionAritmetica node, Object param);
	public Object visit(Comparacion node, Object param);
	public Object visit(ExpresionLogica node, Object param);
	public Object visit(MenosUnario node, Object param);
	public Object visit(Not node, Object param);
	public Object visit(AccesoArray node, Object param);
	public Object visit(AccesoStruct node, Object param);
	public Object visit(Variable node, Object param);
	public Object visit(LlamadaFuncion node, Object param);
	public Object visit(Cast node, Object param);
	public Object visit(LitInt node, Object param);
	public Object visit(LitReal node, Object param);
	public Object visit(LitChar node, Object param);
	public Object visit(IntTipo node, Object param);
	public Object visit(RealTipo node, Object param);
	public Object visit(CharTipo node, Object param);
	public Object visit(IdentTipo node, Object param);
	public Object visit(ArrayTipo node, Object param);
}
