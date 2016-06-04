/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TRAVERSE_TREE;

import AST_TREE.Add;
import AST_TREE.And;
import AST_TREE.ArgumentList;
import AST_TREE.ArithmeticExpression;
import AST_TREE.AssignmentStatement;
import AST_TREE.AssignmentStatementError;
import AST_TREE.BooleanExpression;
import AST_TREE.BooleanType;
import AST_TREE.DeclarationStatement;
import AST_TREE.Declarations;
import AST_TREE.DeclareStatement;
import AST_TREE.DeclareStatementError;
import AST_TREE.Distinct;
import AST_TREE.Div;
import AST_TREE.ElseIfStatement;
import AST_TREE.ElseIfStatementError;
import AST_TREE.ElsifStatements;
import AST_TREE.Equal;
import AST_TREE.ExitStatement;
import AST_TREE.ExitStatementError;
import AST_TREE.Expression;
import AST_TREE.FalseType;
import AST_TREE.FloatLiteral;
import AST_TREE.FloatType;
import AST_TREE.ForStatement;
import AST_TREE.ForStatementError;
import AST_TREE.FunctionCall;
import AST_TREE.FunctionCallError;
import AST_TREE.FunctionStatement;
import AST_TREE.FunctionStatementError;
import AST_TREE.Get;
import AST_TREE.GetError;
import AST_TREE.Greater;
import AST_TREE.GreaterEqual;
import AST_TREE.IOStatement;
import AST_TREE.Identifier;
import AST_TREE.IfStatement;
import AST_TREE.IfStatementError;
import AST_TREE.In;
import AST_TREE.InOut;
import AST_TREE.IntegerLiteral;
import AST_TREE.IntegerType;
import AST_TREE.Less;
import AST_TREE.LessEqual;
import AST_TREE.LiteralExpression;
import AST_TREE.LogicalExpression;
import AST_TREE.LoopStatement;
import AST_TREE.LoopStatementError;
import AST_TREE.Min;
import AST_TREE.Mul;
import AST_TREE.Not;
import AST_TREE.NullType;
import AST_TREE.Or;
import AST_TREE.Out;
import AST_TREE.ParamsList;
import AST_TREE.ParamsModifier;
import AST_TREE.Power;
import AST_TREE.ProcedureStatement;
import AST_TREE.ProcedureStatementError;
import AST_TREE.ProgramInit;
import AST_TREE.ProgramInitError;
import AST_TREE.Put;
import AST_TREE.PutError;
import AST_TREE.ReturnStatement;
import AST_TREE.ReturnStatementError;
import AST_TREE.Statement;
import AST_TREE.StringLiteral;
import AST_TREE.StringType;
import AST_TREE.TrueType;
import AST_TREE.Type;
import AST_TREE.Umin;
import AST_TREE.VariableList;
import AST_TREE.WhileStatement;
import AST_TREE.WhileStatementError;
import INTERM_LANG.IntermediateExpression;
import INTERM_LANG.IntermediateForm;
import INTERM_LANG.IntermediateStatement;
import INTERM_LANG.Label;
import INTERM_LANG.Quadruple;
import INTERM_LANG.Temporal;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cbanegas
 */
public class IntermediateCode implements IntermediateTraverse{
    private BufferedWriter out;
    private StringBuilder file;
    private IntermediateStatement program;
    private SemanticAnalysis semanticTable;
    private static final String GLOBAL_SCOPE = "s0";
    private ArrayList<String> stringsTable;
    private ArrayList<Double> doublesTable;
    
    public IntermediateCode(File outputFile, SemanticAnalysis semanticTable) throws IOException {
        this.out = new BufferedWriter(new FileWriter(outputFile));
        this.semanticTable = semanticTable;
        stringsTable = new ArrayList();
        doublesTable = new ArrayList();
    }
    
    public void createFile(String content) throws IOException {
        out.write(content);
        out.flush();
        out.close();
    }
    
    public void complete(ArrayList<Label> list, Label label) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setLabelName(label.toString());
        }
    }
    
    public ArrayList<Label> merge(ArrayList<Label> list1, ArrayList<Label> list2) {
        ArrayList<Label> newList = new ArrayList();
        newList.addAll(list1);
        newList.addAll(list2);
        return newList;
    }
    
    public ArrayList<String> getStringsTable() {
        return stringsTable;
    }
    
    public ArrayList<Double> getDoublesTable() {
        return doublesTable;
    }
    
    @Override
    public IntermediateForm traverse(Type x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(IntegerType x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(BooleanType x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(FloatType x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(TrueType x) {
        IntermediateExpression ie = new IntermediateExpression();
        Label next = new Label();
        ie.operations.add(new Quadruple("","","",Quadruple.Operations.GOTO, next));
        ie.getTrue().add(next);
        ie.setPlace(new Temporal(Integer.toString(1)));
        return ie;
    }

    @Override
    public IntermediateForm traverse(FalseType x) {
        IntermediateExpression ie = new IntermediateExpression();
        Label next = new Label();
        ie.operations.add(new Quadruple("","","",Quadruple.Operations.GOTO, next));
        ie.getFalse().add(next);
        ie.setPlace(new Temporal(Integer.toString(0)));
        return ie;
    }

    @Override
    public IntermediateForm traverse(FloatLiteral x) {
         IntermediateExpression ie = new IntermediateExpression();
        ie.setPlace(new Temporal(Float.toString(x.num)));
        return ie;
    }

    @Override
    public IntermediateForm traverse(LogicalExpression x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(LiteralExpression x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(Identifier x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(Expression x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(ArithmeticExpression x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(Add x) {
        IntermediateExpression ie = new IntermediateExpression();
        
    }

    @Override
    public IntermediateForm traverse(Min x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(Mul x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(Div x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(Power x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(Umin x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(BooleanExpression x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(LessEqual x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(GreaterEqual x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(Distinct x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(Greater x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(Less x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(Equal x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(And x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(Or x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(Not x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(ArgumentList x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(VariableList x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(DeclareStatement x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(AssignmentStatement x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(IOStatement x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(Get x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(Put x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(ReturnStatement x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(IfStatement x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(ElseIfStatement x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(ElsifStatements x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(WhileStatement x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(ExitStatement x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(LoopStatement x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(ForStatement x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(In x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(Out x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(InOut x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(ParamsModifier x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(ParamsList x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(ProcedureStatement x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(FunctionStatement x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(DeclarationStatement x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(Declarations x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(ProgramInit x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(WhileStatementError x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(ExitStatementError x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(LoopStatementError x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(ForStatementError x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(ProcedureStatementError x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(DeclareStatementError x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(AssignmentStatementError x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(FunctionCallError x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(FunctionCall x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(IfStatementError x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(ElseIfStatementError x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(FunctionStatementError x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(ReturnStatementError x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(PutError x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(GetError x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(ProgramInitError x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(StringType x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(NullType x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(Statement x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(IntegerLiteral x) {
        IntermediateExpression ie = new IntermediateExpression();
        ie.setPlace(new Temporal(Integer.toString(x.num)));
        return ie;
    }

    @Override
    public IntermediateForm traverse(StringLiteral x) {
        IntermediateExpression ie = new IntermediateExpression();
        ie.setPlace(new Temporal("\""+x.str+"\""));
        if(!stringsTable.contains(x.str)){
          stringsTable.add(x.str);
        }
        return ie;
    }
    
}
