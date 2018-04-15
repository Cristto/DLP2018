// @author Raúl Izquierdo

/* No es necesario modificar esta sección ------------------ */
%{
package sintactico;

import java.io.*;
import java.util.*;
import ast.*;
import ast.definitions.*;
import ast.expressions.*;
import ast.statements.*;
import ast.types.*;
import main.*;

%}

/* Precedencias aquí --------------------------------------- */
%right '='
%left 'OR' 'AND' 
%left '<' '>' 'MENORIGUALQUE' 'MAYORIGUALQUE' 'IGUALQUE' 'DISTINTO'
%left '+' '-'
%left '*' '/' 
%left  '['  ']' 
%left '!'
%left  '.'
%nonassoc 'ELSE'
%nonassoc '(' ')'

%%

/* Añadir las reglas en esta sección ----------------------- */

program: definiciones 			{raiz = new Program($1);}
	;

definiciones: definiciones definicion	{$$ = $1; ((List)$1).add($2);}
	|									{$$ = new ArrayList<Definition>();}
	;
definicion: defFuncion 	{$$ = $1;}
	|		defVariable {$$ = $1;}
	|		defStruct 	{$$ = $1;}
	; 

defVariablesOpt: defVariablesOpt defVariable  {$$ = $1; ((List)$1).add($2);}
		|		 							  {$$ = new ArrayList<Definition>();}
		;

defVariable: 'VAR' 'IDENT' ':' tipo ';' {$$ = new DefVariable($2,$4);}

defFuncion: 'IDENT' '(' parametrosOpt ')' ':' tipo '{' defVariablesOpt sentenciasOpt '}' {$$ = new DefFuncion($1,new TipoFuncion($6, $3),$8,$9);}
	|		'IDENT' '(' parametrosOpt ')' '{' defVariablesOpt sentenciasOpt '}' {$$ = new DefFuncion($1,new TipoFuncion(new TipoVoid(), $3),$6,$7);}
	;

parametrosOpt: defParametros 	{$$ = $1;}
	|							{$$ = new ArrayList<Definition>();}				
	;
defParametros: defParametros ',' defParametro	{$$=$1;  ((List)$$).add($3); }
		|	   defParametro		{$$= new ArrayList<Definition>(); ((List)$$).add($1); }  
		;
defParametro: 'IDENT' ':' tipo 		{$$ = new DefVariable($1,$3);}
	;

defStruct: 'STRUCT' 'IDENT' '{' camposOpt '}' ';' {$$ = new DefStruct($2,new TipoStruct($2),$4);}
	;

camposOpt: camposOpt defCampo  { $$ = $1; ((List)$1).add($2);}
	|				{$$ = new ArrayList<Definition>();}
	;
defCampo: 'IDENT' ':' tipo ';' {$$ = new DefCampo($1,$3);}
	;

tipo: tipoSimple			{ $$ = $1; }
 |	'IDENT'					{$$ = new TipoStruct($1);}
 |	'[' 'LITENT' ']' tipo	{$$ = new TipoArray($2,$4);}
 ;
 
tipoSimple: 'INT' 				{$$ = IntTipo.getInstance();}
 |	'REAL'					{$$ = RealTipo.getInstance();}
 |	'CHAR'					{$$ = CharTipo.getInstance();}
 ;

sentenciasOpt: sentenciasOpt sentencia    {$$ = $1; ((List)$1).add($2);}
		|	 							  {$$ = new ArrayList<Statement>();}
		;

