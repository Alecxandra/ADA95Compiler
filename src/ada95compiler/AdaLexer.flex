/* user code*/
/*and or not xor*/
package ada95compiler;

%%

%class AdaLexer

%unicode
%line
%column

Number= [0-9]
/* preguntar por caracteres raros o guiones */
Id= [A-Za-z][A-Za-z0-9]*
Integer= Number+
Float= {Number}+[\.]{Number}
Space= [\s\t\f]
Nextline= \r|\n|\r\n

%%
<YYINITIAL>{
   {Id}                        {System.out.println("Identificador: "+ yytext());}
   "procedure"                 {System.out.println("procedure");}
   "is"                        {System.out.println("is");}
   "begin"                     {System.out.println("begin");} 
   "end"                       {System.out.println("end");}
   ":"                         {System.out.println("dos puntos :");}
   "integer"                   {System.out.println("integer");}
   "boolean"                   {System.out.println("boolean");}
   "float"                     {System.out.println("float");}  	
   ","                         {System.out.println("coma ,");}
   ";"                         {System.out.println("punto y coma ;");}
   "if"                        {System.out.println("if");}
   "then"                      {System.out.println("then");}
   "elsif"                     {System.out.println("else if");}
   "for"                       {System.out.println("for");}
   "in"                        {System.out.println("in");}
   "loop"                      {System.out.println("loop");}
   "exit"                      {System.out.println("exit");}
   "when"                      {System.out.println("when");}
   "while"                     {System.out.println("while");}
   "declare"                   {System.out.println("declare");}
   ":="                        {System.out.println("asignacion");}
   "="                         {System.out.println("igual");}
   "/="                        {System.out.println("distinto");}
   ".."	                       {System.out.println("rango");} 
   "function"                  {System.out.println("function");}
   "return"                    {System.out.println("retorno");}
   "out"                       {System.out.println("out");} 
   "("                         {System.out.println("parentesis (");}
   ")"                         {System.out.println("parentesis )");}
   "get"                       {System.out.println("get");}
   "put"                       {System.out.println("put");}  
   ">"                         {System.out.println("mayor que");}
   "<"                         {System.out.println("menor que");}
   "<="                        {System.out.println("menor igual");}
   ">="                        {System.out.println("mayor igual");}
   "and"                       {System.out.println("operador and");}
   "or"                        {System.out.println("operador or");}
   "true"                      {System.out.println("true");}
   "false"                     {System.out.println("false");}
   "+"                         {System.out.println("mas +");}
   "-"                         {System.out.println("menos -");}
   "*"                         {System.out.println("por *");}
   "/"                         {System.out.println("/");}
   "main"                      {System.out.println("main");}   
   {Float}                     {System.out.println("float: "+yytext());}
   {Integer}                   {System.out.println("Integer: "+ yytext());}
   {Space}                     {/* Ignore */}
   {Nextline}                  {/* Ignore */} 
}

