procedure Sum_Squares(A, B : in integer; Result : out integer) is
 function Square(X : in integer) return integer is
 begin -- this is the beginning of Square
 return X*X;
 end Square;
begin -- this is the beginning of Sum_Squares
 Result := Square(A) +Square(B);
end Sum_Squares; 