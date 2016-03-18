procedure IfDemo is

Index, Count : INTEGER;

begin
   for Index in 1..7 loop  -- This contains two simple if statements
      Put("Index is ");
      Put(Index);
      if Index < 4 then
         Put(" so is less than 4");
      end if;
      if Index > 5 then
         Put(" so is more than 5");
      end if;
   end loop;


   for Index in 13..17 loop  -- This contains an else clause
      Put("Index is");
      Put(Index);
      if Index < 15 then
         Put_Line(" and is less than 15.");
      else
         Put_Line(" and is 15 or greater.");
      end if;
   end loop;


   for Index in 13..17 loop  -- This introduces the elsif statement
      Put("Index is");
      Put(Index);
      if Index < 15 then
         Put_Line(" and is less than 15.");
      elsif Index = 15 then
         Put_Line(" and is 15.");
      elsif Index = 16 then
         Put_Line(" and is 16.");
      else
         Put_Line(" and is greater than 16.");
      end if;
   end loop;


-- This final group of statements contains a loop with a nested if
--   statement, and a loop within the the else part of the nested
--   if statement.

   for Index in 13..17 loop
      Put("Index is");
      Put(Index);
      if Index < 16 then
         if Index > 14 then
            Put(" and is less than 16 and greater than 14.");
         else
            Put(" and is less than or equal to 14.");
         end if;
      else
         Put(" and is 16 or greater.");
         for New_Index in 222..224 loop
           Put(" stutter");
         end loop;
      end if;
   
   end loop;

end IfDemo;