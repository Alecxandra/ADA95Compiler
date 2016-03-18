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
    public void traverse(VariableList x);
    public void traverse(DeclareStatement x); 
    public void traverse(AssignmentStatement x); 
    public void traverse(IOStatement x);
    public void traverse(Get x); 
    public void traverse(ReturnStatement x); 
    public void traverse(IfStatement x); 
    public void traverse(ElseIfStatement x);
    public void traverse(ElsifStatements x);
    public void traverse(WhileStatement x); 
    public void traverse(ExitStatement x);
    public void traverse(LoopStatement x);
    public void traverse(ForStatement x);
    public void traverse(In x);
    public void traverse(Out x);
    public void traverse(InOut x);
    public void traverse(ParamsModifier x);
    public void traverse(ParamsList x);
    public void traverse(ProcedureStatement x);
    public void traverse(FunctionStatement x);
    public void traverse(DeclarationStatement x);
    public void traverse(Declarations x);
    public void traverse(ProgramInit x);
    public void traverse(WhileStatementError x);
    public void traverse(ExitStatementError x);
    public void traverse(LoopStatementError x);
    public void traverse(ForStatementError x);
    public void traverse(ProcedureStatementError x);
    public void traverse(DeclareStatementError x);
    public void traverse(AssignmentStatementError x);
    public void traverse(FunctionCallError x);
    public void traverse(IfStatementError x);
    public void traverse(ElseIfStatementError x);
    public void traverse(IdentifierError x);
    public void traverse(FunctionStatementError x);
    public void traverse(ReturnStatementError x);
}
