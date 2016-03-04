procedure prueba(S3,S2: in out float) is
   x,y,z : boolean;
 begin
   procedure Media(x,y: in float;med: out float) is 
      dos: float; --variable local 
  begin 
      dos := 2.0; 
      med := (x + y) / dos; 
  end Media;
end prueba;
