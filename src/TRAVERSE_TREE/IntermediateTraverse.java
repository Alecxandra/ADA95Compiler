/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TRAVERSE_TREE;

import AST_TREE.*;
import INTERM_LANG.IntermediateForm;


/**
 *
 * @author alecx
 */
public interface IntermediateTraverse {
    public IntermediateForm traverse(Type x);
    public IntermediateForm traverse(IntegerType x);
    public IntermediateForm traverse(BooleanType x);
    public IntermediateForm traverse(FloatType x);
    public IntermediateForm traverse(TrueType x);
    public IntermediateForm traverse(FalseType x);
    public IntermediateForm traverse(FloatLiteral x);
    public IntermediateForm traverse(LogicalExpression x);
    public IntermediateForm traverse(LiteralExpression x);
    public IntermediateForm traverse(Identifier x);
    public IntermediateForm traverse(Expression x);
    public IntermediateForm traverse(ArithmeticExpression x);
    public IntermediateForm traverse(Add x);
    public IntermediateForm traverse(Min x);
    public IntermediateForm traverse(Mul x);
    public IntermediateForm traverse(Div x);
    public IntermediateForm traverse(Power x);
    public IntermediateForm traverse(Umin x);
    public IntermediateForm traverse(BooleanExpression x);
    public IntermediateForm traverse(LessEqual x);
    public IntermediateForm traverse(GreaterEqual x);
    public IntermediateForm traverse(Distinct x);
    public IntermediateForm traverse(Greater x);
    public IntermediateForm traverse(Less x);
    public IntermediateForm traverse(Equal x);
    public IntermediateForm traverse(And x);
    public IntermediateForm traverse(Or x);
    public IntermediateForm traverse(Not x);
    public IntermediateForm traverse(ArgumentList x);
    public IntermediateForm traverse(VariableList x);
    public IntermediateForm traverse(DeclareStatement x); 
    public IntermediateForm traverse(AssignmentStatement x); 
    public IntermediateForm traverse(IOStatement x);
    public IntermediateForm traverse(Get x); 
    public IntermediateForm traverse(Put x); 
    public IntermediateForm traverse(ReturnStatement x); 
    public IntermediateForm traverse(IfStatement x); 
    public IntermediateForm traverse(ElseIfStatement x);
    public IntermediateForm traverse(ElsifStatements x);
    public IntermediateForm traverse(WhileStatement x); 
    public IntermediateForm traverse(ExitStatement x);
    public IntermediateForm traverse(LoopStatement x);
    public IntermediateForm traverse(ForStatement x);
    public IntermediateForm traverse(In x);
    public IntermediateForm traverse(Out x);
    public IntermediateForm traverse(InOut x);
    public IntermediateForm traverse(ParamsModifier x);
    public IntermediateForm traverse(ParamsList x);
    public IntermediateForm traverse(ProcedureStatement x);
    public IntermediateForm traverse(FunctionStatement x);
    public IntermediateForm traverse(DeclarationStatement x);
    public IntermediateForm traverse(Declarations x);
    public IntermediateForm traverse(ProgramInit x);
    public IntermediateForm traverse(WhileStatementError x);
    public IntermediateForm traverse(ExitStatementError x);
    public IntermediateForm traverse(LoopStatementError x);
    public IntermediateForm traverse(ForStatementError x);
    public IntermediateForm traverse(ProcedureStatementError x);
    public IntermediateForm traverse(DeclareStatementError x);
    public IntermediateForm traverse(AssignmentStatementError x);
    public IntermediateForm traverse(FunctionCallError x);
    public IntermediateForm traverse(FunctionCall x);
    public IntermediateForm traverse(IfStatementError x);
    public IntermediateForm traverse(ElseIfStatementError x);
    public IntermediateForm traverse(FunctionStatementError x);
    public IntermediateForm traverse(ReturnStatementError x);
    public IntermediateForm traverse(PutError x);
    public IntermediateForm traverse(GetError x);
    public IntermediateForm traverse(ProgramInitError x);
    public IntermediateForm traverse(StringType x);
    public IntermediateForm traverse(NullType x);
    public IntermediateForm traverse(Statement x);
    public IntermediateForm traverse(IntegerLiteral x);
    public IntermediateForm traverse(StringLiteral x);
}
