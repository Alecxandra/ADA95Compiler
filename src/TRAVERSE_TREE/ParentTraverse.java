/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TRAVERSE_TREE;

import AST_TREE.*;

/**
 *
 * @author alecx
 */
public interface ParentTraverse {
    public void traverse(Type x);
    public void traverse(IntegerType x);
    public void traverse(BooleanType x);
    public void traverse(FloatType x);
    public void traverse(TrueType x);
    public void traverse(FalseType x);
    public void traverse(FloatLiteral x);
    public void traverse(LogicalExpression x);
    public void traverse(LiteralExpression x);
    public void traverse(Identifier x);
    public void traverse(Expression x);
    public void traverse(ArithmeticExpression x);
    public void traverse(Add x);
    public void traverse(Min x);
    public void traverse(Mul x);
    public void traverse(Div x);
    public void traverse(Power x);
    public void traverse(Umin x);
    public void traverse(BooleanExpression x);
    public void traverse(LessEqual x);
    public void traverse(GreaterEqual x);
    public void traverse(Distinct x);
    public void traverse(Greater x);
    public void traverse(Less x);
    public void traverse(Equal x);
    public void traverse(And x);
    public void traverse(Or x);
    public void traverse(Not x);
    public void traverse(ArgumentList x);
    
    
}
