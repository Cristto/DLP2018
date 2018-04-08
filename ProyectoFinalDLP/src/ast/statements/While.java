/**
 * @generated VGen 1.3.3
 */

package ast.statements;

import java.util.*;

import ast.expressions.Expression;
import visitor.*;

//	while:statement -> condicion:expression  sentencias:statement*

public class While extends AbstractStatement {

	public While(Expression condicion, List<Statement> sentencias) {
		this.condicion = condicion;
		this.sentencias = sentencias;

		searchForPositions(condicion, sentencias);	// Obtener linea/columna a partir de los hijos
	}

	@SuppressWarnings("unchecked")
	public While(Object condicion, Object sentencias) {
		this.condicion = (Expression) condicion;
		this.sentencias = (List<Statement>) sentencias;

		searchForPositions(condicion, sentencias);	// Obtener linea/columna a partir de los hijos
	}

	public Expression getCondicion() {
		return condicion;
	}
	public void setCondicion(Expression condicion) {
		this.condicion = condicion;
	}

	public List<Statement> getSentencias() {
		return sentencias;
	}
	public void setSentencias(List<Statement> sentencias) {
		this.sentencias = sentencias;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private Expression condicion;
	private List<Statement> sentencias;
}

