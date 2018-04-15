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
public final static short LITENT=267;
public final static short INT=268;
public final static short REAL=269;
public final static short CHAR=270;
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
    3,    8,    8,   10,   10,   11,    5,   12,   12,   13,
    7,    7,    7,   14,   14,   14,    9,    9,   15,   15,
   15,   15,   15,   15,   15,   15,   15,   15,   15,   17,
   17,   18,   18,   16,   16,   16,   16,   16,   16,   16,
   16,   16,   16,   16,   16,   16,   16,   16,   16,   16,
   16,   16,   16,   16,   16,   16,
};
final static short yylen[] = {                            2,
    1,    2,    0,    1,    1,    1,    2,    0,    5,   10,
    8,    1,    0,    3,    1,    3,    6,    2,    0,    4,
    1,    1,    4,    1,    1,    1,    2,    0,    3,    3,
    3,    3,    4,    7,   11,    7,    3,    2,    5,    1,
    0,    3,    1,    1,    1,    1,    1,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    2,    2,    3,    4,    4,    7,
};
final static short yydefred[] = {                         3,
    0,    0,    0,    0,    0,    2,    4,    5,    6,    0,
    0,    0,    0,    0,    0,    0,   15,   19,    0,   22,
   24,   25,   26,    0,   21,    0,    0,    0,    0,    0,
    9,   16,    0,    8,   14,    0,    0,   18,    0,    0,
    0,    0,   17,   23,    8,    7,    0,    0,    0,    0,
    0,    0,    0,   11,   44,    0,    0,    0,    0,    0,
    0,    0,   45,   47,    0,   27,    0,   20,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   38,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   10,    0,
   63,    0,    0,    0,   29,   30,   31,   32,    0,    0,
   37,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   54,    0,    0,    0,
    0,    0,    0,   33,   65,   64,   39,    0,   28,   28,
    0,    0,    0,    0,    0,   36,   66,    0,   28,    0,
   35,
};
final static short yydgoto[] = {                          1,
    2,    6,    7,   46,    9,   41,   24,   15,   47,   16,
   17,   29,   38,   25,   66,   67,  103,  104,
};
final static short yysindex[] = {                         0,
    0, -240, -259,  -22, -235,    0,    0,    0,    0,  -23,
 -229,  -82,  -74,  -16,   11,   12,    0,    0, -222,    0,
    0,    0,    0,   -2,    0,  -74,  -50, -229, -123,  -28,
    0,    0,  -74,    0,    0,    8,    3,    0,  -74,  -64,
 -196,  -74,    0,    0,    0,    0,  -33,   10, -196,   83,
   83,   83,   32,    0,    0,   83,   83,   83,   83,   34,
   35,   67,    0,    0,   18,    0,  337,    0,  -17,   37,
   -9,   33,  369,   83,  512,  520,  741,  747,   83,   83,
    0,  768,  -74,   83,   83,   83,   83,   83,   83,   83,
   83,   83,   83,   83,   83,   83,   83, -185,    0,   83,
    0,  828,   42,   45,    0,    0,    0,    0,  376,  401,
    0,   24,  774,  834,  834,    4,    4,    4,    4,    4,
    4,   -9,   -9,  -37,  -37,  798,    0,   49,   39,   83,
  -32,  -29,   56,    0,    0,    0,    0,  828,    0,    0,
   83,   -1,   15,  408, -164,    0,    0,  -21,    0,   31,
    0,
};
final static short yyrindex[] = {                         0,
    0,  101,    0,    0,    0,    0,    0,    0,    0,    0,
   62,    0,    0,    0,    0,   69,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   48,    0,    0,    0,    0,    0,    0,    0,   48,    0,
    0,    0,  694,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  107,
  -40,  116,    0,   70,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   70,
    0,  -31,    0,   72,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  -30,   26,  415,  450,  456,  463,  485,
  491,  421,  428,  143,  347,    0,    0,    0,  806,    0,
    0,    0,    0,    0,    0,    0,    0,   17,    0,    0,
    0,    0,    0,    0,   64,    0,    0,    0,    0,    0,
    0,
};
final static short yygindex[] = {                         0,
    0,    0,    0,  112,    0,   73,    1,    0,  -34,    0,
   89,    0,    0,    0,    0,  848,   20,    0,
};
final static int YYTABLESIZE=1096;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         51,
   61,   37,   61,   61,   61,   10,   52,   33,   98,   43,
   58,   50,   43,   58,   69,   51,   19,   11,   61,   61,
   61,   61,   52,    3,    4,    5,   32,   50,   58,   12,
   58,   51,   95,   40,   13,   14,   98,   96,   52,   44,
   18,   26,   48,   50,   30,   95,   93,   51,   94,   98,
   96,   27,   61,   97,   52,   28,   31,   42,   45,   50,
   42,   43,   58,   51,   39,   42,   57,    3,   68,   57,
   52,   74,   34,   79,   80,   50,  100,   83,   98,  127,
   28,   97,  129,  112,   57,  133,   57,   28,  130,  136,
  139,   54,   28,  140,   97,  141,   34,  137,  148,   51,
    1,  149,   13,   34,  142,  143,   52,   99,   34,   12,
   41,   50,   40,    8,  150,   51,   35,   49,   57,  128,
    0,    0,   52,  145,    0,   81,    0,   50,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  146,
    0,   36,    0,    0,    0,    0,    0,   46,   46,   46,
   46,   46,   46,   46,    0,  151,   62,   62,   62,   62,
   62,    0,   62,    0,    0,   46,   46,   46,   46,    0,
    0,    0,   28,    0,   62,   62,   62,   62,    0,    0,
    0,    0,    0,   52,   52,   52,   52,   52,   34,   52,
   20,    0,    0,   21,   22,   23,    0,   46,    0,   46,
    0,   52,   52,   52,   52,    0,   62,    0,   62,    0,
    0,    0,    0,    0,    0,    0,   61,   61,   61,   61,
   61,   61,    0,    0,    0,    0,   58,   58,    0,    0,
    0,   53,    0,   55,    0,   52,    0,   56,   57,   58,
   59,   60,   61,   62,   63,   64,   65,   53,    0,   55,
    0,    0,    0,   56,   57,   58,   59,   60,   61,   62,
   63,   64,   65,   53,    0,   55,    0,    0,    0,   56,
   57,   58,   59,   60,   61,   62,   63,   64,   65,   53,
    0,   55,   57,   57,    0,   56,   57,   58,   59,   60,
   61,   62,   63,   64,   65,   53,    0,   55,    0,    0,
    0,   56,   57,   58,   59,   60,   61,   62,   63,   64,
   65,    0,   28,    0,   28,    0,    0,    0,   28,   28,
   28,   28,   28,   28,   28,   28,   28,   28,   34,    0,
   34,   70,    0,   55,   34,   34,   34,   34,   34,   34,
   34,   34,   34,   34,   63,   64,   65,   70,    0,   55,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   63,   64,   65,   46,   46,   46,   46,   46,   46,    0,
    0,    0,   62,   62,   62,   62,   62,   62,   95,   93,
    0,   94,   98,   96,    0,    0,    0,   53,   53,   53,
   53,   53,    0,   53,    0,    0,   87,   84,   88,   52,
   52,   52,   52,   52,   52,   53,   53,   53,   53,  101,
   95,   93,    0,   94,   98,   96,  131,   95,   93,    0,
   94,   98,   96,    0,    0,    0,    0,   97,   87,    0,
   88,    0,    0,    0,    0,   87,    0,   88,    0,   53,
    0,  132,   95,   93,    0,   94,   98,   96,  147,   95,
   93,    0,   94,   98,   96,   50,    0,    0,   50,   97,
   87,   48,   88,   48,   48,   48,   97,   87,   49,   88,
   49,   49,   49,   50,   50,   50,   50,    0,    0,   48,
   48,   48,   48,    0,    0,    0,   49,   49,   49,   49,
   51,   97,    0,   51,    0,    0,   56,    0,   97,   56,
    0,    0,    0,   55,    0,    0,   55,   50,   51,   51,
   51,   51,    0,   48,   56,   56,   56,   56,    0,    0,
   49,   55,   55,   55,   55,   60,    0,    0,   60,    0,
    0,   59,    0,    0,   59,    0,    0,    0,    0,    0,
    0,    0,   51,   60,   60,   60,   60,    0,   56,   59,
   59,   59,   59,   95,   93,   55,   94,   98,   96,    0,
    0,   95,   93,    0,   94,   98,   96,    0,    0,    0,
  105,   87,    0,   88,    0,    0,    0,   60,  106,   87,
    0,   88,    0,   59,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   85,   86,   89,   90,   91,   92,    0,
    0,    0,   97,   53,   53,   53,   53,   53,   53,    0,
   97,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   85,   86,   89,   90,   91,
   92,    0,   85,   86,   89,   90,   91,   92,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   85,   86,   89,
   90,   91,   92,    0,   85,   86,   89,   90,   91,   92,
    0,   50,   50,   50,   50,   50,   50,   48,   48,   48,
   48,   48,   48,    0,   49,   49,   49,   49,   49,   49,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   51,   51,   51,   51,
   51,   51,   56,   56,   56,   56,   56,   56,    0,   55,
   55,   55,   55,   55,   55,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   46,   46,    0,   46,   46,
   46,   60,   60,   60,   60,   60,   60,   59,   59,   59,
   59,   59,   59,   46,   46,   46,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   85,   86,
   89,   90,   91,   92,    0,    0,   85,   86,   89,   90,
   91,   92,   95,   93,   46,   94,   98,   96,   95,   93,
    0,   94,   98,   96,    0,    0,    0,    0,    0,  107,
   87,    0,   88,    0,    0,  108,   87,    0,   88,   95,
   93,    0,   94,   98,   96,   95,   93,    0,   94,   98,
   96,    0,    0,    0,    0,    0,  111,   87,    0,   88,
    0,   97,  134,   87,    0,   88,    0,   97,    0,   95,
   93,    0,   94,   98,   96,    0,    0,   64,   64,    0,
   64,   64,   64,    0,    0,    0,    0,   87,   97,   88,
    0,    0,    0,    0,   97,   64,   64,   64,    0,   95,
   93,    0,   94,   98,   96,   95,   93,    0,   94,   98,
   96,    0,    0,    0,    0,    0,    0,   87,   97,   88,
  135,    0,    0,   87,    0,   88,   64,   71,   72,   73,
    0,    0,    0,   75,   76,   77,   78,    0,    0,   82,
    0,    0,    0,    0,    0,    0,    0,    0,   97,    0,
    0,  102,    0,    0,   97,    0,  109,  110,    0,    0,
    0,  113,  114,  115,  116,  117,  118,  119,  120,  121,
  122,  123,  124,  125,  126,    0,    0,  102,    0,    0,
   46,   46,   46,   46,   46,   46,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  138,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  144,    0,
    0,    0,    0,    0,    0,    0,    0,   85,   86,   89,
   90,   91,   92,   85,   86,   89,   90,   91,   92,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   85,   86,   89,   90,   91,   92,
   85,   86,   89,   90,   91,   92,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   85,   86,   89,   90,   91,   92,
    0,    0,   64,   64,   64,   64,   64,   64,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   85,   86,   89,   90,   91,   92,
    0,    0,   89,   90,   91,   92,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   41,  125,   43,   44,   45,  265,   40,   58,   46,   41,
   41,   45,   44,   44,   49,   33,   91,   40,   59,   60,
   61,   62,   40,  264,  265,  266,   26,   45,   59,  265,
   61,   33,   42,   33,   58,  265,   46,   47,   40,   39,
  123,   58,   42,   45,  267,   42,   43,   33,   45,   46,
   47,   41,   93,   91,   40,   44,   59,   41,  123,   45,
   44,   59,   93,   33,   93,   58,   41,  264,   59,   44,
   40,   40,  123,   40,   40,   45,   40,   60,   46,  265,
   33,   91,   41,   83,   59,   62,   61,   40,   44,   41,
  123,  125,   45,  123,   91,   40,   33,   59,  263,   33,
    0,  123,   41,   40,  139,  140,   40,  125,   45,   41,
   41,   45,   41,    2,  149,   33,   28,   45,   93,  100,
   -1,   -1,   40,  125,   -1,   59,   -1,   45,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  125,
   -1,  265,   -1,   -1,   -1,   -1,   -1,   41,   42,   43,
   44,   45,   46,   47,   -1,  125,   41,   42,   43,   44,
   45,   -1,   47,   -1,   -1,   59,   60,   61,   62,   -1,
   -1,   -1,  125,   -1,   59,   60,   61,   62,   -1,   -1,
   -1,   -1,   -1,   41,   42,   43,   44,   45,  125,   47,
  265,   -1,   -1,  268,  269,  270,   -1,   91,   -1,   93,
   -1,   59,   60,   61,   62,   -1,   91,   -1,   93,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,  260,
  261,  262,   -1,   -1,   -1,   -1,  257,  258,   -1,   -1,
   -1,  265,   -1,  267,   -1,   93,   -1,  271,  272,  273,
  274,  275,  276,  277,  278,  279,  280,  265,   -1,  267,
   -1,   -1,   -1,  271,  272,  273,  274,  275,  276,  277,
  278,  279,  280,  265,   -1,  267,   -1,   -1,   -1,  271,
  272,  273,  274,  275,  276,  277,  278,  279,  280,  265,
   -1,  267,  257,  258,   -1,  271,  272,  273,  274,  275,
  276,  277,  278,  279,  280,  265,   -1,  267,   -1,   -1,
   -1,  271,  272,  273,  274,  275,  276,  277,  278,  279,
  280,   -1,  265,   -1,  267,   -1,   -1,   -1,  271,  272,
  273,  274,  275,  276,  277,  278,  279,  280,  265,   -1,
  267,  265,   -1,  267,  271,  272,  273,  274,  275,  276,
  277,  278,  279,  280,  278,  279,  280,  265,   -1,  267,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  278,  279,  280,  257,  258,  259,  260,  261,  262,   -1,
   -1,   -1,  257,  258,  259,  260,  261,  262,   42,   43,
   -1,   45,   46,   47,   -1,   -1,   -1,   41,   42,   43,
   44,   45,   -1,   47,   -1,   -1,   60,   61,   62,  257,
  258,  259,  260,  261,  262,   59,   60,   61,   62,   41,
   42,   43,   -1,   45,   46,   47,   41,   42,   43,   -1,
   45,   46,   47,   -1,   -1,   -1,   -1,   91,   60,   -1,
   62,   -1,   -1,   -1,   -1,   60,   -1,   62,   -1,   93,
   -1,   41,   42,   43,   -1,   45,   46,   47,   41,   42,
   43,   -1,   45,   46,   47,   41,   -1,   -1,   44,   91,
   60,   41,   62,   43,   44,   45,   91,   60,   41,   62,
   43,   44,   45,   59,   60,   61,   62,   -1,   -1,   59,
   60,   61,   62,   -1,   -1,   -1,   59,   60,   61,   62,
   41,   91,   -1,   44,   -1,   -1,   41,   -1,   91,   44,
   -1,   -1,   -1,   41,   -1,   -1,   44,   93,   59,   60,
   61,   62,   -1,   93,   59,   60,   61,   62,   -1,   -1,
   93,   59,   60,   61,   62,   41,   -1,   -1,   44,   -1,
   -1,   41,   -1,   -1,   44,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   93,   59,   60,   61,   62,   -1,   93,   59,
   60,   61,   62,   42,   43,   93,   45,   46,   47,   -1,
   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,   -1,
   59,   60,   -1,   62,   -1,   -1,   -1,   93,   59,   60,
   -1,   62,   -1,   93,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  257,  258,  259,  260,  261,  262,   -1,
   -1,   -1,   91,  257,  258,  259,  260,  261,  262,   -1,
   91,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  257,  258,  259,  260,  261,
  262,   -1,  257,  258,  259,  260,  261,  262,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,
  260,  261,  262,   -1,  257,  258,  259,  260,  261,  262,
   -1,  257,  258,  259,  260,  261,  262,  257,  258,  259,
  260,  261,  262,   -1,  257,  258,  259,  260,  261,  262,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,  260,
  261,  262,  257,  258,  259,  260,  261,  262,   -1,  257,
  258,  259,  260,  261,  262,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   42,   43,   -1,   45,   46,
   47,  257,  258,  259,  260,  261,  262,  257,  258,  259,
  260,  261,  262,   60,   61,   62,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,
  259,  260,  261,  262,   -1,   -1,  257,  258,  259,  260,
  261,  262,   42,   43,   91,   45,   46,   47,   42,   43,
   -1,   45,   46,   47,   -1,   -1,   -1,   -1,   -1,   59,
   60,   -1,   62,   -1,   -1,   59,   60,   -1,   62,   42,
   43,   -1,   45,   46,   47,   42,   43,   -1,   45,   46,
   47,   -1,   -1,   -1,   -1,   -1,   59,   60,   -1,   62,
   -1,   91,   59,   60,   -1,   62,   -1,   91,   -1,   42,
   43,   -1,   45,   46,   47,   -1,   -1,   42,   43,   -1,
   45,   46,   47,   -1,   -1,   -1,   -1,   60,   91,   62,
   -1,   -1,   -1,   -1,   91,   60,   61,   62,   -1,   42,
   43,   -1,   45,   46,   47,   42,   43,   -1,   45,   46,
   47,   -1,   -1,   -1,   -1,   -1,   -1,   60,   91,   62,
   93,   -1,   -1,   60,   -1,   62,   91,   50,   51,   52,
   -1,   -1,   -1,   56,   57,   58,   59,   -1,   -1,   62,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   91,   -1,
   -1,   74,   -1,   -1,   91,   -1,   79,   80,   -1,   -1,
   -1,   84,   85,   86,   87,   88,   89,   90,   91,   92,
   93,   94,   95,   96,   97,   -1,   -1,  100,   -1,   -1,
  257,  258,  259,  260,  261,  262,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  130,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  141,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,
  260,  261,  262,  257,  258,  259,  260,  261,  262,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  257,  258,  259,  260,  261,  262,
  257,  258,  259,  260,  261,  262,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  257,  258,  259,  260,  261,  262,
   -1,   -1,  257,  258,  259,  260,  261,  262,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  257,  258,  259,  260,  261,  262,
   -1,   -1,  259,  260,  261,  262,
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
"\"ELSE\"","\"VAR\"","\"IDENT\"","\"STRUCT\"","\"LITENT\"","\"INT\"","\"REAL\"",
"\"CHAR\"","\"PRINT\"","\"PRINTSP\"","\"PRINTLN\"","\"READ\"","\"IF\"",
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
"defFuncion : \"IDENT\" '(' parametrosOpt ')' ':' tipo '{' defVariablesOpt sentenciasOpt '}'",
"defFuncion : \"IDENT\" '(' parametrosOpt ')' '{' defVariablesOpt sentenciasOpt '}'",
"parametrosOpt : defParametros",
"parametrosOpt :",
"defParametros : defParametros ',' defParametro",
"defParametros : defParametro",
"defParametro : \"IDENT\" ':' tipo",
"defStruct : \"STRUCT\" \"IDENT\" '{' camposOpt '}' ';'",
"camposOpt : camposOpt defCampo",
"camposOpt :",
"defCampo : \"IDENT\" ':' tipo ';'",
"tipo : tipoSimple",
"tipo : \"IDENT\"",
"tipo : '[' \"LITENT\" ']' tipo",
"tipoSimple : \"INT\"",
"tipoSimple : \"REAL\"",
"tipoSimple : \"CHAR\"",
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

