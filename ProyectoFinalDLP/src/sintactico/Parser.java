//### This file created by BYACC 1.8(/Java extension  1.14)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 5 "sintac.y"
package sintactico;

import java.io.*;
import java.util.*;
import ast.*;
import ast.definitions.*;
import ast.expressions.*;
import ast.statements.*;
import ast.types.*;
import main.*;

//#line 29 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//## **user defined:Object
String   yytext;//user variable to return contextual strings
Object yyval; //used to return semantic vals from action routines
Object yylval;//the 'lval' (result) I got from yylex()
Object valstk[] = new Object[YYSTACKSIZE];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
final void val_init()
{
  yyval=new Object();
  yylval=new Object();
  valptr=-1;
}
final void val_push(Object val)
{
  try {
    valptr++;
    valstk[valptr]=val;
  }
  catch (ArrayIndexOutOfBoundsException e) {
    int oldsize = valstk.length;
    int newsize = oldsize*2;
    Object[] newstack = new Object[newsize];
    System.arraycopy(valstk,0,newstack,0,oldsize);
    valstk = newstack;
    valstk[valptr]=val;
  }
}
final Object val_pop()
{
  return valstk[valptr--];
}
final void val_drop(int cnt)
{
  valptr -= cnt;
}
final Object val_peek(int relative)
{
  return valstk[valptr-relative];
}
//#### end semantic value section ####
public final static short OR=257;
public final static short AND=258;
public final static short MENORIGUALQUE=259;
public final static short MAYORIGUALQUE=260;
public final static short IGUALQUE=261;
public final static short DISTINTO=262;
public final static short ELSE=263;
public final static short VAR=264;
public final static short IDENT=265;
public final static short STRUCT=266;
public final static short INT=267;
public final static short REAL=268;
public final static short CHAR=269;
public final static short LITENT=270;
public final static short PRINT=271;
public final static short PRINTSP=272;
public final static short PRINTLN=273;
public final static short READ=274;
public final static short IF=275;
public final static short WHILE=276;
public final static short RETURN=277;
public final static short LITREAL=278;
public final static short LITCHAR=279;
public final static short CAST=280;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    2,    2,    2,    6,    6,    4,    3,
    9,    9,    8,    8,   11,   11,   12,    5,   13,   13,
   14,    7,    7,    7,    7,    7,   10,   10,   15,   15,
   15,   15,   15,   15,   15,   15,   15,   15,   15,   17,
   17,   18,   18,   16,   16,   16,   16,   16,   16,   16,
   16,   16,   16,   16,   16,   16,   16,   16,   16,   16,
   16,   16,   16,   16,   16,   16,
};
final static short yylen[] = {                            2,
    1,    2,    0,    1,    1,    1,    2,    0,    5,    9,
    2,    0,    1,    0,    3,    1,    3,    6,    2,    0,
    4,    1,    1,    1,    1,    4,    2,    0,    3,    3,
    3,    3,    4,    7,   11,    7,    3,    2,    5,    1,
    0,    3,    1,    1,    1,    1,    1,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    2,    2,    3,    4,    4,    7,
};
final static short yydefred[] = {                         3,
    0,    0,    0,    0,    0,    2,    4,    5,    6,    0,
    0,    0,    0,    0,    0,    0,   16,   20,    0,   25,
   22,   23,   24,    0,    0,    0,    0,    0,    0,    9,
   17,    0,    0,   15,    0,    0,   19,    0,   11,    8,
    0,   18,   26,    0,    0,    7,    0,   21,    0,    0,
    0,    0,   10,   44,    0,    0,    0,    0,    0,    0,
    0,   45,   47,    0,   27,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   38,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   63,    0,    0,    0,
   29,   30,   31,   32,    0,    0,   37,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   54,    0,    0,    0,    0,    0,    0,   33,
   65,   64,   39,    0,   28,   28,    0,    0,    0,    0,
    0,   36,   66,    0,   28,    0,   35,
};
final static short yydgoto[] = {                          1,
    2,    6,    7,    8,    9,   44,   24,   15,   33,   47,
   16,   17,   28,   37,   65,   66,   99,  100,
};
final static short yysindex[] = {                         0,
    0, -192, -263,  -37, -254,    0,    0,    0,    0,  -45,
 -243,  -89,  -58,  -15,    8,    2,    0,    0, -216,    0,
    0,    0,    0,   -2,  -58,    1, -243, -111,  -32,    0,
    0,  -58,  -67,    0,   10,   11,    0,  -58,    0,    0,
  -58,    0,    0, -189,   18,    0,  -33,    0,   67,   67,
   67,   38,    0,    0,   67,   67,   67,   67,   39,   40,
   64,    0,    0,   22,    0,  497,   43,    5,   41,  159,
   67,  519,  525,  547,  751,   67,   67,    0,  772,  -58,
   67,   67,   67,   67,   67,   67,   67,   67,   67,   67,
   67,   67,   67,   67, -180,   67,    0,  842,   45,   46,
    0,    0,    0,    0,  362,  384,    0,   27,  778,  863,
  863,   20,   20,   20,   20,   20,   20,    5,    5,  -41,
  -41,  802,    0,   50,   35,   67,  -28,  -25,   59,    0,
    0,    0,    0,  842,    0,    0,   67,  -17,   -1,  392,
 -162,    0,    0,  -21,    0,   15,    0,
};
final static short yyrindex[] = {                         0,
    0,  103,    0,    0,    0,    0,    0,    0,    0,    0,
   65,    0,    0,    0,    0,   69,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -18,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   31,    0,    0,    0,    0,    0,    0,
    0,  810,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   91,  -35,  100,    0,
   72,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   72,    0,  -26,    0,   73,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  -40,
  -24,  426,  440,  452,  462,  476,  487,  404,  417,  127,
  136,    0,    0,    0,  836,    0,    0,    0,    0,    0,
    0,    0,    0,   -3,    0,    0,    0,    0,    0,    0,
   48,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,    0,   71,    0,    0,    4,    0,    0, -105,
    0,   89,    0,    0,    0,  879,   21,    0,
};
final static int YYTABLESIZE=1125;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         50,
   58,   10,   11,   58,   95,   61,   51,   61,   61,   61,
   12,   49,   13,   36,   43,   50,   57,   43,   58,   57,
   58,   14,   51,   61,   61,   61,   61,   49,   31,  138,
  139,   50,   19,   18,   57,   39,   57,   42,   51,  146,
   42,   43,   25,   49,   45,   27,   92,   50,   26,   94,
   95,   93,   58,   29,   51,   40,   30,   61,   32,   49,
   38,   92,   90,   28,   91,   95,   93,   41,   57,   42,
   28,    3,    4,    5,    3,   28,   48,   71,   76,   77,
   34,   80,   96,  108,  123,  125,   95,   34,  129,  126,
  132,   53,   34,  133,  135,   94,   50,  136,  137,   50,
  144,  145,    1,   51,   12,   14,   51,  141,   49,   13,
   94,   49,   41,   40,   46,   34,  124,    0,    0,    0,
    0,    0,   78,  142,    0,    0,    0,    0,    0,    0,
    0,   46,   46,   46,   46,   46,   46,   46,    0,  147,
   62,   62,   62,   62,   62,    0,   62,    0,    0,   46,
   46,   46,   46,   35,    0,   28,    0,    0,   62,   62,
   62,   62,    0,    0,    0,    0,    0,   52,   52,   52,
   52,   52,   34,   52,    0,    0,   53,   53,   53,   53,
   53,   46,   53,   46,    0,   52,   52,   52,   52,    0,
   62,    0,   62,    0,   53,   53,   53,   53,    0,   97,
   92,   90,    0,   91,   95,   93,   20,    0,   21,   22,
   23,    0,    0,    0,    0,    0,   58,   58,   84,   52,
   85,   61,   61,   61,   61,   61,   61,    0,   53,    0,
    0,   52,   57,   57,    0,    0,   54,   55,   56,   57,
   58,   59,   60,   61,   62,   63,   64,   52,    0,   94,
    0,    0,   54,   55,   56,   57,   58,   59,   60,   61,
   62,   63,   64,   52,    0,    0,    0,    0,   54,   55,
   56,   57,   58,   59,   60,   61,   62,   63,   64,   52,
    0,    0,    0,    0,   54,   55,   56,   57,   58,   59,
   60,   61,   62,   63,   64,   28,    0,    0,    0,    0,
   28,   28,   28,   28,   28,   28,   28,   28,   28,   28,
   28,    0,   34,    0,    0,    0,    0,   34,   34,   34,
   34,   34,   34,   34,   34,   34,   34,   34,   67,    0,
    0,   67,    0,   54,    0,    0,   54,    0,    0,    0,
    0,   62,   63,   64,   62,   63,   64,   46,   46,   46,
   46,   46,   46,    0,    0,    0,   62,   62,   62,   62,
   62,   62,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   52,   52,   52,   52,   52,   52,    0,
    0,    0,   53,   53,   53,   53,   53,   53,    0,    0,
    0,    0,  127,   92,   90,    0,   91,   95,   93,    0,
    0,    0,    0,    0,    0,   82,   83,   86,   87,   88,
   89,   84,    0,   85,  128,   92,   90,    0,   91,   95,
   93,    0,  143,   92,   90,    0,   91,   95,   93,    0,
    0,    0,    0,   84,   48,   85,   48,   48,   48,    0,
    0,   84,   94,   85,    0,    0,    0,   49,    0,   49,
   49,   49,   48,   48,   48,   48,   50,    0,    0,   50,
    0,    0,    0,    0,   94,   49,   49,   49,   49,    0,
   51,    0,   94,   51,   50,   50,   50,   50,    0,    0,
    0,    0,   56,    0,    0,   56,   48,    0,   51,   51,
   51,   51,   55,    0,    0,   55,    0,    0,    0,   49,
   56,   56,   56,   56,    0,    0,   60,    0,   50,   60,
   55,   55,   55,   55,    0,    0,    0,   59,    0,    0,
   59,    0,   51,    0,   60,   60,   60,   60,   92,   90,
    0,   91,   95,   93,   56,   59,   59,   59,   59,    0,
    0,    0,    0,    0,   55,    0,   84,   81,   85,    0,
   92,   90,    0,   91,   95,   93,   92,   90,   60,   91,
   95,   93,    0,    0,    0,    0,    0,  101,   84,   59,
   85,    0,    0,  102,   84,    0,   85,   94,   92,   90,
    0,   91,   95,   93,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  103,   84,    0,   85,   94,
    0,    0,    0,    0,    0,   94,    0,    0,   82,   83,
   86,   87,   88,   89,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   94,    0,    0,
   82,   83,   86,   87,   88,   89,    0,    0,   82,   83,
   86,   87,   88,   89,    0,    0,    0,    0,    0,    0,
   48,   48,   48,   48,   48,   48,    0,    0,    0,    0,
    0,    0,    0,   49,   49,   49,   49,   49,   49,    0,
    0,    0,   50,   50,   50,   50,   50,   50,    0,    0,
    0,    0,    0,    0,    0,    0,   51,   51,   51,   51,
   51,   51,    0,    0,    0,    0,    0,    0,   56,   56,
   56,   56,   56,   56,    0,    0,    0,    0,   55,   55,
   55,   55,   55,   55,    0,    0,    0,    0,    0,    0,
    0,    0,   60,   60,   60,   60,   60,   60,    0,    0,
    0,    0,    0,   59,   59,   59,   59,   59,   59,    0,
    0,    0,    0,   82,   83,   86,   87,   88,   89,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   82,   83,   86,   87,   88,
   89,   82,   83,   86,   87,   88,   89,    0,    0,    0,
    0,    0,   92,   90,    0,   91,   95,   93,    0,    0,
    0,    0,    0,   82,   83,   86,   87,   88,   89,  104,
   84,    0,   85,   92,   90,    0,   91,   95,   93,   92,
   90,    0,   91,   95,   93,    0,    0,    0,    0,    0,
  107,   84,    0,   85,    0,    0,  130,   84,    0,   85,
    0,   94,    0,   92,   90,    0,   91,   95,   93,    0,
    0,   46,   46,    0,   46,   46,   46,    0,    0,    0,
    0,   84,   94,   85,    0,    0,    0,    0,   94,   46,
   46,   46,    0,    0,    0,    0,    0,   64,   64,    0,
   64,   64,   64,   92,   90,    0,   91,   95,   93,    0,
    0,    0,   94,    0,  131,   64,   64,   64,    0,    0,
   46,   84,    0,   85,   92,   90,    0,   91,   95,   93,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   84,    0,   85,    0,   64,   68,   69,   70,
    0,    0,   94,   72,   73,   74,   75,    0,    0,   79,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   98,
    0,    0,    0,   94,  105,  106,    0,    0,    0,  109,
  110,  111,  112,  113,  114,  115,  116,  117,  118,  119,
  120,  121,  122,    0,   98,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  134,    0,    0,   82,   83,   86,
   87,   88,   89,    0,    0,  140,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   82,   83,
   86,   87,   88,   89,   82,   83,   86,   87,   88,   89,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   82,   83,
   86,   87,   88,   89,    0,    0,   46,   46,   46,   46,
   46,   46,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   64,   64,   64,   64,   64,   64,   82,   83,
   86,   87,   88,   89,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   86,   87,   88,   89,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   41,  265,   40,   44,   46,   41,   40,   43,   44,   45,
  265,   45,   58,  125,   41,   33,   41,   44,   59,   44,
   61,  265,   40,   59,   60,   61,   62,   45,   25,  135,
  136,   33,   91,  123,   59,   32,   61,   41,   40,  145,
   44,   38,   58,   45,   41,   44,   42,   33,   41,   91,
   46,   47,   93,  270,   40,  123,   59,   93,   58,   45,
   93,   42,   43,   33,   45,   46,   47,   58,   93,   59,
   40,  264,  265,  266,  264,   45,   59,   40,   40,   40,
   33,   60,   40,   80,  265,   41,   46,   40,   62,   44,
   41,  125,   45,   59,  123,   91,   33,  123,   40,   33,
  263,  123,    0,   40,  123,   41,   40,  125,   45,   41,
   91,   45,   41,   41,   44,   27,   96,   -1,   -1,   -1,
   -1,   -1,   59,  125,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   41,   42,   43,   44,   45,   46,   47,   -1,  125,
   41,   42,   43,   44,   45,   -1,   47,   -1,   -1,   59,
   60,   61,   62,  265,   -1,  125,   -1,   -1,   59,   60,
   61,   62,   -1,   -1,   -1,   -1,   -1,   41,   42,   43,
   44,   45,  125,   47,   -1,   -1,   41,   42,   43,   44,
   45,   91,   47,   93,   -1,   59,   60,   61,   62,   -1,
   91,   -1,   93,   -1,   59,   60,   61,   62,   -1,   41,
   42,   43,   -1,   45,   46,   47,  265,   -1,  267,  268,
  269,   -1,   -1,   -1,   -1,   -1,  257,  258,   60,   93,
   62,  257,  258,  259,  260,  261,  262,   -1,   93,   -1,
   -1,  265,  257,  258,   -1,   -1,  270,  271,  272,  273,
  274,  275,  276,  277,  278,  279,  280,  265,   -1,   91,
   -1,   -1,  270,  271,  272,  273,  274,  275,  276,  277,
  278,  279,  280,  265,   -1,   -1,   -1,   -1,  270,  271,
  272,  273,  274,  275,  276,  277,  278,  279,  280,  265,
   -1,   -1,   -1,   -1,  270,  271,  272,  273,  274,  275,
  276,  277,  278,  279,  280,  265,   -1,   -1,   -1,   -1,
  270,  271,  272,  273,  274,  275,  276,  277,  278,  279,
  280,   -1,  265,   -1,   -1,   -1,   -1,  270,  271,  272,
  273,  274,  275,  276,  277,  278,  279,  280,  265,   -1,
   -1,  265,   -1,  270,   -1,   -1,  270,   -1,   -1,   -1,
   -1,  278,  279,  280,  278,  279,  280,  257,  258,  259,
  260,  261,  262,   -1,   -1,   -1,  257,  258,  259,  260,
  261,  262,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  257,  258,  259,  260,  261,  262,   -1,
   -1,   -1,  257,  258,  259,  260,  261,  262,   -1,   -1,
   -1,   -1,   41,   42,   43,   -1,   45,   46,   47,   -1,
   -1,   -1,   -1,   -1,   -1,  257,  258,  259,  260,  261,
  262,   60,   -1,   62,   41,   42,   43,   -1,   45,   46,
   47,   -1,   41,   42,   43,   -1,   45,   46,   47,   -1,
   -1,   -1,   -1,   60,   41,   62,   43,   44,   45,   -1,
   -1,   60,   91,   62,   -1,   -1,   -1,   41,   -1,   43,
   44,   45,   59,   60,   61,   62,   41,   -1,   -1,   44,
   -1,   -1,   -1,   -1,   91,   59,   60,   61,   62,   -1,
   41,   -1,   91,   44,   59,   60,   61,   62,   -1,   -1,
   -1,   -1,   41,   -1,   -1,   44,   93,   -1,   59,   60,
   61,   62,   41,   -1,   -1,   44,   -1,   -1,   -1,   93,
   59,   60,   61,   62,   -1,   -1,   41,   -1,   93,   44,
   59,   60,   61,   62,   -1,   -1,   -1,   41,   -1,   -1,
   44,   -1,   93,   -1,   59,   60,   61,   62,   42,   43,
   -1,   45,   46,   47,   93,   59,   60,   61,   62,   -1,
   -1,   -1,   -1,   -1,   93,   -1,   60,   61,   62,   -1,
   42,   43,   -1,   45,   46,   47,   42,   43,   93,   45,
   46,   47,   -1,   -1,   -1,   -1,   -1,   59,   60,   93,
   62,   -1,   -1,   59,   60,   -1,   62,   91,   42,   43,
   -1,   45,   46,   47,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   59,   60,   -1,   62,   91,
   -1,   -1,   -1,   -1,   -1,   91,   -1,   -1,  257,  258,
  259,  260,  261,  262,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   91,   -1,   -1,
  257,  258,  259,  260,  261,  262,   -1,   -1,  257,  258,
  259,  260,  261,  262,   -1,   -1,   -1,   -1,   -1,   -1,
  257,  258,  259,  260,  261,  262,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  257,  258,  259,  260,  261,  262,   -1,
   -1,   -1,  257,  258,  259,  260,  261,  262,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,  260,
  261,  262,   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,
  259,  260,  261,  262,   -1,   -1,   -1,   -1,  257,  258,
  259,  260,  261,  262,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  257,  258,  259,  260,  261,  262,   -1,   -1,
   -1,   -1,   -1,  257,  258,  259,  260,  261,  262,   -1,
   -1,   -1,   -1,  257,  258,  259,  260,  261,  262,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  257,  258,  259,  260,  261,
  262,  257,  258,  259,  260,  261,  262,   -1,   -1,   -1,
   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,   -1,   -1,  257,  258,  259,  260,  261,  262,   59,
   60,   -1,   62,   42,   43,   -1,   45,   46,   47,   42,
   43,   -1,   45,   46,   47,   -1,   -1,   -1,   -1,   -1,
   59,   60,   -1,   62,   -1,   -1,   59,   60,   -1,   62,
   -1,   91,   -1,   42,   43,   -1,   45,   46,   47,   -1,
   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,   -1,
   -1,   60,   91,   62,   -1,   -1,   -1,   -1,   91,   60,
   61,   62,   -1,   -1,   -1,   -1,   -1,   42,   43,   -1,
   45,   46,   47,   42,   43,   -1,   45,   46,   47,   -1,
   -1,   -1,   91,   -1,   93,   60,   61,   62,   -1,   -1,
   91,   60,   -1,   62,   42,   43,   -1,   45,   46,   47,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   60,   -1,   62,   -1,   91,   49,   50,   51,
   -1,   -1,   91,   55,   56,   57,   58,   -1,   -1,   61,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   71,
   -1,   -1,   -1,   91,   76,   77,   -1,   -1,   -1,   81,
   82,   83,   84,   85,   86,   87,   88,   89,   90,   91,
   92,   93,   94,   -1,   96,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  126,   -1,   -1,  257,  258,  259,
  260,  261,  262,   -1,   -1,  137,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,
  259,  260,  261,  262,  257,  258,  259,  260,  261,  262,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,
  259,  260,  261,  262,   -1,   -1,  257,  258,  259,  260,
  261,  262,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  257,  258,  259,  260,  261,  262,  257,  258,
  259,  260,  261,  262,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  259,  260,  261,  262,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=280;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,null,null,null,"'('","')'","'*'","'+'",
