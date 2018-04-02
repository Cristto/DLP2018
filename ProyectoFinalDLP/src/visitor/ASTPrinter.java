/**
 * @generated VGen 1.3.3
 */

package visitor;

import java.io.*;

import ast.*;
import java.util.*;

/**
 * ASTPrinter. Utilidad que ayuda a validar un arbol AST:
 * 	-	Muestra la estructura del árbol en HTML.
 * 	-	Destaca los hijos/propiedades a null.
 * 	-	Muestra a qué texto apuntan las posiciones de cada nodo (linea/columna)
 * 		ayudando a decidir cual de ellas usar en los errores y generación de código.
 * 
 * Esta clase se genera con VGen. El uso de esta clase es opcional (puede eliminarse del proyecto). 
 * 
 */
public class ASTPrinter extends DefaultVisitor {

	/**
	 * toHtml. Muestra la estructura del AST indicando qué hay en las posiciones (linea y columna) de cada nodo.
	 * 
	 * @param sourceFile	El fichero del cual se ha obtenido el AST
	 * @param raiz				El AST creado a partir de sourceFile
	 * @param filename		Nombre del fichero HMTL a crear con la traza del AST
	 */

	public static void toHtml(String sourceFile, AST raiz, String filename) {
		toHtml(sourceFile, raiz, filename, 4);
	}
	
	public static void toHtml(AST raiz, String filename) {
		toHtml(null, raiz, filename);
	}

	// tabWidth deberían ser los espacios correspondientes a un tabulador en eclipse.
	// Normalmente no será necesario especificarlo. Usar mejor los dos métodos anteriores.
	public static void toHtml(String sourceFile, AST raiz, String filename, int tabWidth) {
		try {
			PrintWriter writer = new PrintWriter(new FileWriter(filename.endsWith(".html") ? filename : filename + ".html"));
			generateHeader(writer);
			writer.println("[ASTPrinter] -------------------------------- line:col  line:col");
			if (raiz != null) {
				ASTPrinter tracer = new ASTPrinter(writer, loadLines(sourceFile, tabWidth));
				raiz.accept(tracer, new Integer(0));
			} else
				writer.println("raiz == null");
			writer.println(ls + ls + "[ASTPrinter] --------------------------------");
			generateFooter(writer);
			writer.close();
			System.out.println(ls + "ASTPrinter: Fichero '" + filename + ".html' generado con éxito. Abra el fichero para validar el árbol AST generado.");
		} catch (IOException e) {
			System.out.println(ls + "ASTPrinter: No se ha podido crear el fichero " + filename);
			e.printStackTrace();
		}
	}

	private static void generateHeader(PrintWriter writer) {
		writer.println("<html>\r\n" +
				"<head>\r\n" +
				"<style type=\"text/css\">\r\n" +
				".value { font-weight: bold; }\r\n" +
				".dots { color: #888888; }\r\n" +
				".type { color: #BBBBBB; }\r\n" +
				".pos { color: #CCCCCC; }\r\n" +
				".sourceText { color: #BBBBBB; }\r\n" +
				".posText {\r\n" +
				"	color: #BBBBBB;\r\n" +
				"	text-decoration: underline; font-weight: bold;\r\n" +
				"}\r\n" +
				".null {\r\n" +
				"	color: #FF0000;\r\n" +
				"	font-weight: bold;\r\n" +
				"	font-style: italic;\r\n" +
				"}\r\n" +
			//	 "pre { font-family: Arial, Helvetica, sans-serif; font-size: 11px; }\r\n" +
			//	"pre { font-size: 11px; }\r\n" +
				"</style>\r\n" +
				"</head>\r\n" +
				"\r\n" +
				"<body><pre>");
	}

	private static void generateFooter(PrintWriter writer) {
		writer.println("</pre>\r\n" +
				"</body>\r\n" +
				"</html>");
	}

	private ASTPrinter(PrintWriter writer, List<String> sourceLines) {
		this.writer = writer;
		this.sourceLines = sourceLines;
	}

	// ----------------------------------------------

	//	class Program { List<Definition> definiciones; }
	public Object visit(Program node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "Program", node, false);

