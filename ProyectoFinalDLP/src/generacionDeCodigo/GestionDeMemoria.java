package generacionDeCodigo;

import ast.Program;
import ast.definitions.DefFuncion;
import ast.definitions.DefStruct;
import ast.definitions.DefVariable;
import ast.types.TipoArray;
import ast.types.TipoChar;
import ast.types.TipoFuncion;
import ast.types.TipoInt;
import ast.types.TipoReal;
import ast.types.TipoStruct;
import ast.types.TipoVoid;
import generacionDeCodigo.tipos.Funcion;
import visitor.DefaultVisitor;

/** 
 * Clase encargada de asignar direcciones a las variables 
 */
public class GestionDeMemoria extends DefaultVisitor {
	
	 private int dir;
	
	    @Override
	    public Object visit(Program node, Object param) {
		this.dir = 0;
		return super.visit(node, param);
	    }

	    @Override
	    public Object visit(DefVariable node, Object param) {
		node.setAmbito(dir);
		dir += (int) node.getTipo().accept(this, Funcion.TAM);
		return null;
	    }

	    @Override
	    public Object visit(DefStruct node, Object param) {
		node.setDireccion(dir);
		node.getTipo().accept(this, Funcion.TAM);
		return null;
	    }

	    @Override
	    public Object visit(DefFuncion node, Object param) {
		int dirLocal = 0;
		int dirParam = 4;

		for (int i = node.getParametros().size() - 1; i >= 0; i--) {
		    node.getParametros().get(i).setDireccion(dirParam);
		    dirParam += (int) node.getParametros().get(i).getTipo().accept(this, Funcion.TAM);
		}

		for (DefVariable child : node.getVariables()) {
		    dirLocal -= (int) child.getTipo().accept(this, Funcion.TAM);
		    child.setDireccion(dirLocal);
		}
		return null;
	    }

	    @Override
	    public Object visit(TipoStruct node, Object param) {
		assert (param == Funcion.TAM);
		int sum = 0;
		for (DefCampo c : node.getStruct().getCampos()) {
		    c.setDireccion(sum);
		    sum += (int) c.getTipo().accept(this, Funcion.TAM);
		}

		return sum;
	    }

	    @Override
	    public Object visit(TipoInt node, Object param) {
		assert (param == Funcion.TAM);
		return 2;
	    }

	    @Override
	    public Object visit(TipoReal node, Object param) {
		assert (param == Funcion.TAM);
		return 4;
	    }

	    @Override
	    public Object visit(TipoChar node, Object param) {
		assert (param == Funcion.TAM);
		return 1;
	    }

	    @Override
	    public Object visit(TipoArray node, Object param) {
		assert (param == Funcion.TAM);
		return (int) node.getTipo().accept(this, Funcion.TAM) * node.getSize();
	    }

	    @Override
	    public Object visit(TipoFuncion node, Object param) {
		assert (param == Funcion.TAM);
		return node.getTipoRetorno().accept(this, Funcion.TAM);
	    }

	    @Override
	    public Object visit(TipoVoid node, Object param) {
		assert (param == Funcion.TAM);
		return 0;
	    }

	   



}
