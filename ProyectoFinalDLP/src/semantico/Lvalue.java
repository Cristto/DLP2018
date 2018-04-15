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
import ast.statements.Asignacion;
import ast.statements.Read;
import ast.types.TipoFuncion;
import main.GestorErrores;
import visitor.DefaultVisitor;

public class Lvalue extends DefaultVisitor{
	
	 private GestorErrores gestorErrores;

	public Lvalue(GestorErrores gestorErrores) {
		this.gestorErrores = gestorErrores;
	}

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

	    @Override
	    public Object visit(Asignacion node, Object param) {
	    	super.visit(node, param);
	    	if(!node.getLeftExpr().isLvalue()) {
	    		gestorErrores.error("Fase lvalue", "la expresion de la izquierda no es modificable ",
						node.getStart());
	    	}
	    	return null;
	    }
	    
	    @Override
	    public Object visit(Read node, Object param) {
	    	super.visit(node, param);
	    	if(!node.getExpresion().isLvalue()) {
	    		gestorErrores.error("Fase lvalue", "la expresion no es modificable ",
						node.getStart());
	    	}
	    	return null;
	    }
}
