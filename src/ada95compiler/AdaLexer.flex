/* user code*/

package ada95compiler;
%%

%class AdaLexer

%unicode
%line
%column
%int

/* preguntar por caracteres raros en id o guiones,sentencia goto 
potencia
*/
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
   "procedure"                 {System.out.println("<procedure>");}
   "is"                        {System.out.println("<is>");}
   "begin"                     {System.out.println("<begin>");} 
   "end"                       {System.out.println("<end>");}
   "<="                        {System.out.println("menor igual <=");}
   ">="                        {System.out.println("mayor igual >=");}
   "/="                        {System.out.println("distinto /=");}
   ":="                        {System.out.println("asignacion :=");}
   ">"                         {System.out.println("mayor que >");}
   "<"                         {System.out.println("menor que <");}  
   "="                         {System.out.println("igual = ");}   
   ":"                         {System.out.println("dos puntos : ");}
   "in out"                    {System.out.println("<in out>");} 
   "in"                        {System.out.println("<in>");}
   "integer"                   {System.out.println("<integer>");}
   "boolean"                   {System.out.println("<boolean>");}
   "float"                     {System.out.println("<float>");}  	
   ","                         {System.out.println("coma ,");}
   ";"                         {System.out.println("punto y coma ;");}
   "if"                        {System.out.println("<if>");}
   "then"                      {System.out.println("<then>");}
   "elsif"                     {System.out.println("<elsif>");}
   "else"                      {System.out.println("<else>");}
   "for"                       {System.out.println("<for>");}
   "loop"                      {System.out.println("<loop>");}
   "exit"                      {System.out.println("<exit>");}
   "when"                      {System.out.println("<when>");}
   "while"                     {System.out.println("<while>");}
   "declare"                   {System.out.println("<declare>");}
   "function"                  {System.out.println("<function>");}
   "return"                    {System.out.println("<return>");}
   "out"                       {System.out.println("<out>");}
   "("                         {System.out.println("parentesis (");}
   ")"                         {System.out.println("parentesis )");}
   "get"                       {System.out.println("<get>");}
   "put"                       {System.out.println("<put>");}  
   "and"                       {System.out.println("operador and");}
   "or"                        {System.out.println("operador or");}
   "not"                       {System.out.println("operador not");}
   "true"                      {System.out.println("true");}
   "false"                     {System.out.println("false");}
   {CommentDelimiter}          {yybegin(COMMENT);}
   "+"                         {System.out.println("mas +");}
   "-"                         {System.out.println("menos -");}
   "**"                        {System.out.println("potencia **");}
   "*"                         {System.out.println("por *");}
   "/"                         {System.out.println("entre /");}
   "main"                      {System.out.println("<main>");}
   {Id}                        {System.out.println("Identificador: "+ yytext());}   
   {Float}                     {System.out.println("float: "+yytext());}
   {Integer}                   {System.out.println("Integer: "+ yytext());}
   {Space}                     {/* Ignore */}
   {Nextline}                  {/* Ignore */} 
   {Quote}                     {yybegin(STRING);}
   .                           {System.out.println("Token no reconocido por el lenguaje");}
}

<STRING>{
   {Quote}                     {yybegin(YYINITIAL);} 
   {StringCont}                {System.out.println("Contenido del string: "+yytext());}
   .                           {System.out.println("Caracter no permitido "+yytext()+" linea: "+yyline+" columna"+ yycolumn);} 

}
<COMMENT>{
   {Nextline}                  {System.out.println("se encontro un comentario"); yybegin(YYINITIAL);}
   {Space}                     { /* Ignore */                                  }
   .                           { /* Ignore */                                  }

}

