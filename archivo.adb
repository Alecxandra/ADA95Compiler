procedure principal is
 i, x1 : integer;

   function suma (x,y: in out integer) return integer is
      z: integer;
    begin
      return x+y;
   end suma; 

   procedure algo (cosas : in integer) is
     dundo: integer;
    begin
    dundo :=20;
      put(dundo);
   end algo;

 begin
 i := 1+10;
 put(i+10);
 get(i);
 x1 := suma(1,2);

 if x1 >= 12 then
   put("hola");
 elsif x1 < 20 then
  get(i);  
 end if;

 while x1 /=  10  loop
   put("hola");
   exit when x1 < 10;
 end loop;

 loop

 end loop;

 for x1 in 1..10 loop
   put("hola");
 end loop;

end principal;