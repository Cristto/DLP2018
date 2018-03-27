package semantico;

import ast.AccesoArray;
import ast.AccesoStruct;
import ast.Cast;
import ast.Comparacion;
import ast.ExpresionAritmetica;
import ast.ExpresionLogica;
import ast.LitChar;
import ast.LitInt;
import ast.LitReal;
import ast.LlamadaFuncion;
import ast.MenosUnario;
import ast.Not;
import ast.Variable;
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
