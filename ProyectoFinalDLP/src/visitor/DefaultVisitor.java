/**
 * @generated VGen 1.3.3
 */

package visitor;

import ast.*;
import java.util.*;

/*
DefaultVisitor. Implementación base del visitor para ser derivada por nuevos visitor.
	No modificar esta clase. Para crear nuevos visitor usar el fichero "_PlantillaParaVisitors.txt".
	DefaultVisitor ofrece una implementación por defecto de cada nodo que se limita a visitar los nodos hijos.
*/
public class DefaultVisitor implements Visitor {

	//	class Program { List<Definition> definiciones; }
	public Object visit(Program node, Object param) {
		visitChildren(node.getDefiniciones(), param);
		return null;
	}

	//	class DefVariable { String ident;  Type tipo; }
	public Object visit(DefVariable node, Object param) {
		if (node.getTipo() != null)
			node.getTipo().accept(this, param);
		return null;
	}

	//	class DefFuncion { String ident;  Type tipo;  List<Definition> defParametros;  List<Definition> defVarLocal;  List<Statement> sentencias; }
	public Object visit(DefFuncion node, Object param) {
		if (node.getTipo() != null)
			node.getTipo().accept(this, param);
		visitChildren(node.getDefParametros(), param);
		visitChildren(node.getDefVarLocal(), param);
		visitChildren(node.getSentencias(), param);
		return null;
	}

	//	class DefStruct { String ident;  List<Definition> campos; }
	public Object visit(DefStruct node, Object param) {
		visitChildren(node.getCampos(), param);
		return null;
	}

	//	class DefCampo { String ident;  Type tipo; }
	public Object visit(DefCampo node, Object param) {
		if (node.getTipo() != null)
			node.getTipo().accept(this, param);
		return null;
	}

	//	class IfElse { Expression condicion;  List<Statement> sentenciasIf;  List<Statement> sentenciasElse; }
	public Object visit(IfElse node, Object param) {
		if (node.getCondicion() != null)
			node.getCondicion().accept(this, param);
		visitChildren(node.getSentenciasIf(), param);
		visitChildren(node.getSentenciasElse(), param);
		return null;
	}

	//	class While { Expression condicion;  List<Statement> sentencias; }
	public Object visit(While node, Object param) {
		if (node.getCondicion() != null)
			node.getCondicion().accept(this, param);
		visitChildren(node.getSentencias(), param);
		return null;
	}

	//	class Read { Expression expresion; }
	public Object visit(Read node, Object param) {
		if (node.getExpresion() != null)
			node.getExpresion().accept(this, param);
		return null;
	}

	//	class Print { Expression expresion; }
	public Object visit(Print node, Object param) {
		if (node.getExpresion() != null)
			node.getExpresion().accept(this, param);
		return null;
	}

	//	class Printsp { Expression expresion; }
	public Object visit(Printsp node, Object param) {
		if (node.getExpresion() != null)
			node.getExpresion().accept(this, param);
		return null;
	}

	//	class Println { Expression expresion; }
	public Object visit(Println node, Object param) {
		if (node.getExpresion() != null)
			node.getExpresion().accept(this, param);
		return null;
	}

	//	class Asignacion { Expression leftExpr;  Expression rightExpr; }
	public Object visit(Asignacion node, Object param) {
		if (node.getLeftExpr() != null)
			node.getLeftExpr().accept(this, param);
		if (node.getRightExpr() != null)
			node.getRightExpr().accept(this, param);
		return null;
	}

	//	class Return { Expression expresion; }
	public Object visit(Return node, Object param) {
		if (node.getExpresion() != null)
			node.getExpresion().accept(this, param);
		return null;
	}

	//	class Invocacion { String ident;  List<Expression> argumentos; }
	public Object visit(Invocacion node, Object param) {
		visitChildren(node.getArgumentos(), param);
		return null;
	}

