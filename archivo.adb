procedure principal is
 x: float;
 y: float;

 function resta ( j, k: in out float ) return float is
      rest: float;
     begin
      rest := j - k ;
      return rest;
 end resta;

 function suma ( n, m: in out float ) return float is
  sum: float;
 begin
  sum := n + m ;
  return sum;
 end suma;

 begin
  y := 10.0;
  x := suma(x,y);
  put("el resultado es: ");
  put(x); 
   
end principal;