/**
 * @generated VGen 1.3.3
 */

package ast;

import java.util.*;
import visitor.*;

//	program -> definiciones:definition*

public class Program extends AbstractTraceable implements AST {

	public Program(List<Definition> definiciones) {
		this.definiciones = definiciones;

		searchForPositions(definiciones);	// Obtener linea/columna a partir de los hijos
	}

	@SuppressWarnings("unchecked")
	public Program(Object definiciones) {
		this.definiciones = (List<Definition>) definiciones;

		searchForPositions(definiciones);	// Obtener linea/columna a partir de los hijos
	}

	public List<Definition> getDefiniciones() {
		return definiciones;
	}
	public void setDefiniciones(List<Definition> definiciones) {
		this.definiciones = definiciones;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private List<Definition> definiciones;
}