"','","'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,"':'",
"';'","'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,"\"OR\"","\"AND\"",
"\"MENORIGUALQUE\"","\"MAYORIGUALQUE\"","\"IGUALQUE\"","\"DISTINTO\"",
"\"ELSE\"","\"VAR\"","\"IDENT\"","\"STRUCT\"","\"INT\"","\"REAL\"","\"CHAR\"",
"\"LITENT\"","\"PRINT\"","\"PRINTSP\"","\"PRINTLN\"","\"READ\"","\"IF\"",
"\"WHILE\"","\"RETURN\"","\"LITREAL\"","\"LITCHAR\"","\"CAST\"",
};
final static String yyrule[] = {
"$accept : program",
"program : definiciones",
"definiciones : definiciones definicion",
"definiciones :",
"definicion : defFuncion",
"definicion : defVariable",
"definicion : defStruct",
"defVariablesOpt : defVariablesOpt defVariable",
"defVariablesOpt :",
"defVariable : \"VAR\" \"IDENT\" ':' tipo ';'",
"defFuncion : \"IDENT\" '(' parametrosOpt ')' retornoOpt '{' defVariablesOpt sentenciasOpt '}'",
"retornoOpt : ':' tipo",
"retornoOpt :",
"parametrosOpt : defParametros",
"parametrosOpt :",
"defParametros : defParametros ',' defParametro",
"defParametros : defParametro",
"defParametro : \"IDENT\" ':' tipo",
"defStruct : \"STRUCT\" \"IDENT\" '{' camposOpt '}' ';'",
"camposOpt : camposOpt defCampo",
"camposOpt :",
"defCampo : \"IDENT\" ':' tipo ';'",
"tipo : \"INT\"",
"tipo : \"REAL\"",
"tipo : \"CHAR\"",
"tipo : \"IDENT\"",
"tipo : '[' \"LITENT\" ']' tipo",
"sentenciasOpt : sentenciasOpt sentencia",
"sentenciasOpt :",
"sentencia : \"PRINT\" expresion ';'",
"sentencia : \"PRINTSP\" expresion ';'",
"sentencia : \"PRINTLN\" expresion ';'",
"sentencia : \"READ\" expresion ';'",
"sentencia : expresion '=' expresion ';'",
"sentencia : \"IF\" '(' expresion ')' '{' sentenciasOpt '}'",
"sentencia : \"IF\" '(' expresion ')' '{' sentenciasOpt '}' \"ELSE\" '{' sentenciasOpt '}'",
"sentencia : \"WHILE\" '(' expresion ')' '{' sentenciasOpt '}'",
"sentencia : \"RETURN\" expresion ';'",
"sentencia : \"RETURN\" ';'",
"sentencia : \"IDENT\" '(' expresionesOpt ')' ';'",
"expresionesOpt : expresiones",
"expresionesOpt :",
"expresiones : expresiones ',' expresion",
"expresiones : expresion",
"expresion : \"LITENT\"",
"expresion : \"LITREAL\"",
"expresion : \"IDENT\"",
"expresion : \"LITCHAR\"",
"expresion : expresion '+' expresion",
"expresion : expresion '-' expresion",
"expresion : expresion '<' expresion",
"expresion : expresion '>' expresion",
"expresion : expresion '*' expresion",
"expresion : expresion '/' expresion",
"expresion : expresion '.' \"IDENT\"",
"expresion : expresion \"MAYORIGUALQUE\" expresion",
"expresion : expresion \"MENORIGUALQUE\" expresion",
"expresion : expresion \"AND\" expresion",
"expresion : expresion \"OR\" expresion",
"expresion : expresion \"DISTINTO\" expresion",
"expresion : expresion \"IGUALQUE\" expresion",
"expresion : '-' expresion",
"expresion : '!' expresion",
"expresion : '(' expresion ')'",
"expresion : \"IDENT\" '(' expresionesOpt ')'",
"expresion : expresion '[' expresion ']'",
"expresion : \"CAST\" '<' tipo '>' '(' expresion ')'",
};

