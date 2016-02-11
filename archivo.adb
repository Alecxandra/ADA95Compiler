
--
-- Integer calculator program.  Takes lines of input consisting of
-- <operator> <number>, and applies each one to a display value.  The
-- display value is printed at each step.  The operator is one of =,
-- +, -, *, /, or ^, which correspond to assign, add, subtract, multiply
-- divide, and raise, respectively.  The display value is initially zero.
-- The program terminates on a input of q.
--

procedure Calc is
                -- Operation to perform.
   Disp: integer := 0;          -- Contents of the display.
   In_Val: integer;             -- Input value used to update the display.
begin
   loop
      -- Print the display.
      put(Disp);
      

      -- Promt the user.
      put("> ");

      -- Skip leading blanks and read the operation.
      loop
         get(Op);
         exit when Op /= " ";
      end loop;

      -- Stop when we're s'posed to.
      exit when Op = "Q" or Op = "q";

      -- Read the integer value (skips leading blanks) and discard the
      -- remainder of the line.
      get(In_Val);
      

      -- Apply the correct operation.
      
   end loop;
end Calc;