/**
 * @generated VGen 1.3.3
 */

package visitor;

import ast.*;
import ast.definitions.DefCampo;
import ast.definitions.DefFuncion;
import ast.definitions.DefStruct;
import ast.definitions.DefVariable;
import ast.expressions.AccesoArray;
import ast.expressions.AccesoStruct;
import ast.expressions.Cast;
import ast.expressions.Comparacion;
import ast.expressions.ExpresionAritmetica;
import ast.expressions.ExpresionLogica;
import ast.expressions.LitChar;
import ast.expressions.LitInt;
import ast.expressions.LitReal;
import ast.expressions.LlamadaFuncion;
import ast.expressions.MenosUnario;
import ast.expressions.Not;
import ast.expressions.Variable;
import ast.statements.Asignacion;
import ast.statements.IfElse;
import ast.statements.Invocacion;
import ast.statements.Print;
import ast.statements.Println;
import ast.statements.Printsp;
import ast.statements.Read;
import ast.statements.Return;
import ast.statements.While;
import ast.types.TipoArray;
import ast.types.CharTipo;
import ast.types.TipoFuncion;
import ast.types.TipoStruct;
import ast.types.IntTipo;
import ast.types.RealTipo;
import ast.types.TipoVoid;

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
	public Object visit(TipoStruct node, Object param);
	public Object visit(TipoArray node, Object param);
	public Object visit(TipoFuncion node, Object param);
	public Object visit(TipoVoid node, Object param);
}
