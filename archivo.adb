procedure principal is
	x,y,z : integer;
	h:float;

	procedure una_prueba(a,b: in integer; c: out integer) is
		algo1 : integer;
		
		begin
		c := a + b;
		
		if c = 10 then
		  put(x);
		else
         c := 20;
		end if;

	end una_prueba;

	function algo (dinero : in integer; algo1: in out float ) return float is
      total : integer;
     begin
     
      while total >= 0 loop
        get(total);
      end loop;
    end algo;
    
    

	begin 
	x := 10;
	total := 1.0;
	una_prueba(x,10);
	h := algo(12,34.0);
	put(h);
end principal;