/* user code*/

package ada95compiler;
%%

%class AdaLexer

%unicode
%line
%column
%cup

%{
  private Symbol symbol(int type) {
    return new Symbol(type, yyline, yycolumn);
  }
  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline, yycolumn, value);
  }
%}

%eofval{ 
    return symbol(sym.EOF);
%eofval}



Number= [0-9]
Id= [_A-Za-z][_A-Za-z0-9]*
Integer= {Number}+
Float= {Number}+[\.]{Number}+
Space= [\s\t\f]
Nextline= \r|\n|\r\n
Quote= [\"]
CommentDelimiter = "--"
StringCont = ([^\"\\;] | (\\n) | (\\t) | (\\\\) | (\\r) | (\\\") | (\\;))*


%state STRING
%state COMMENT

%%
<YYINITIAL>{
   "procedure"                 {return symbol(sym.PROCEDURE);}
   "is"                        {return symbol(sym.IS);}
   "begin"                     {return symbol(sym.BEGIN);} 
   "end"                       {return symbol(sym.END);}
   "<="                        {return symbol(sym.LEQUAL);}
   ">="                        {return symbol(sym.GEQUAL);}
   "/="                        {return symbol(sym.DISTINCT);}
   ":="                        {return symbol(sym.ASSIGN);}
   ">"                         {return symbol(sym.GREATER);}
   "<"                         {return symbol(sym.LESS);}  
   "="                         {return symbol(sym.EQUAL);}   
   ":"                         {return symbol(sym.COLON);}
   "in out"                    {return symbol(sym.INOUT);} 
   "in"                        {return symbol(sym.IN);}
   "integer"                   {return symbol(sym.INTEGER);}
   "boolean"                   {return symbol(sym.BOOLEAN);}
   "float"                     {return symbol(sym.FLOAT);}  	
   ","                         {return symbol(sym.COMMA);}
   ";"                         {return symbol(sym.SEMICOLON);}
   "if"                        {return symbol(sym.IF);}
   "then"                      {return symbol(sym.THEN);}
   "elsif"                     {return symbol(sym.ELSIF);}
   "else"                      {return symbol(sym.ELSE);}
   "for"                       {return symbol(sym.FOR);}
   "loop"                      {return symbol(sym.LOOP);}
   "exit"                      {return symbol(sym.EXIT);}
   "when"                      {return symbol(sym.WHEN);}
   "while"                     {return symbol(sym.WHILE);}
   "declare"                   {return symbol(sym.DECLARE);}
   "function"                  {return symbol(sym.FUNCTION);}
   "return"                    {return symbol(sym.RETURN);}
   "out"                       {return symbol(sym.OUT);}
   "("                         {return symbol(sym.LPAR);}
   ")"                         {return symbol(sym.RPAR);}
   "get"                       {return symbol(sym.GET);}
   "put"                       {return symbol(sym.PUT);}  
   "and"                       {return symbol(sym.AND);}
   "or"                        {return symbol(sym.OR);}
   "not"                       {return symbol(sym.NOT);}
   "true"                      {return symbol(sym.TRUE);}
   "false"                     {return symbol(sym.FALSE);}
   {CommentDelimiter}          {yybegin(COMMENT);}
   "+"                         {return symbol(sym.ADD);}
   "-"                         {return symbol(sym.MIN);}
   "**"                        {return symbol(sym.POT);}
   "*"                         {return symbol(sym.MUL);}
   "/"                         {return symbol(sym.DIV);}
   "main"                      {return symbol(sym.MAIN);}
   {Id}                        {return symbol(sym.ID, yytext()); }   
   {Float}                     {return symbol(sym.FLOATN, new Float(Float.parseFloat(yytext())));}
   {Integer}                   {System.out.println( return symbol(sym.INTEGERN, new Integer(Integer.parseInt(yytext())));}
   {Space}                     {/* Ignore */}
   {Nextline}                  {/* Ignore */} 
   {Quote}                     {yybegin(STRING);}
   .                           {System.err.println("<"+yytext()+">"+" en la linea:"+(yyline+1)+", columna: "+(yycolumn+1)+ " caracter no valido");}
}

<STRING>{
   {Quote}                     {yybegin(YYINITIAL);} 
   {StringCont}                {return symbol(sym.STRINGCONT, yytext());}
   .                           {System.err.println("<"+yytext()+">"+" en la linea:"+(yyline+1)+", columna: "+(yycolumn+1)+ " caracter no valido");} 

}
<COMMENT>{
   {Nextline}                  {yybegin(YYINITIAL);}
   {Space}                     { /* Ignore */                                  }
   .                           { /* Ignore */                                  }

}

