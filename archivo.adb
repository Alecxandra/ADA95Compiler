--Ejemplo de subprogramas 

procedure Ejemplo is --procedimiento principal Ejemplo 
   
  --función local EsPar 
  function EsPar return boolean is 
  begin 
      return ((x / 2) = 0); 
  end EsPar; 
  --procedimiento local Media 
  procedure Media(x,y: in float;med: out float) is 
      dos: float; --variable local 
  begin 
      dos := 2.0; 
      med := (x + y) / dos; 
  end Media;

  R1,R2,R3 : float; --variables locales de Ejemplo 
  i : integer; 
begin 
    put("Entre un numero: "); 
    get(i);
         if EsPar(i) then put("es par"); else put("no es par"); end if; 
    put("Entre una pareja de numeros separados por un espacio: "); 
    get(R1); get(R2); 
    Media(R1,R2,R3); 
    Put(R3); 
end Ejemplo; 