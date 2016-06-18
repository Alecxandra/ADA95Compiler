procedure principal is
 i: integer;
begin

    put("Hola");
  i := 0;
  while true loop
    if i >= 10 and i <= 15 then
        put(i);
        put("\n");
    end if;
    i := i +1;
    exit when i = 30;
  end loop;


end principal;