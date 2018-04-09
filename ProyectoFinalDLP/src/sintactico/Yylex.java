/* The following code was generated by JFlex 1.4.1 on 10/04/18 0:29 */

// @author Ra�l Izquierdo

/* -- No es necesario modificar esta parte ----------------------------------------------- */
package sintactico;

import java.io.*;
import main.*;
import ast.Position;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.1
 * on 10/04/18 0:29 from the specification file
 * <tt>lexico.l</tt>
 */
public class Yylex {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\44\1\40\2\0\1\43\22\0\1\43\1\23\4\0\1\27"+
    "\1\36\2\1\1\42\3\1\1\33\1\41\1\31\11\32\2\1\1\26"+
    "\1\24\1\25\2\0\32\34\1\1\1\37\1\1\1\0\1\35\1\0"+
    "\1\3\1\34\1\15\1\21\1\17\1\5\1\34\1\16\1\11\2\34"+
    "\1\6\1\34\1\12\1\7\1\20\1\34\1\4\1\13\1\10\1\14"+
    "\1\2\1\22\3\34\1\1\1\30\1\1\103\0\1\34\7\0\1\34"+
    "\3\0\1\34\3\0\1\34\1\0\1\34\6\0\1\34\6\0\1\34"+
    "\7\0\1\34\3\0\1\34\3\0\1\34\1\0\1\34\6\0\1\34"+
    "\uff05\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\12\3\4\2\2\1\2\4\1\1"+
    "\1\5\1\2\1\6\3\3\1\7\7\3\1\10\1\11"+
    "\1\12\1\13\1\14\1\15\4\0\1\5\1\0\1\16"+
    "\3\3\1\17\6\3\1\20\1\21\2\0\1\22\3\3"+
    "\1\23\1\24\1\25\3\3\1\26\1\3\1\27\1\30"+
    "\1\31\1\32\2\3\1\33\1\34";