	//	class ExpresionAritmetica { Expression leftExpr;  String operador;  Expression rightExpr; }
	public Object visit(ExpresionAritmetica node, Object param) {
		if (node.getLeftExpr() != null)
			node.getLeftExpr().accept(this, param);
		if (node.getRightExpr() != null)
			node.getRightExpr().accept(this, param);
		return null;
	}

	//	class Comparacion { Expression leftExpr;  String operador;  Expression rightExpr; }
	public Object visit(Comparacion node, Object param) {
		if (node.getLeftExpr() != null)
			node.getLeftExpr().accept(this, param);
		if (node.getRightExpr() != null)
			node.getRightExpr().accept(this, param);
		return null;
	}

	//	class ExpresionLogica { Expression leftExpr;  String operador;  Expression rightExpr; }
	public Object visit(ExpresionLogica node, Object param) {
		if (node.getLeftExpr() != null)
			node.getLeftExpr().accept(this, param);
		if (node.getRightExpr() != null)
			node.getRightExpr().accept(this, param);
		return null;
	}

	//	class MenosUnario { String operador;  Expression expresion; }
	public Object visit(MenosUnario node, Object param) {
		if (node.getExpresion() != null)
			node.getExpresion().accept(this, param);
		return null;
	}

	//	class Not { String operador;  Expression expresion; }
	public Object visit(Not node, Object param) {
		if (node.getExpresion() != null)
			node.getExpresion().accept(this, param);
		return null;
	}

	//	class AccesoArray { Expression ident;  Expression index; }
	public Object visit(AccesoArray node, Object param) {
		if (node.getIdent() != null)
			node.getIdent().accept(this, param);
		if (node.getIndex() != null)
			node.getIndex().accept(this, param);
		return null;
	}

	//	class AccesoStruct { Expression ident;  String campo; }
	public Object visit(AccesoStruct node, Object param) {
		if (node.getIdent() != null)
			node.getIdent().accept(this, param);
		return null;
	}

	//	class Variable { String ident; }
	public Object visit(Variable node, Object param) {
		return null;
	}

	//	class LlamadaFuncion { String ident;  List<Expression> argumentos; }
	public Object visit(LlamadaFuncion node, Object param) {
		visitChildren(node.getArgumentos(), param);
		return null;
	}

	//	class Cast { Type tipo;  Expression expresion; }
	public Object visit(Cast node, Object param) {
		if (node.getTipo() != null)
			node.getTipo().accept(this, param);
		if (node.getExpresion() != null)
			node.getExpresion().accept(this, param);
		return null;
	}

	//	class LitInt { int valor; }
	public Object visit(LitInt node, Object param) {
		return null;
	}

	//	class LitReal { float valor; }
	public Object visit(LitReal node, Object param) {
		return null;
	}

	//	class LitChar { char valor; }
	public Object visit(LitChar node, Object param) {
		return null;
	}

	//	class IntTipo {  }
	public Object visit(IntTipo node, Object param) {
		return null;
	}

	//	class RealTipo {  }
	public Object visit(RealTipo node, Object param) {
		return null;
	}

	//	class CharTipo {  }
	public Object visit(CharTipo node, Object param) {
		return null;
	}

	//	class IdentTipo { String valor; }
	public Object visit(IdentTipo node, Object param) {
		return null;
	}

	//	class ArrayTipo { int size;  Type tipo; }
	public Object visit(ArrayTipo node, Object param) {
		if (node.getTipo() != null)
			node.getTipo().accept(this, param);
		return null;
	}

	//	class FuncionTipo { Type tipoRetorno; }
	public Object visit(FuncionTipo node, Object param) {
		if (node.getTipoRetorno() != null)
			node.getTipoRetorno().accept(this, param);
		return null;
	}

	//	class VoidTipo {  }
	public Object visit(VoidTipo node, Object param) {
		return null;
	}
	
	// Método auxiliar -----------------------------
	protected void visitChildren(List<? extends AST> children, Object param) {
		if (children != null)
			for (AST child : children)
				child.accept(this, param);
	}
}
