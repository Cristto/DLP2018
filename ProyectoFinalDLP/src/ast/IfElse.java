/**
 * @generated VGen 1.3.3
 */

package ast;

import java.util.*;
import visitor.*;

//	ifElse:statement -> condicion:expression  sentenciasIf:statement*  sentenciasElse:statement*

public class IfElse extends AbstractStatement {

	public IfElse(Expression condicion, List<Statement> sentenciasIf, List<Statement> sentenciasElse) {
		this.condicion = condicion;
		this.sentenciasIf = sentenciasIf;
		this.sentenciasElse = sentenciasElse;

		searchForPositions(condicion, sentenciasIf, sentenciasElse);	// Obtener linea/columna a partir de los hijos
	}
	
	public IfElse(Expression condicion, List<Statement> sentenciasIf) {
		this.condicion = condicion;
		this.sentenciasIf = sentenciasIf;

		searchForPositions(condicion, sentenciasIf);	// Obtener linea/columna a partir de los hijos
	}

	@SuppressWarnings("unchecked")
	public IfElse(Object condicion, Object sentenciasIf, Object sentenciasElse) {
		this.condicion = (Expression) condicion;
		this.sentenciasIf = (List<Statement>) sentenciasIf;
		this.sentenciasElse = (List<Statement>) sentenciasElse;

		searchForPositions(condicion, sentenciasIf, sentenciasElse);	// Obtener linea/columna a partir de los hijos
	}
	
	@SuppressWarnings("unchecked")
	public IfElse(Object condicion, Object sentenciasIf) {
		this.condicion = (Expression) condicion;
		this.sentenciasIf = (List<Statement>) sentenciasIf;

		searchForPositions(condicion, sentenciasIf);	// Obtener linea/columna a partir de los hijos
	}

	public Expression getCondicion() {
		return condicion;
	}
	public void setCondicion(Expression condicion) {
		this.condicion = condicion;
	}

	public List<Statement> getSentenciasIf() {
		return sentenciasIf;
	}
	public void setSentenciasIf(List<Statement> sentenciasIf) {
		this.sentenciasIf = sentenciasIf;
	}

	public List<Statement> getSentenciasElse() {
		return sentenciasElse;
	}
	public void setSentenciasElse(List<Statement> sentenciasElse) {
		this.sentenciasElse = sentenciasElse;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private Expression condicion;
	private List<Statement> sentenciasIf;
	private List<Statement> sentenciasElse;
}