  private static int [] zzUnpackAction() {
    int [] result = new int[83];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\45\0\45\0\112\0\157\0\224\0\271\0\336"+
    "\0\u0103\0\u0128\0\u014d\0\u0172\0\u0197\0\u01bc\0\u01e1\0\u0206"+
    "\0\u022b\0\u0250\0\u0275\0\u029a\0\u02bf\0\u02e4\0\45\0\u0309"+
    "\0\45\0\u032e\0\u0353\0\u0378\0\157\0\u039d\0\u03c2\0\u03e7"+
    "\0\u040c\0\u0431\0\u0456\0\u047b\0\45\0\45\0\45\0\45"+
    "\0\45\0\45\0\u029a\0\u04a0\0\u04c5\0\u04ea\0\u050f\0\u0534"+
    "\0\157\0\u0559\0\u057e\0\u05a3\0\157\0\u05c8\0\u05ed\0\u0612"+
    "\0\u0637\0\u065c\0\u0681\0\u04a0\0\45\0\u06a6\0\u06cb\0\157"+
    "\0\u06f0\0\u0715\0\u073a\0\157\0\157\0\157\0\u075f\0\u0784"+
    "\0\u07a9\0\157\0\u07ce\0\u07f3\0\157\0\157\0\157\0\u0818"+
    "\0\u083d\0\157\0\157";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[83];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\5\1\6\1\7\3\5\1\10"+
    "\1\5\1\11\1\5\1\12\1\5\1\13\1\14\1\5"+
    "\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24"+
    "\1\25\1\3\1\5\1\2\1\26\1\2\1\27\1\30"+
    "\1\3\1\27\1\31\47\0\1\5\1\32\17\5\6\0"+
    "\2\5\1\0\2\5\11\0\21\5\6\0\2\5\1\0"+
    "\2\5\11\0\15\5\1\33\3\5\6\0\2\5\1\0"+
    "\2\5\11\0\4\5\1\34\14\5\6\0\2\5\1\0"+
    "\2\5\11\0\3\5\1\35\4\5\1\36\10\5\6\0"+
    "\2\5\1\0\2\5\11\0\6\5\1\37\12\5\6\0"+
    "\2\5\1\0\2\5\11\0\1\5\1\40\12\5\1\41"+
    "\4\5\6\0\2\5\1\0\2\5\11\0\4\5\1\42"+
    "\14\5\6\0\2\5\1\0\2\5\11\0\2\5\1\43"+
    "\16\5\6\0\2\5\1\0\2\5\11\0\14\5\1\44"+
    "\4\5\6\0\2\5\1\0\2\5\33\0\1\45\44\0"+
    "\1\46\44\0\1\47\44\0\1\50\47\0\1\51\45\0"+
    "\1\52\45\0\2\53\1\54\42\0\2\25\1\54\11\0"+
    "\37\55\1\56\1\0\4\55\41\0\1\57\1\60\4\0"+
    "\2\5\1\61\16\5\6\0\2\5\1\0\2\5\11\0"+
    "\1\5\1\62\4\5\1\63\12\5\6\0\2\5\1\0"+
    "\2\5\11\0\5\5\1\64\13\5\6\0\2\5\1\0"+
    "\2\5\11\0\6\5\1\65\12\5\6\0\2\5\1\0"+
    "\2\5\11\0\2\5\1\66\16\5\6\0\2\5\1\0"+
    "\2\5\11\0\11\5\1\67\7\5\6\0\2\5\1\0"+
    "\2\5\11\0\1\5\1\70\17\5\6\0\2\5\1\0"+
    "\2\5\11\0\11\5\1\71\7\5\6\0\2\5\1\0"+
    "\2\5\11\0\7\5\1\72\11\5\6\0\2\5\1\0"+
    "\2\5\11\0\7\5\1\73\11\5\6\0\2\5\1\0"+
    "\2\5\40\0\2\74\50\0\1\75\16\0\1\55\1\0"+
    "\1\55\16\0\2\76\3\0\1\75\6\0\40\57\1\0"+
    "\4\57\42\60\1\77\2\60\2\0\17\5\1\100\1\5"+
    "\6\0\2\5\1\0\2\5\11\0\12\5\1\101\6\5"+
    "\6\0\2\5\1\0\2\5\11\0\1\5\1\102\17\5"+
    "\6\0\2\5\1\0\2\5\11\0\12\5\1\103\6\5"+
    "\6\0\2\5\1\0\2\5\11\0\6\5\1\104\12\5"+
    "\6\0\2\5\1\0\2\5\11\0\2\5\1\105\16\5"+
    "\6\0\2\5\1\0\2\5\11\0\15\5\1\106\3\5"+
    "\6\0\2\5\1\0\2\5\11\0\10\5\1\107\10\5"+
    "\6\0\2\5\1\0\2\5\11\0\4\5\1\110\14\5"+
    "\6\0\2\5\1\0\2\5\40\0\2\76\3\0\1\75"+
    "\6\0\41\60\1\27\1\77\2\60\2\0\2\5\1\111"+
    "\16\5\6\0\2\5\1\0\2\5\11\0\6\5\1\112"+
    "\12\5\6\0\2\5\1\0\2\5\11\0\13\5\1\113"+
    "\5\5\6\0\2\5\1\0\2\5\11\0\6\5\1\114"+
    "\12\5\6\0\2\5\1\0\2\5\11\0\15\5\1\115"+
    "\3\5\6\0\2\5\1\0\2\5\11\0\10\5\1\116"+
    "\10\5\6\0\2\5\1\0\2\5\11\0\6\5\1\117"+
    "\12\5\6\0\2\5\1\0\2\5\11\0\4\5\1\120"+
    "\4\5\1\121\7\5\6\0\2\5\1\0\2\5\11\0"+
    "\10\5\1\122\10\5\6\0\2\5\1\0\2\5\11\0"+
    "\16\5\1\123\2\5\6\0\2\5\1\0\2\5\7\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[2146];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\2\11\23\1\1\11\1\1\1\11\13\1\6\11"+
    "\4\0\1\1\1\0\14\1\1\11\2\0\24\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[83];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the textposition at the last state to be included in yytext */
  private int zzPushbackPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
	public Yylex(Reader in, GestorErrores gestor) {
		this(in);
		this.gestor = gestor;
	}

	public int line() { return yyline + 1; }
	public int column() { return yycolumn + 1; }
	public String lexeme() { return yytext(); }

	// Traza para probar el l�xico de manera independiente al sint�ctico
	public static void main(String[] args) throws Exception {
		Yylex lex = new Yylex(new FileReader(Main.programa), new GestorErrores());
		int token;
		while ((token = lex.yylex()) != 0)
			System.out.println("\t[" + lex.line() + ":" + lex.column() + "] Token: " + token + ". Lexema: " + lex.lexeme());
	}

	private GestorErrores gestor;


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public Yylex(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public Yylex(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 162) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzPushbackPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead < 0) {
      return true;
    }
    else {
      zzEndRead+= numRead;
      return false;
    }
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = zzPushbackPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public int yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                                                             zzCurrentPosL++) {
        switch (zzBufferL[zzCurrentPosL]) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn++;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = zzLexicalState;


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 20: 
          { return Parser.CHAR;
          }
        case 29: break;
        case 7: 
          { return Parser.IF;
          }
        case 30: break;
        case 17: 
          { return Parser.LITCHAR;
          }
        case 31: break;
        case 18: 
          { return Parser.READ;
          }
        case 32: break;
        case 2: 
          { return yytext().charAt(0);
          }
        case 33: break;
        case 3: 
          { return Parser.IDENT;
          }
        case 34: break;
        case 26: 
          { return Parser.STRUCT;
          }
        case 35: break;
        case 1: 
          { gestor.error("L�xico", "Cadena \"" + yytext() +"\" no reconocida.", new Position(line(), column()));
          }
        case 36: break;
        case 12: 
          { return Parser.AND;
          }
        case 37: break;
        case 23: 
          { return Parser.PRINT;
          }
        case 38: break;
        case 25: 
          { return Parser.RETURN;
          }
        case 39: break;
        case 28: 
          { return Parser.PRINTSP;
          }
        case 40: break;
        case 21: 
          { return Parser.ELSE;
          }
        case 41: break;
        case 13: 
          { return Parser.OR;
          }
        case 42: break;
        case 19: 
          { return Parser.CAST;
          }
        case 43: break;
        case 4: 
          { return Parser.LITENT;
          }
        case 44: break;
        case 9: 
          { return Parser.IGUALQUE;
          }
        case 45: break;
        case 24: 
          { return Parser.WHILE;
          }
        case 46: break;
        case 6: 
          { yycolumn += 3;
          }
        case 47: break;
        case 22: 
          { return Parser.REAL;
          }
        case 48: break;
        case 16: 
          { return Parser.LITREAL;
          }
        case 49: break;
        case 8: 
          { return Parser.DISTINTO;
          }
        case 50: break;
        case 27: 
          { return Parser.PRINTLN;
          }
        case 51: break;
        case 11: 
          { return Parser.MENORIGUALQUE;
          }
        case 52: break;
        case 15: 
          { return Parser.INT;
          }
        case 53: break;
        case 14: 
          { return Parser.VAR;
          }
        case 54: break;
        case 10: 
          { return Parser.MAYORIGUALQUE;
          }
        case 55: break;
        case 5: 
          { 
          }
        case 56: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              { return 0; }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
