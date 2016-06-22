procedure principal is
  resultado : integer;
 function suma(x,y: in out integer) return integer is
  result: integer;
  begin
   return x+y;
 end suma; 
begin
 resultado := suma(2,2);
 put(resultado);
end principal;