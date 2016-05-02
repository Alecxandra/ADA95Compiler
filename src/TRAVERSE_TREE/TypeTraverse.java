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
public interface TypeTraverse {
    public Type traverse(Type x);
    public Type traverse(IntegerType x);
    public Type traverse(BooleanType x);
    public Type traverse(FloatType x);
    public Type traverse(TrueType x);
    public Type traverse(FalseType x);
    public Type traverse(FloatLiteral x);
    public Type traverse(LogicalExpression x);
    public Type traverse(LiteralExpression x);
    public Type traverse(Identifier x);
    public Type traverse(Expression x);
    public Type traverse(ArithmeticExpression x);
    public Type traverse(Add x);
    public Type traverse(Min x);
    public Type traverse(Mul x);
    public Type traverse(Div x);
    public Type traverse(Power x);
    public Type traverse(Umin x);
    public Type traverse(BooleanExpression x);
    public Type traverse(LessEqual x);
    public Type traverse(GreaterEqual x);
    public Type traverse(Distinct x);
    public Type traverse(Greater x);
    public Type traverse(Less x);
    public Type traverse(Equal x);
    public Type traverse(And x);
    public Type traverse(Or x);
    public Type traverse(Not x);
    public Type traverse(ArgumentList x);
    public Type traverse(VariableList x);
    public Type traverse(DeclareStatement x); 
    public Type traverse(AssignmentStatement x); 
    public Type traverse(IOStatement x);
    public Type traverse(Get x); 
    public Type traverse(ReturnStatement x); 
    public Type traverse(IfStatement x); 
    public Type traverse(ElseIfStatement x);
    public Type traverse(ElsifStatements x);
    public Type traverse(WhileStatement x); 
    public Type traverse(ExitStatement x);
    public Type traverse(LoopStatement x);
    public Type traverse(ForStatement x);
    public Type traverse(In x);
    public Type traverse(Out x);
    public Type traverse(InOut x);
    public Type traverse(ParamsModifier x);
    public Type traverse(ParamsList x);
    public Type traverse(ProcedureStatement x);
    public Type traverse(FunctionStatement x);
    public Type traverse(DeclarationStatement x);
    public Type traverse(Declarations x);
    public Type traverse(ProgramInit x);
    public Type traverse(WhileStatementError x);
    public Type traverse(ExitStatementError x);
    public Type traverse(LoopStatementError x);
    public Type traverse(ForStatementError x);
    public Type traverse(ProcedureStatementError x);
    public Type traverse(DeclareStatementError x);
    public Type traverse(AssignmentStatementError x);
    public Type traverse(FunctionCallError x);
    public Type traverse(FunctionCall x);
    public Type traverse(IfStatementError x);
    public Type traverse(ElseIfStatementError x);
    public Type traverse(FunctionStatementError x);
    public Type traverse(ReturnStatementError x);
    public Type traverse(PutError x);
    public Type traverse(GetError x);
    public Type traverse(ProgramInitError x);
    public Type traverse(StringType x);
    public Type traverse(NullType x);
    public Type traverse(Statement x);
    public Type traverse(IntegerLiteral x);
    public Type traverse(StringLiteral x);
}
