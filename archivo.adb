procedure principal is
 i: integer;
 num1: integer;
 num2: integer;

 function suma (x,y: in out integer) return integer is
  z: integer; 
 begin
   
  z := x + y;
  return z;
 end suma;

begin
  put("Ingrese un numero: ");
  get(num1);
  put("Ingrese un numero: ");
  get(num2);
  i:= suma(num1,num2); 
  put("El resultado es: ");
  put(i);
end principal;