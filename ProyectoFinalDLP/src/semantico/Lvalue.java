package semantico;

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
import visitor.DefaultVisitor;

public class Lvalue extends DefaultVisitor{
	
	 @Override
	    public Object visit(AccesoArray node, Object param) {
		node.setLvalue(true);
		return super.visit(node, param);
	    }

	    @Override
	    public Object visit(AccesoStruct node, Object param) {
		node.setLvalue(true);
		return super.visit(node, param);
	    }

	    @Override
	    public Object visit(Variable node, Object param) {
		node.setLvalue(true);
		return super.visit(node, param);
	    }

	    @Override
	    public Object visit(MenosUnario node, Object param) {
		node.setLvalue(false);
		return super.visit(node, param);
	    }

	    @Override
	    public Object visit(ExpresionAritmetica node, Object param) {
		node.setLvalue(false);
		return super.visit(node, param);
	    }

	    @Override
	    public Object visit(Comparacion node, Object param) {
		node.setLvalue(false);
		return super.visit(node, param);
	    }

	    @Override
	    public Object visit(Not node, Object param) {
		node.setLvalue(false);
		return super.visit(node, param);
	    }

	    @Override
	    public Object visit(ExpresionLogica node, Object param) {
		node.setLvalue(false);
		return super.visit(node, param);
	    }

	    @Override
	    public Object visit(Cast node, Object param) {
		node.setLvalue(false);
		return super.visit(node, param);
	    }

	    public Object visit(LlamadaFuncion node, Object param){
	    node.setLvalue(false);
	    return super.visit(node, param);
	    }

	    @Override
	    public Object visit(LitReal node, Object param) {
		node.setLvalue(false);
		return super.visit(node, param);
	    }

	    @Override
	    public Object visit(LitInt node, Object param) {
		node.setLvalue(false);
		return super.visit(node, param);
	    }

	    @Override
	    public Object visit(LitChar node, Object param) {
		node.setLvalue(false);
		return super.visit(node, param);
	    }


}
