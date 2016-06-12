procedure principal is
 x: integer;
 function max (arr: in Integer) return Integer is
    i: Integer;
    function min (arr1: in Integer) return Integer is
       a: Integer;
    begin
       a := arr1;
       put(a);
       if a < 4 then
          Put(" so is less than 4");
          if a < 2 then
            Put(" test");
          end if;
       end if;
       return a;
    end min;
 begin
    for i in 4..7 loop
       Put("Going through the first loop");
       Put(i);
       min(i);
    end loop;
    return i;
 end max;
begin
 get(x);
 put(5);
 if 1 < 4 then
    Put(" so is less than 4");
    if 1 < 5 then
      Put(" test");
    end if;
 end if;
 max(4);
end principal;