sentencia: 'PRINT' expresion ';'													{$$ = new Print($2);}
	| 'PRINTSP' expresion ';'														{$$ = new Printsp($2);}
	| 'PRINTLN' expresion ';'														{$$ = new Println($2);}
	| 'READ' expresion ';'															{$$ = new Read($2);}
	| expresion '=' expresion ';'													{$$ = new Asignacion($1,$3);}	
	| 'IF' '(' expresion ')' '{' sentenciasOpt '}'									{$$ = new IfElse($3,$6);}
	| 'IF' '(' expresion ')' '{' sentenciasOpt '}' 'ELSE' '{' sentenciasOpt '}' 	{$$ = new IfElse($3,$6,$10);}
	| 'WHILE' '(' expresion ')' '{' sentenciasOpt '}'  								{$$ = new While($3,$6);}
	| 'RETURN' expresion ';' 														{$$ = new Return($2);}
	| 'RETURN' ';' 																	{$$ = new Return();}
	| 'IDENT' '(' expresionesOpt ')' ';'											{$$ = new Invocacion($1,$3);}
	;

expresionesOpt: expresiones		{$$ = $1;}
	|							{$$ = new ArrayList<Expression>();}
	;

expresiones: expresiones ',' expresion	{$$=$1;  ((List)$$).add($3); }
	|		 expresion					{$$= new ArrayList<Expression>(); ((List)$$).add($1); }  
	;

expresion: 'LITENT'								{$$ = new LitInt($1);}
	| 'LITREAL' 								{$$ = new LitReal($1);}
	| 'IDENT' 									{$$ = new Variable($1);}
	| 'LITCHAR' 								{$$ = new LitChar($1);}
	| expresion '+' expresion	  				{$$ = new ExpresionAritmetica($1,$2,$3);}
	| expresion '-' expresion  					{$$ = new ExpresionAritmetica($1,$2,$3);}
	| expresion '<' expresion 					{$$ = new Comparacion($1,$2,$3);}
	| expresion '>' expresion 					{$$ = new Comparacion($1,$2,$3);}
	| expresion '*' expresion  					{$$ = new ExpresionAritmetica($1,$2,$3);}
	| expresion '/' expresion 					{$$ = new ExpresionAritmetica($1,$2,$3);}
	| expresion '.' 'IDENT' 					{$$ = new AccesoStruct($1,$3);}
	| expresion 'MAYORIGUALQUE' expresion 		{$$ = new Comparacion($1,$2,$3);}
	| expresion 'MENORIGUALQUE' expresion 		{$$ = new Comparacion($1,$2,$3);}
	| expresion 'AND' expresion 				{$$ = new ExpresionLogica($1,$2,$3);}
	| expresion 'OR' expresion 					{$$ = new ExpresionLogica($1,$2,$3);}
	| expresion 'DISTINTO' expresion 			{$$ = new Comparacion($1,$2,$3);}
	| expresion 'IGUALQUE' expresion 			{$$ = new Comparacion($1,$2,$3);}
	| '-' expresion								{$$ = new MenosUnario($2);}
	| '!' expresion 							{$$ = new Not($2);}
	| '(' expresion ')' 						{$$ = $2;}
	| 'IDENT' '(' expresionesOpt ')' 			{$$ = new LlamadaFuncion($1,$3);}
	| expresion '['expresion ']' 				{$$ = new AccesoArray($1,$3);}
	| 'CAST' '<' tipo '>''(' expresion ')' 		{$$ = new Cast($3,$6);}
	;

%%
/* No es necesario modificar esta sección ------------------ */

public Parser(Yylex lex, GestorErrores gestor, boolean debug) {
	this(debug);
	this.lex = lex;
	this.gestor = gestor;
}

// Métodos de acceso para el main -------------
public int parse() { return yyparse(); }
public AST getAST() { return raiz; }

// Funciones requeridas por Yacc --------------
void yyerror(String msg) {
	Token lastToken = (Token) yylval;
	gestor.error("Sintáctico", "Token = " + lastToken.getToken() + ", lexema = \"" + lastToken.getLexeme() + "\". " + msg, lastToken.getStart());
}

int yylex() {
	try {
		int token = lex.yylex();
		yylval = new Token(token, lex.lexeme(), lex.line(), lex.column());
		return token;
	} catch (IOException e) {
		return -1;
	}
}

private Yylex lex;
private GestorErrores gestor;
private AST raiz;
