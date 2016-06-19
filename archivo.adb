procedure principal is
 i: integer;
 x1: integer;
 function suma (x,y: in out integer) return integer is
  z: integer; 
 begin
  z:= x + y;
  return z;
 end suma;

begin
  x1:=20;
  i:= suma(5*x1+10,6/x1+9); 
  put("El resultado es: ");
  put(i);
end principal;