		visit(indent + 1, "definiciones", "List<Definition>",node.getDefiniciones());
		return null;
	}

	//	class DefVariable { String ident;  Type tipo; }
	public Object visit(DefVariable node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "DefVariable", node, false);

		print(indent + 1, "ident", "String", node.getIdent());
		visit(indent + 1, "tipo", "Type",node.getTipo());
		return null;
	}

	//	class DefFuncion { String ident;  Type tipo;  List<Definition> defParametros;  List<Definition> defVarLocal;  List<Statement> sentencias; }
	public Object visit(DefFuncion node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "DefFuncion", node, false);

		print(indent + 1, "ident", "String", node.getIdent());
		visit(indent + 1, "tipo", "Type",node.getTipo());
		visit(indent + 1, "defParametros", "List<Definition>",node.getDefParametros());
		visit(indent + 1, "defVarLocal", "List<Definition>",node.getDefVarLocal());
		visit(indent + 1, "sentencias", "List<Statement>",node.getSentencias());
		return null;
	}

	//	class DefStruct { String ident;  List<Definition> campos; }
	public Object visit(DefStruct node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "DefStruct", node, false);

		print(indent + 1, "ident", "String", node.getIdent());
		visit(indent + 1, "campos", "List<Definition>",node.getCampos());
		return null;
	}

	//	class DefCampo { String ident;  Type tipo; }
	public Object visit(DefCampo node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "DefCampo", node, false);

		print(indent + 1, "ident", "String", node.getIdent());
		visit(indent + 1, "tipo", "Type",node.getTipo());
		return null;
	}

	//	class IfElse { Expression condicion;  List<Statement> sentenciasIf;  List<Statement> sentenciasElse; }
	public Object visit(IfElse node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "IfElse", node, false);

		visit(indent + 1, "condicion", "Expression",node.getCondicion());
		visit(indent + 1, "sentenciasIf", "List<Statement>",node.getSentenciasIf());
		visit(indent + 1, "sentenciasElse", "List<Statement>",node.getSentenciasElse());
		return null;
	}

	//	class While { Expression condicion;  List<Statement> sentencias; }
	public Object visit(While node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "While", node, false);

		visit(indent + 1, "condicion", "Expression",node.getCondicion());
		visit(indent + 1, "sentencias", "List<Statement>",node.getSentencias());
		return null;
	}

	//	class Read { Expression expresion; }
	public Object visit(Read node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "Read", node, false);

		visit(indent + 1, "expresion", "Expression",node.getExpresion());
		return null;
	}

	//	class Print { Expression expresion; }
	public Object visit(Print node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "Print", node, false);

		visit(indent + 1, "expresion", "Expression",node.getExpresion());
		return null;
	}

	//	class Printsp { Expression expresion; }
	public Object visit(Printsp node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "Printsp", node, false);

		visit(indent + 1, "expresion", "Expression",node.getExpresion());
		return null;
	}

	//	class Println { Expression expresion; }
	public Object visit(Println node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "Println", node, false);

		visit(indent + 1, "expresion", "Expression",node.getExpresion());
		return null;
	}

	//	class Asignacion { Expression leftExpr;  Expression rightExpr; }
	public Object visit(Asignacion node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "Asignacion", node, false);

		visit(indent + 1, "leftExpr", "Expression",node.getLeftExpr());
		visit(indent + 1, "rightExpr", "Expression",node.getRightExpr());
		return null;
	}

	//	class Return { Expression expresion; }
	public Object visit(Return node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "Return", node, false);

		visit(indent + 1, "expresion", "Expression",node.getExpresion());
		return null;
	}

	//	class Invocacion { String ident;  List<Expression> argumentos; }
	public Object visit(Invocacion node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "Invocacion", node, false);

		print(indent + 1, "ident", "String", node.getIdent());
		visit(indent + 1, "argumentos", "List<Expression>",node.getArgumentos());
		return null;
	}

	//	class ExpresionAritmetica { Expression leftExpr;  String operador;  Expression rightExpr; }
	public Object visit(ExpresionAritmetica node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "ExpresionAritmetica", node, false);

		visit(indent + 1, "leftExpr", "Expression",node.getLeftExpr());
		print(indent + 1, "operador", "String", node.getOperador());
		visit(indent + 1, "rightExpr", "Expression",node.getRightExpr());
		return null;
	}

	//	class Comparacion { Expression leftExpr;  String operador;  Expression rightExpr; }
	public Object visit(Comparacion node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "Comparacion", node, false);

		visit(indent + 1, "leftExpr", "Expression",node.getLeftExpr());
		print(indent + 1, "operador", "String", node.getOperador());
		visit(indent + 1, "rightExpr", "Expression",node.getRightExpr());
		return null;
	}

	//	class ExpresionLogica { Expression leftExpr;  String operador;  Expression rightExpr; }
	public Object visit(ExpresionLogica node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "ExpresionLogica", node, false);

		visit(indent + 1, "leftExpr", "Expression",node.getLeftExpr());
		print(indent + 1, "operador", "String", node.getOperador());
		visit(indent + 1, "rightExpr", "Expression",node.getRightExpr());
		return null;
	}

	//	class MenosUnario { Expression expresion; }
	public Object visit(MenosUnario node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "MenosUnario", node, false);

		visit(indent + 1, "expresion", "Expression",node.getExpresion());
		return null;
	}

	//	class Not { Expression expresion; }
	public Object visit(Not node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "Not", node, false);

		visit(indent + 1, "expresion", "Expression",node.getExpresion());
		return null;
	}

	//	class AccesoArray { Expression ident;  Expression index; }
	public Object visit(AccesoArray node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "AccesoArray", node, false);

		visit(indent + 1, "ident", "Expression",node.getIdent());
		visit(indent + 1, "index", "Expression",node.getIndex());
		return null;
	}

	//	class AccesoStruct { Expression ident;  String campo; }
	public Object visit(AccesoStruct node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "AccesoStruct", node, false);

		visit(indent + 1, "ident", "Expression",node.getIdent());
		print(indent + 1, "campo", "String", node.getCampo());
		return null;
	}

	//	class Variable { String ident; }
	public Object visit(Variable node, Object param) {
		int indent = ((Integer)param).intValue();

		printCompact(indent, "Variable", node, "ident", node.getIdent());
		return null;
	}

	//	class LlamadaFuncion { String ident;  List<Expression> argumentos; }
	public Object visit(LlamadaFuncion node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "LlamadaFuncion", node, false);

		print(indent + 1, "ident", "String", node.getIdent());
		visit(indent + 1, "argumentos", "List<Expression>",node.getArgumentos());
		return null;
	}

	//	class Cast { Type tipo;  Expression expresion; }
	public Object visit(Cast node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "Cast", node, false);

		visit(indent + 1, "tipo", "Type",node.getTipo());
		visit(indent + 1, "expresion", "Expression",node.getExpresion());
		return null;
	}

	//	class LitInt { int valor; }
	public Object visit(LitInt node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "LitInt", node, false);

		print(indent + 1, "valor", "int", node.getValor());
		return null;
	}

	//	class LitReal { float valor; }
	public Object visit(LitReal node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "LitReal", node, false);

		print(indent + 1, "valor", "float", node.getValor());
		return null;
	}

	//	class LitChar { char valor; }
	public Object visit(LitChar node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "LitChar", node, false);

		print(indent + 1, "valor", "char", node.getValor());
		return null;
	}

	//	class IntTipo {  }
	public Object visit(IntTipo node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "IntTipo", node, true);

		return null;
	}

	//	class RealTipo {  }
	public Object visit(RealTipo node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "RealTipo", node, true);

		return null;
	}

	//	class CharTipo {  }
	public Object visit(CharTipo node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "CharTipo", node, true);

		return null;
	}

	//	class IdentTipo { String valor; }
	public Object visit(IdentTipo node, Object param) {
		int indent = ((Integer)param).intValue();

		printCompact(indent, "IdentTipo", node, "valor", node.getValor());
		return null;
	}

	//	class ArrayTipo { int size;  Type tipo; }
	public Object visit(ArrayTipo node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "ArrayTipo", node, false);

		print(indent + 1, "size", "int", node.getSize());
		visit(indent + 1, "tipo", "Type",node.getTipo());
		return null;
	}

	//	class FuncionTipo { Type tipoRetorno; }
	public Object visit(FuncionTipo node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "FuncionTipo", node, false);

		visit(indent + 1, "tipoRetorno", "Type",node.getTipoRetorno());
		return null;
	}

	//	class VoidTipo {  }
	public Object visit(VoidTipo node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "VoidTipo", node, true);

		return null;
	}

	// -----------------------------------------------------------------
	// Métodos invocados desde los métodos visit -----------------------

	private void printName(int indent, String name, AST node, boolean empty) {
		String text = ls + tabula(indent) + name + " &rarr;  ";
		text = String.format("%1$-" + 93 + "s", text);
		if (empty)
			text = text.replace(name, valueTag(name));
		writer.print(text + getPosition(node));
	}

	private void print(int indent, String name, String type, Object value) {
		write(indent, formatValue(value) + "  " + typeTag(type));
	}

	private void print(int indent, String attName, String type, List<? extends Object> children) {
		write(indent, attName + "  " + typeTag(type) + " = ");
		if (children != null)
			for (Object child : children)
				write(indent + 1, formatValue(child));
		else
			writer.print(" " + valueTag(null));
	}

	// Versión compacta de una linea para nodos que solo tienen un atributo String
	private void printCompact(int indent, String nodeName, AST node, String attName, Object value) {
		String fullName = nodeName + '.' + attName;
		String text = ls + tabula(indent) + '\"' + value + "\"  " + fullName;
		text = String.format("%1$-" + 88 + "s", text);
		// text = text.replace(value.toString(), valueTag(value));
		text = text.replace(fullName, typeTag(fullName));
		writer.print(text + getPosition(node));
	}

	private void visit(int indent, String attName, String type, List<? extends AST> children) {
		write(indent, attName + "  " + typeTag(type) + " = ");
		if (children != null)
			for (AST child : children)
				child.accept(this, indent + 1);
		else
			writer.print(" " + valueTag(null));
	}

	private void visit(int indent, String attName, String type, AST child) {
		if (child != null)
			child.accept(this, new Integer(indent));
		else
			write(indent, valueTag(null) + "  " + attName + ':' + typeTag(type));
	}

	// -----------------------------------------------------------------
	// Métodos auxiliares privados -------------------------------------

	private void write(int indent, String text) {
		writer.print(ls + tabula(indent) + text);
	}

	private static String tabula(int count) {
		StringBuffer cadena = new StringBuffer("<span class=\"dots\">");
		for (int i = 0; i < count; i++)
			cadena.append(i % 2 == 0 && i > 0 ? "|  " : "·  ");
		return cadena.toString() + "</span>";
	}

	private String typeTag(String type) {
		if (type.equals("String"))
			return "";
		return "<span class=\"type\">" + type.replace("<", "&lt;").replace(">", "&gt;") + "</span>";
	}

	private String valueTag(Object value) {
		if (value == null)
			return "<span class=\"null\">null</span>";
		return "<span class=\"value\">" + value + "</span>";
	}

	private String formatValue(Object value) {
		String text = valueTag(value);
		if (value instanceof String)
			text = "\"" + text + '"';
		return text;
	}


	// -----------------------------------------------------------------
	// Métodos para mostrar las Posiciones -----------------------------

	private String getPosition(Traceable node) {
		String text = node.getStart() + "  " + node.getEnd();
		text = "<span class=\"pos\">" + String.format("%1$-" + 13 + "s", text) + "</span>";
		text = text.replace("null", "<span class=\"null\">null</span>");
		String sourceText = findSourceText(node);
		if (sourceText != null)
			text += sourceText;
		return text;
	}

	private String findSourceText(Traceable node) {
		if (sourceLines == null)
			return null;

		Position start = node.getStart();
		Position end = node.getEnd();
		if (start == null || end == null)
			return null;

		String afterText, text, beforeText;
		if (start.getLine() == end.getLine()) {
			String line = sourceLines.get(start.getLine() - 1);
			afterText = line.substring(0, start.getColumn() - 1);
			text = line.substring(start.getColumn() - 1, end.getColumn());
			beforeText = line.substring(end.getColumn());
		} else {
			String firstLine = sourceLines.get(start.getLine() - 1);
			String lastLine = sourceLines.get(end.getLine() - 1);

			afterText = firstLine.substring(0, start.getColumn() - 1);

			text = firstLine.substring(start.getColumn() - 1);
			text += "</span><span class=\"sourceText\">" + " ... " + "</span><span class=\"posText\">";
			text += lastLine.substring(0, end.getColumn()).replaceAll("^\\s+", "");

			beforeText = lastLine.substring(end.getColumn());
		}
		return "<span class=\"sourceText\">" + afterText.replaceAll("^\\s+", "")
				+ "</span><span class=\"posText\">" + text
				+ "</span><span class=\"sourceText\">" + beforeText + "</span>";
	}

	private static List<String> loadLines(String sourceFile, int tabWidth) {
		if (sourceFile == null)
			return null;
		try {
			String spaces = new String(new char[tabWidth]).replace("\0", " ");
			
			List<String> lines = new ArrayList<String>();
			BufferedReader br = new BufferedReader(new FileReader(sourceFile));
			String line;
			while ((line = br.readLine()) != null)
				lines.add(line.replace("\t", spaces));
			br.close();
			return lines;
		} catch (FileNotFoundException e) {
			System.out.println("Warning. No se pudo encontrar el fichero fuente '" + sourceFile + "'. No se mostrará informaicón de posición.");
			return null;
		} catch (IOException e) {
			System.out.println("Warning. Error al leer del fichero fuente '" + sourceFile + "'. No se mostrará informaicón de posición.");
			return null;
		}
	}


	private List<String> sourceLines;
	private static String ls = System.getProperty("line.separator");
	private PrintWriter writer;
}

