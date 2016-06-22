procedure principal is
  resultado,x,y: integer;

  function resta (num1, num2 : in out integer) return integer is
   result: integer;
  begin
   result := num1-num2;
   return result;
  end resta;
begin
 put("Ingrese un numero: ");
 get(x);
 put("Ingrese un numero: ");
 get(y);
 resultado := resta(x,y);
 put("el resultado es: ");
 put(resultado);
end principal;
