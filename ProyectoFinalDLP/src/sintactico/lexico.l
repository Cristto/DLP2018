// @author Raúl Izquierdo

/* -- No es necesario modificar esta parte ----------------------------------------------- */
package sintactico;

import java.io.*;
import main.*;
import ast.Position;

%%
%byaccj
%unicode
%line
%column
%public

%{
	public Yylex(Reader in, GestorErrores gestor) {
		this(in);
		this.gestor = gestor;
	}

	public int line() { return yyline + 1; }
	public int column() { return yycolumn + 1; }
	public String lexeme() { return yytext(); }

	// Traza para probar el léxico de manera independiente al sintáctico
	public static void main(String[] args) throws Exception {
		Yylex lex = new Yylex(new FileReader(Main.programa), new GestorErrores());
		int token;
		while ((token = lex.yylex()) != 0)
			System.out.println("\t[" + lex.line() + ":" + lex.column() + "] Token: " + token + ". Lexema: " + lex.lexeme());
	}

	private GestorErrores gestor;
%}

%%

/* -- Modificar aquí. Añadir reglas en esta sección ----------------------------------------- */

[!+<>;:(){}=,\./*-]  { return yytext().charAt(0); }
"\]" | "\["  { return yytext().charAt(0); }

var { return Parser.VAR; }
float {return Parser.REAL; }
int { return Parser.INT; }
struct {return Parser.STRUCT;}
char {return Parser.CHAR; }
return {return Parser.RETURN; }

print 	{ return Parser.PRINT; }
printsp { return Parser.PRINTSP; }
println { return Parser.PRINTLN; }
read 	{ return Parser.READ; }
if 		{ return Parser.IF; }
else 	{ return Parser.ELSE; }
while 	{ return Parser.WHILE; }
cast 	{return Parser.CAST; }

"!="	{return Parser.DISTINTO; }
">="	{return Parser.MAYORIGUALQUE; }
"<="	{return Parser.MENORIGUALQUE; }
"&&"	{return Parser.AND; }
"||"	{return Parser.OR; }
"=="	{return Parser.IGUALQUE;}


0|[1-9][0-9]*     				{ return Parser.LITENT; }
[0-9]+"."[0-9]+ 		{ return Parser.LITREAL; }
[a-záéíóúA-ZÁÉÍÓÚnñÑ][a-záéíóúA-ZÁÉÍÓÚ0-9_nñÑ]* { return Parser.IDENT; }
"'"((\\[0-9]+)|(\\n)|(\\t)|.)"'"	{return Parser.LITCHAR;}






/* -- Se aconseja dejar estas reglas al final ----------------------------------------------- */

"/*"([^*]|\*+[^*/])*\*+"/"		{ }		/* Comentario de varias lineas como este */
"//".*							{ }		// Comentario de una linea como este

[ \n\r]		{ }
"\t"		{ yycolumn += 3; } // Para que coincida con la info del editor de Eclipse (opcional). En eclipse: \t == 4 caracteres. En Jflex: \t == 1 carácter.

.			{ gestor.error("Léxico", "Cadena \"" + yytext() +"\" no reconocida.", new Position(line(), column())); }
