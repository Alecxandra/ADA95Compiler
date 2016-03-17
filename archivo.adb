procedure Ejemplo is 
    x:integer;

    function a (y,z,x: in float) return boolean is
         z,x,y: float;
         function hola(x: out float; tr: in out boolean) return float is
            x:float;
          begin
           x:=34;
         end hola; 
         begin
    end a;
begin 
    put("Entre un numero: "); 
    Get(i); 
         if EsPar(i) then put("es par"); else put("no es par"); end if; 
    put("Entre una pareja de numeros separados por un espacio: "); 
    Get(R1); 
    Get(R2); 
    Media(R1,R2,R3); 
    Put(R3); 
end Ejemplo; 