//#line 133 "sintac.y"
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
//#line 566 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 34 "sintac.y"
{raiz = new Program(val_peek(0));}
break;
case 2:
//#line 37 "sintac.y"
{yyval = val_peek(1); ((List)val_peek(1)).add(val_peek(0));}
break;
case 3:
//#line 38 "sintac.y"
{yyval = new ArrayList<Definition>();}
break;
case 4:
//#line 40 "sintac.y"
{yyval = val_peek(0);}
break;
case 5:
//#line 41 "sintac.y"
{yyval = val_peek(0);}
break;
case 6:
//#line 42 "sintac.y"
{yyval = val_peek(0);}
break;
case 7:
//#line 45 "sintac.y"
{yyval = val_peek(1); ((List)val_peek(1)).add(val_peek(0));}
break;
case 8:
//#line 46 "sintac.y"
{yyval = new ArrayList<Definition>();}
break;
case 9:
//#line 49 "sintac.y"
{yyval = new DefVariable(val_peek(3),val_peek(1));}
break;
case 10:
//#line 51 "sintac.y"
{yyval = new DefFuncion(val_peek(8),new TipoFuncion(val_peek(4), val_peek(6)),val_peek(2),val_peek(1));}
break;
case 11:
//#line 54 "sintac.y"
{yyval = new TipoFuncion(val_peek(0));}
break;
case 12:
//#line 55 "sintac.y"
{yyval = new VoidTipo();}
break;
case 13:
//#line 57 "sintac.y"
{yyval = val_peek(0);}
break;
case 14:
//#line 58 "sintac.y"
{yyval = new ArrayList<Definition>();}
break;
case 15:
//#line 60 "sintac.y"
{yyval=val_peek(2);  ((List)yyval).add(val_peek(0)); }
break;
case 16:
//#line 61 "sintac.y"
{yyval= new ArrayList<Definition>(); ((List)yyval).add(val_peek(0)); }
break;
case 17:
//#line 63 "sintac.y"
{yyval = new DefVariable(val_peek(2),val_peek(0));}
break;
case 18:
//#line 66 "sintac.y"
{yyval = new DefStruct(val_peek(4),new TipoStruct(val_peek(4)),val_peek(2));}
break;
case 19:
//#line 69 "sintac.y"
{ yyval = val_peek(1); ((List)val_peek(1)).add(val_peek(0));}
break;
case 20:
//#line 70 "sintac.y"
{yyval = new ArrayList<Definition>();}
break;
case 21:
//#line 72 "sintac.y"
{yyval = new DefCampo(val_peek(3),val_peek(1));}
break;
case 22:
//#line 75 "sintac.y"
{yyval = IntTipo.getInstance();}
break;
case 23:
//#line 76 "sintac.y"
{yyval = RealTipo.getInstance();}
break;
case 24:
//#line 77 "sintac.y"
{yyval = CharTipo.getInstance();}
break;
case 25:
//#line 78 "sintac.y"
{yyval = new TipoStruct(val_peek(0));}
break;
case 26:
//#line 79 "sintac.y"
{yyval = new ArrayTipo(val_peek(2),val_peek(0));}
break;
case 27:
//#line 82 "sintac.y"
{yyval = val_peek(1); ((List)val_peek(1)).add(val_peek(0));}
break;
case 28:
//#line 83 "sintac.y"
{yyval = new ArrayList<Statement>();}
break;
case 29:
//#line 86 "sintac.y"
{yyval = new Print(val_peek(1));}
break;
case 30:
//#line 87 "sintac.y"
{yyval = new Printsp(val_peek(1));}
break;
case 31:
//#line 88 "sintac.y"
{yyval = new Println(val_peek(1));}
break;
case 32:
//#line 89 "sintac.y"
{yyval = new Read(val_peek(1));}
break;
case 33:
//#line 90 "sintac.y"
{yyval = new Asignacion(val_peek(3),val_peek(1));}
break;
case 34:
//#line 91 "sintac.y"
{yyval = new IfElse(val_peek(4),val_peek(1));}
break;
case 35:
//#line 92 "sintac.y"
{yyval = new IfElse(val_peek(8),val_peek(5),val_peek(1));}
break;
case 36:
//#line 93 "sintac.y"
{yyval = new While(val_peek(4),val_peek(1));}
break;
case 37:
//#line 94 "sintac.y"
{yyval = new Return(val_peek(1));}
break;
case 38:
//#line 95 "sintac.y"
{yyval = new Return();}
break;
case 39:
//#line 96 "sintac.y"
{yyval = new Invocacion(val_peek(4),val_peek(2));}
break;
case 40:
//#line 99 "sintac.y"
{yyval = val_peek(0);}
break;
case 41:
//#line 100 "sintac.y"
{yyval = new ArrayList<Expression>();}
break;
case 42:
//#line 103 "sintac.y"
{yyval=val_peek(2);  ((List)yyval).add(val_peek(0)); }
break;
case 43:
//#line 104 "sintac.y"
{yyval= new ArrayList<Expression>(); ((List)yyval).add(val_peek(0)); }
break;
case 44:
//#line 107 "sintac.y"
{yyval = new LitInt(val_peek(0));}
break;
case 45:
//#line 108 "sintac.y"
{yyval = new LitReal(val_peek(0));}
break;
case 46:
//#line 109 "sintac.y"
{yyval = new Variable(val_peek(0));}
break;
case 47:
//#line 110 "sintac.y"
{yyval = new LitChar(val_peek(0));}
break;
case 48:
//#line 111 "sintac.y"
{yyval = new ExpresionAritmetica(val_peek(2),val_peek(1),val_peek(0));}
break;
case 49:
//#line 112 "sintac.y"
{yyval = new ExpresionAritmetica(val_peek(2),val_peek(1),val_peek(0));}
break;
case 50:
//#line 113 "sintac.y"
{yyval = new Comparacion(val_peek(2),val_peek(1),val_peek(0));}
break;
case 51:
//#line 114 "sintac.y"
{yyval = new Comparacion(val_peek(2),val_peek(1),val_peek(0));}
break;
case 52:
//#line 115 "sintac.y"
{yyval = new ExpresionAritmetica(val_peek(2),val_peek(1),val_peek(0));}
break;
case 53:
//#line 116 "sintac.y"
{yyval = new ExpresionAritmetica(val_peek(2),val_peek(1),val_peek(0));}
break;
case 54:
//#line 117 "sintac.y"
{yyval = new AccesoStruct(val_peek(2),val_peek(0));}
break;
case 55:
//#line 118 "sintac.y"
{yyval = new Comparacion(val_peek(2),val_peek(1),val_peek(0));}
break;
case 56:
//#line 119 "sintac.y"
{yyval = new Comparacion(val_peek(2),val_peek(1),val_peek(0));}
break;
case 57:
//#line 120 "sintac.y"
{yyval = new ExpresionLogica(val_peek(2),val_peek(1),val_peek(0));}
break;
case 58:
//#line 121 "sintac.y"
{yyval = new ExpresionLogica(val_peek(2),val_peek(1),val_peek(0));}
break;
case 59:
//#line 122 "sintac.y"
{yyval = new Comparacion(val_peek(2),val_peek(1),val_peek(0));}
break;
case 60:
//#line 123 "sintac.y"
{yyval = new Comparacion(val_peek(2),val_peek(1),val_peek(0));}
break;
case 61:
//#line 124 "sintac.y"
{yyval = new MenosUnario(val_peek(0));}
break;
case 62:
//#line 125 "sintac.y"
{yyval = new Not(val_peek(0));}
break;
case 63:
//#line 126 "sintac.y"
{yyval = val_peek(1);}
break;
case 64:
//#line 127 "sintac.y"
{yyval = new LlamadaFuncion(val_peek(3),val_peek(1));}
break;
case 65:
//#line 128 "sintac.y"
{yyval = new AccesoArray(val_peek(3),val_peek(1));}
break;
case 66:
//#line 129 "sintac.y"
{yyval = new Cast(val_peek(4),val_peek(1));}
break;
//#line 978 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
