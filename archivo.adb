procedure principal is
 result: integer;
 
 function prueba (x,y: in out integer) return integer is
   resultado: integer;
   
   function suma (num1, num2: in out integer) return integer is
     retorno: integer;
    begin
      retorno := num1+num2;
      return retorno;
   end suma; 
  
  begin
    resultado := suma(x,y);
    return resultado;
 end prueba;
 
 begin
   result := prueba(2,2); 
   put(result);

end principal;