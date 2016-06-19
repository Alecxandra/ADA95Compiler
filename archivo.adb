procedure principal is
 i: integer;

 function suma (x,y: in out integer) return integer is
  z: integer; 
 begin
  z:= x + y;
  return z;
 end suma;

begin
  i:= suma(5,6); 
  put("El resultado es: ");
  put(i);
end principal;