//#line 134 "sintac.y"
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
//#line 563 "Parser.java"
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
{yyval = new DefFuncion(val_peek(9),new TipoFuncion(val_peek(4), val_peek(7)),val_peek(2),val_peek(1));}
break;
case 11:
//#line 52 "sintac.y"
{yyval = new DefFuncion(val_peek(7),new TipoFuncion(new TipoVoid(), val_peek(5)),val_peek(2),val_peek(1));}
break;
case 12:
//#line 55 "sintac.y"
{yyval = val_peek(0);}
break;
case 13:
//#line 56 "sintac.y"
{yyval = new ArrayList<Definition>();}
break;
case 14:
//#line 58 "sintac.y"
{yyval=val_peek(2);  ((List)yyval).add(val_peek(0)); }
break;
case 15:
//#line 59 "sintac.y"
{yyval= new ArrayList<Definition>(); ((List)yyval).add(val_peek(0)); }
break;
case 16:
//#line 61 "sintac.y"
{yyval = new DefVariable(val_peek(2),val_peek(0));}
break;
case 17:
//#line 64 "sintac.y"
{yyval = new DefStruct(val_peek(4),new TipoStruct(val_peek(4)),val_peek(2));}
break;
case 18:
//#line 67 "sintac.y"
{ yyval = val_peek(1); ((List)val_peek(1)).add(val_peek(0));}
break;
case 19:
//#line 68 "sintac.y"
{yyval = new ArrayList<Definition>();}
break;
case 20:
//#line 70 "sintac.y"
{yyval = new DefCampo(val_peek(3),val_peek(1));}
break;
case 21:
//#line 73 "sintac.y"
{ yyval = val_peek(0); }
break;
case 22:
//#line 74 "sintac.y"
{yyval = new TipoStruct(val_peek(0));}
break;
case 23:
//#line 75 "sintac.y"
{yyval = new TipoArray(val_peek(2),val_peek(0));}
break;
case 24:
//#line 78 "sintac.y"
{yyval = IntTipo.getInstance();}
break;
case 25:
//#line 79 "sintac.y"
{yyval = RealTipo.getInstance();}
break;
case 26:
//#line 80 "sintac.y"
{yyval = CharTipo.getInstance();}
break;
case 27:
//#line 83 "sintac.y"
{yyval = val_peek(1); ((List)val_peek(1)).add(val_peek(0));}
break;
case 28:
//#line 84 "sintac.y"
{yyval = new ArrayList<Statement>();}
break;
case 29:
//#line 87 "sintac.y"
{yyval = new Print(val_peek(1));}
break;
case 30:
//#line 88 "sintac.y"
{yyval = new Printsp(val_peek(1));}
break;
case 31:
//#line 89 "sintac.y"
{yyval = new Println(val_peek(1));}
break;
case 32:
//#line 90 "sintac.y"
{yyval = new Read(val_peek(1));}
break;
case 33:
//#line 91 "sintac.y"
{yyval = new Asignacion(val_peek(3),val_peek(1));}
break;
case 34:
//#line 92 "sintac.y"
{yyval = new IfElse(val_peek(4),val_peek(1));}
break;
case 35:
//#line 93 "sintac.y"
{yyval = new IfElse(val_peek(8),val_peek(5),val_peek(1));}
break;
case 36:
//#line 94 "sintac.y"
{yyval = new While(val_peek(4),val_peek(1));}
break;
case 37:
//#line 95 "sintac.y"
{yyval = new Return(val_peek(1));}
break;
case 38:
//#line 96 "sintac.y"
{yyval = new Return();}
break;
case 39:
//#line 97 "sintac.y"
{yyval = new Invocacion(val_peek(4),val_peek(2));}
break;
case 40:
//#line 100 "sintac.y"
{yyval = val_peek(0);}
break;
case 41:
//#line 101 "sintac.y"
{yyval = new ArrayList<Expression>();}
break;
case 42:
//#line 104 "sintac.y"
{yyval=val_peek(2);  ((List)yyval).add(val_peek(0)); }
break;
case 43:
//#line 105 "sintac.y"
{yyval= new ArrayList<Expression>(); ((List)yyval).add(val_peek(0)); }
break;
case 44:
//#line 108 "sintac.y"
{yyval = new LitInt(val_peek(0));}
break;
case 45:
//#line 109 "sintac.y"
{yyval = new LitReal(val_peek(0));}
break;
case 46:
//#line 110 "sintac.y"
{yyval = new Variable(val_peek(0));}
break;
case 47:
//#line 111 "sintac.y"
{yyval = new LitChar(val_peek(0));}
break;
case 48:
//#line 112 "sintac.y"
{yyval = new ExpresionAritmetica(val_peek(2),val_peek(1),val_peek(0));}
break;
case 49:
//#line 113 "sintac.y"
{yyval = new ExpresionAritmetica(val_peek(2),val_peek(1),val_peek(0));}
break;
case 50:
//#line 114 "sintac.y"
{yyval = new Comparacion(val_peek(2),val_peek(1),val_peek(0));}
break;
case 51:
//#line 115 "sintac.y"
{yyval = new Comparacion(val_peek(2),val_peek(1),val_peek(0));}
break;
case 52:
//#line 116 "sintac.y"
{yyval = new ExpresionAritmetica(val_peek(2),val_peek(1),val_peek(0));}
break;
case 53:
//#line 117 "sintac.y"
{yyval = new ExpresionAritmetica(val_peek(2),val_peek(1),val_peek(0));}
break;
case 54:
//#line 118 "sintac.y"
{yyval = new AccesoStruct(val_peek(2),val_peek(0));}
break;
case 55:
//#line 119 "sintac.y"
{yyval = new Comparacion(val_peek(2),val_peek(1),val_peek(0));}
break;
case 56:
//#line 120 "sintac.y"
{yyval = new Comparacion(val_peek(2),val_peek(1),val_peek(0));}
break;
case 57:
//#line 121 "sintac.y"
{yyval = new ExpresionLogica(val_peek(2),val_peek(1),val_peek(0));}
break;
case 58:
//#line 122 "sintac.y"
{yyval = new ExpresionLogica(val_peek(2),val_peek(1),val_peek(0));}
break;
case 59:
//#line 123 "sintac.y"
{yyval = new Comparacion(val_peek(2),val_peek(1),val_peek(0));}
break;
case 60:
//#line 124 "sintac.y"
{yyval = new Comparacion(val_peek(2),val_peek(1),val_peek(0));}
break;
case 61:
//#line 125 "sintac.y"
{yyval = new MenosUnario(val_peek(0));}
break;
case 62:
//#line 126 "sintac.y"
{yyval = new Not(val_peek(0));}
break;
case 63:
//#line 127 "sintac.y"
{yyval = val_peek(1);}
break;
case 64:
//#line 128 "sintac.y"
{yyval = new LlamadaFuncion(val_peek(3),val_peek(1));}
break;
case 65:
//#line 129 "sintac.y"
{yyval = new AccesoArray(val_peek(3),val_peek(1));}
break;
case 66:
//#line 130 "sintac.y"
{yyval = new Cast(val_peek(4),val_peek(1));}
break;
//#line 975 "Parser.java"
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
