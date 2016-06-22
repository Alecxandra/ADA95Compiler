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
import AST_TREE.ErrorType;
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
import AST_TREE.Statements;
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
import INTERM_LANG.QuadrupleList;
import INTERM_LANG.Temporal;
import ada95compiler.Scope;
import ada95compiler.SymbolTableNode;
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
public class IntermediateCode implements IntermediateTraverse {

    private BufferedWriter out;
    private StringBuilder file;
    private IntermediateStatement program;
    private SemanticAnalysis semanticTable;
    private ArrayList<String> stringsTable;
    private ArrayList<Double> doublesTable;
    private String current_function;
    private String scope;

    public IntermediateCode(File outputFile, SemanticAnalysis semanticTable) throws IOException {
        this.out = new BufferedWriter(new FileWriter(outputFile));
        this.semanticTable = semanticTable;
        stringsTable = new ArrayList();
        doublesTable = new ArrayList();
        this.scope = "";
        Scope.resetCount();
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
        ie.operations.add(new Quadruple("", "", "", Quadruple.Operations.GOTO, next));
        ie.getTrue().add(next);
        ie.setPlace(new Temporal(Integer.toString(1)));
        return ie;
    }

    @Override
    public IntermediateForm traverse(FalseType x) {
        IntermediateExpression ie = new IntermediateExpression();
        Label next = new Label();
        ie.operations.add(new Quadruple("", "", "", Quadruple.Operations.GOTO, next));
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
        if (x instanceof And) {
            return ((And) x).accept(this);
        } else if (x instanceof Or) {
            return ((Or) x).accept(this);
        } else if (x instanceof Not) {
            return ((Not) x).accept(this);
        } else if (x instanceof TrueType) {
            return ((TrueType) x).accept(this);
        } else if (x instanceof FalseType) {
            return ((FalseType) x).accept(this);
        } else {
            return new IntermediateExpression();
        }
    }

    @Override
    public IntermediateForm traverse(LiteralExpression x) {
        if (x instanceof FloatLiteral) {
            return ((FloatLiteral) x).accept(this);
        } else if (x instanceof IntegerLiteral) {
            return ((IntegerLiteral) x).accept(this);
        } else if (x instanceof StringLiteral) {
            return ((StringLiteral) x).accept(this);
        } else {
            return new IntermediateExpression();
        }
    }

    @Override
    public IntermediateForm traverse(Identifier x) {
        SymbolTableNode node = this.semanticTable.getSymboltable().findSymbol(x.id,this.scope);
        IntermediateExpression ie = new IntermediateExpression();
        ie.setPlace(new Temporal("_"+x.id+ "_"+ node.getScope()));
        return ie;
    }

    @Override
    public IntermediateForm traverse(Expression x) {
        if (x instanceof Identifier) {
            return ((Identifier) x).accept(this);
        } else if (x instanceof LiteralExpression) {
            return ((LiteralExpression) x).accept(this);
        } else if (x instanceof ArithmeticExpression) {
            return ((ArithmeticExpression) x).accept(this);
        } else if (x instanceof Expression) {
            return ((Expression) x).accept(this);
        } else if (x instanceof BooleanExpression) {
            return ((BooleanExpression) x).accept(this);
        } else if (x instanceof LogicalExpression) {
            return ((LogicalExpression) x).accept(this);
        } else if (x instanceof FunctionCall) {
            return ((FunctionCall) x).accept(this);
        } else {
            return new IntermediateExpression();
        }
    }

    @Override
    public IntermediateForm traverse(ArithmeticExpression x) {
        if (x instanceof Add) {
            return ((Add) x).accept(this);
        } else if (x instanceof Min) {
            return ((Min) x).accept(this);
        } else if (x instanceof Mul) {
            return ((Mul) x).accept(this);
        } else if (x instanceof Div) {
            return ((Div) x).accept(this);
        } else if (x instanceof Power) {
            return ((Power) x).accept(this);
        } else if (x instanceof Umin) {
            return ((Umin) x).accept(this);
        } else {
            return new IntermediateExpression();
        }
    }

    @Override
    public IntermediateForm traverse(Add x) {
        IntermediateExpression ie = new IntermediateExpression();
        Temporal temp = new Temporal();
        IntermediateExpression ex1 = (IntermediateExpression) x.exp1.accept(this);
        IntermediateExpression ex2 = (IntermediateExpression) x.exp2.accept(this);
        ie.operations = ie.operations.merge(ex1.operations);
        ie.operations = ie.operations.merge(ex2.operations);
        Quadruple quad = new Quadruple(temp.toString(), ex1.getPlace().toString(), ex2.getPlace().toString(), Quadruple.Operations.ADD);
        ie.setPlace(temp);
        ie.operations.add(quad);
        return ie;
    }

    @Override
    public IntermediateForm traverse(Min x) {
        IntermediateExpression ie = new IntermediateExpression();
        Temporal temp = new Temporal();
        IntermediateExpression ex1 = (IntermediateExpression) x.exp1.accept(this);
        IntermediateExpression ex2 = (IntermediateExpression) x.exp2.accept(this);
        ie.operations = ie.operations.merge(ex1.operations);
        ie.operations = ie.operations.merge(ex2.operations);
        Quadruple quad = new Quadruple(temp.toString(), ex1.getPlace().toString(), ex2.getPlace().toString(), Quadruple.Operations.MIN);
        ie.setPlace(temp);
        ie.operations.add(quad);
        return ie;
    }

    @Override
    public IntermediateForm traverse(Mul x) {
        IntermediateExpression ie = new IntermediateExpression();
        Temporal temp = new Temporal();
        IntermediateExpression ex1 = (IntermediateExpression) x.exp1.accept(this);
        IntermediateExpression ex2 = (IntermediateExpression) x.exp2.accept(this);
        ie.operations = ie.operations.merge(ex1.operations);
        ie.operations = ie.operations.merge(ex2.operations);
        Quadruple quad = new Quadruple(temp.toString(), ex1.getPlace().toString(), ex2.getPlace().toString(), Quadruple.Operations.MUL);
        ie.setPlace(temp);
        ie.operations.add(quad);
        return ie;
    }

    @Override
    public IntermediateForm traverse(Div x) {
        IntermediateExpression ie = new IntermediateExpression();
        Temporal temp = new Temporal();
        IntermediateExpression ex1 = (IntermediateExpression) x.exp1.accept(this);
        IntermediateExpression ex2 = (IntermediateExpression) x.exp2.accept(this);
        ie.operations = ie.operations.merge(ex1.operations);
        ie.operations = ie.operations.merge(ex2.operations);
        Quadruple quad = new Quadruple(temp.toString(), ex1.getPlace().toString(), ex2.getPlace().toString(), Quadruple.Operations.DIV);
        ie.setPlace(temp);
        ie.operations.add(quad);
        return ie;
    }

    @Override
    public IntermediateForm traverse(Power x) {
        IntermediateExpression ie = new IntermediateExpression();
        Temporal temp = new Temporal();
        IntermediateExpression ex1 = (IntermediateExpression) x.exp1.accept(this);
        IntermediateExpression ex2 = (IntermediateExpression) x.exp2.accept(this);
        ie.operations = ie.operations.merge(ex1.operations);
        ie.operations = ie.operations.merge(ex2.operations);
        Quadruple quad = new Quadruple(temp.toString(), ex1.getPlace().toString(), ex2.getPlace().toString(), Quadruple.Operations.POWER);
        ie.setPlace(temp);
        ie.operations.add(quad);
        return ie;
    }

    @Override
    public IntermediateForm traverse(Umin x) {
        IntermediateExpression ie = new IntermediateExpression();
        Temporal temp = new Temporal();
        IntermediateExpression ex1 = (IntermediateExpression) x.exp.accept(this);

        ie.operations = ie.operations.merge(ex1.operations);
        Quadruple quad = new Quadruple(temp.toString(), ex1.getPlace().toString(), "", Quadruple.Operations.UMIN);
        ie.setPlace(temp);
        ie.operations.add(quad);
        return ie;
    }

    @Override
    public IntermediateForm traverse(BooleanExpression x) {
        if (x instanceof LessEqual) {
            return ((LessEqual) x).accept(this);
        } else if (x instanceof GreaterEqual) {
            return ((GreaterEqual) x).accept(this);
        } else if (x instanceof Distinct) {
            return ((Distinct) x).accept(this);
        } else if (x instanceof Greater) {
            return ((Greater) x).accept(this);
        } else if (x instanceof Less) {
            return ((Less) x).accept(this);
        } else if (x instanceof Equal) {
            return ((Equal) x).accept(this);
        } else {
            return new IntermediateExpression();
        }

    }

    @Override
    public IntermediateForm traverse(LessEqual x) {
        IntermediateExpression ie = new IntermediateExpression();
        IntermediateExpression expre1 = (IntermediateExpression) x.exp1.accept(this);
        IntermediateExpression expre2 = (IntermediateExpression) x.exp2.accept(this);

        ie.operations = ie.operations.merge(expre1.operations);
        ie.operations = ie.operations.merge(expre2.operations);

        Label truelabel = new Label("");
        Quadruple cuad1 = new Quadruple("", expre1.getPlace().toString(), expre2.getPlace().toString(), Quadruple.Operations.IF_LEQ, truelabel);
        ie.getTrue().add(truelabel);
        ie.operations.add(cuad1);

        Label falselabel = new Label("");
        Quadruple cuad2 = new Quadruple("", "", "", Quadruple.Operations.GOTO, falselabel);
        ie.getFalse().add(falselabel);
        ie.operations.add(cuad2);

        return ie;
    }

    @Override
    public IntermediateForm traverse(GreaterEqual x) {
        IntermediateExpression ie = new IntermediateExpression();
        IntermediateExpression expre1 = (IntermediateExpression) x.exp1.accept(this);
        IntermediateExpression expre2 = (IntermediateExpression) x.exp2.accept(this);

        ie.operations = ie.operations.merge(expre1.operations);
        ie.operations = ie.operations.merge(expre2.operations);

        Label truelabel = new Label("");
        Quadruple cuad1 = new Quadruple("", expre1.getPlace().toString(), expre2.getPlace().toString(), Quadruple.Operations.IF_GEQ, truelabel);
        ie.getTrue().add(truelabel);
        ie.operations.add(cuad1);

        Label falselabel = new Label("");
        Quadruple cuad2 = new Quadruple("", "", "", Quadruple.Operations.GOTO, falselabel);
        ie.getFalse().add(falselabel);
        ie.operations.add(cuad2);

        return ie;
    }

    @Override
    public IntermediateForm traverse(Distinct x) {
        IntermediateExpression ie = new IntermediateExpression();
        IntermediateExpression expre1 = (IntermediateExpression) x.exp1.accept(this);
        IntermediateExpression expre2 = (IntermediateExpression) x.exp2.accept(this);

        ie.operations = ie.operations.merge(expre1.operations);
        ie.operations = ie.operations.merge(expre2.operations);

        Label truelabel = new Label("");
        Quadruple cuad1 = new Quadruple("", expre1.getPlace().toString(), expre2.getPlace().toString(), Quadruple.Operations.IF_NEQ, truelabel);
        ie.getTrue().add(truelabel);
        ie.operations.add(cuad1);

        Label falselabel = new Label("");
        Quadruple cuad2 = new Quadruple("", "", "", Quadruple.Operations.GOTO, falselabel);
        ie.getFalse().add(falselabel);
        ie.operations.add(cuad2);

        return ie;
    }

    @Override
    public IntermediateForm traverse(Greater x) {
        IntermediateExpression ie = new IntermediateExpression();
        IntermediateExpression expre1 = (IntermediateExpression) x.exp1.accept(this);
        IntermediateExpression expre2 = (IntermediateExpression) x.exp2.accept(this);

        ie.operations = ie.operations.merge(expre1.operations);
        ie.operations = ie.operations.merge(expre2.operations);

        Label truelabel = new Label("");
        Quadruple cuad1 = new Quadruple("", expre1.getPlace().toString(), expre2.getPlace().toString(), Quadruple.Operations.IF_GT, truelabel);
        ie.getTrue().add(truelabel);
        ie.operations.add(cuad1);

        Label falselabel = new Label("");
        Quadruple cuad2 = new Quadruple("", "", "", Quadruple.Operations.GOTO, falselabel);
        ie.getFalse().add(falselabel);
        ie.operations.add(cuad2);

        return ie;
    }

    @Override
    public IntermediateForm traverse(Less x) {
        IntermediateExpression ie = new IntermediateExpression();
        IntermediateExpression expre1 = (IntermediateExpression) x.exp1.accept(this);
        IntermediateExpression expre2 = (IntermediateExpression) x.exp2.accept(this);

        ie.operations = ie.operations.merge(expre1.operations);
        ie.operations = ie.operations.merge(expre2.operations);

        Label truelabel = new Label("");
        Quadruple cuad1 = new Quadruple("", expre1.getPlace().toString(), expre2.getPlace().toString(), Quadruple.Operations.IF_LT, truelabel);
        ie.getTrue().add(truelabel);
        ie.operations.add(cuad1);

        Label falselabel = new Label("");
        Quadruple cuad2 = new Quadruple("", "", "", Quadruple.Operations.GOTO, falselabel);
        ie.getFalse().add(falselabel);
        ie.operations.add(cuad2);

        return ie;
    }

    @Override
    public IntermediateForm traverse(Equal x) {
        IntermediateExpression ie = new IntermediateExpression();
        IntermediateExpression expre1 = (IntermediateExpression) x.exp1.accept(this);
        IntermediateExpression expre2 = (IntermediateExpression) x.exp2.accept(this);

        ie.operations = ie.operations.merge(expre1.operations);
        ie.operations = ie.operations.merge(expre2.operations);

        Label truelabel = new Label("");
        Quadruple cuad1 = new Quadruple("", expre1.getPlace().toString(), expre2.getPlace().toString(), Quadruple.Operations.IF_EQ, truelabel);
        ie.getTrue().add(truelabel);
        ie.operations.add(cuad1);

        Label falselabel = new Label("");
        Quadruple cuad2 = new Quadruple("", "", "", Quadruple.Operations.GOTO, falselabel);
        ie.getFalse().add(falselabel);
        ie.operations.add(cuad2);

        return ie;
    }

    @Override
    public IntermediateForm traverse(And x) {
        IntermediateExpression ie = new IntermediateExpression();
        IntermediateExpression expre1 = (IntermediateExpression) x.exp1.accept(this);
        IntermediateExpression expre2 = (IntermediateExpression) x.exp2.accept(this);

        Label truelabel = new Label();
        QuadrupleList list = new QuadrupleList();
        list = list.merge(expre1.operations);
        list.add(new Quadruple(truelabel));
        list = list.merge(expre2.operations);
        ie.operations = list;
        complete(expre1.getTrue(), truelabel);
        ie.setFalse(merge(expre1.getFalse(), expre2.getFalse()));
        ie.setTrue(expre2.getTrue());

        return ie;
    }

    @Override
    public IntermediateForm traverse(Or x) {
        IntermediateExpression ie = new IntermediateExpression();
        IntermediateExpression expre1 = (IntermediateExpression) x.exp1.accept(this);
        IntermediateExpression expre2 = (IntermediateExpression) x.exp1.accept(this);
        Label falselabel = new Label();
        QuadrupleList list = new QuadrupleList();
        list = list.merge(expre1.operations);
        list.add(new Quadruple(falselabel));
        list = list.merge(expre2.operations);
        ie.operations = list;
        complete(expre1.getFalse(), falselabel);
        ie.setTrue(merge(expre1.getTrue(), expre2.getTrue()));
        ie.setFalse(expre2.getFalse());
        return ie;
    }

    @Override
    public IntermediateForm traverse(Not x) {
        IntermediateExpression ie = new IntermediateExpression();
        IntermediateExpression expre = (IntermediateExpression) x.exp.accept(this);
        ie.setFalse(expre.getTrue());
        ie.setTrue(expre.getFalse());
        ie.operations = expre.operations;
        return ie;
    }

    @Override
    public IntermediateForm traverse(ArgumentList x) {
        IntermediateExpression ie = new IntermediateExpression();
        for (int i = 0; i < x.size(); i++) {
            IntermediateExpression expre = (IntermediateExpression) x.elementAt(i).accept(this);
            ie.operations = ie.operations.merge(expre.operations);
            ie.operations.add(new Quadruple("", expre.getPlace().toString(), "", Quadruple.Operations.PARAM));
        }
        return ie;
    }

    @Override
    public IntermediateForm traverse(VariableList x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntermediateForm traverse(DeclareStatement x) {
        return new IntermediateStatement();
    }

    @Override
    public IntermediateForm traverse(AssignmentStatement x) {
        IntermediateStatement ie = new IntermediateStatement();
        IntermediateExpression expre = (IntermediateExpression) x.expre.accept(this);
        IntermediateExpression id = (IntermediateExpression) x.id.accept(this);

        ie.operations = ie.operations.merge(expre.operations);
        ie.operations.add(new Quadruple(id.getPlace().toString(), expre.getPlace().toString(), "", Quadruple.Operations.ASSIGN));
        return ie;
    }

    @Override
    public IntermediateForm traverse(IOStatement x) {
        if (x instanceof Get) {
            return ((Get) x).accept(this);
        } else {
            return ((Put) x).accept(this);
        }
    }

    @Override
    public IntermediateForm traverse(Get x) {
        IntermediateStatement ie = new IntermediateStatement();
        IntermediateExpression expre = (IntermediateExpression) x.id.accept(this);
        ie.operations = ie.operations.merge(expre.operations);
        ie.operations.add(new Quadruple("", expre.getPlace().toString(), "", Quadruple.Operations.READ));
        return ie;
    }

    @Override
    public IntermediateForm traverse(Put x) {
        IntermediateStatement ie = new IntermediateStatement();
        IntermediateExpression expre = (IntermediateExpression) x.expre.accept(this);
        ie.operations = ie.operations.merge(expre.operations);
        ie.operations.add(new Quadruple("", expre.getPlace().toString(), "", Quadruple.Operations.PRINT));
        return ie;
    }

    @Override
    public IntermediateForm traverse(ReturnStatement x) {
        IntermediateStatement ie = new IntermediateStatement();
        IntermediateExpression expre = (IntermediateExpression) x.expre.accept(this);
        ie.operations = ie.operations.merge(expre.operations);
        ie.operations.add(new Quadruple("RET", expre.getPlace().toString(), "", Quadruple.Operations.ASSIGN, new Label(this.current_function)));
        return ie;
    }

    @Override
    public IntermediateForm traverse(IfStatement x) {
        if (x.sta1 != null && x.sta2 == null && x.esta == null) { //if
            IntermediateStatement ie = new IntermediateStatement();
            QuadrupleList list = new QuadrupleList();
            IntermediateExpression expre = (IntermediateExpression) x.expre.accept(this);
            Label truelabel = new Label();
            complete(expre.getTrue(), truelabel);
            list = list.merge(expre.operations);
            list.add(new Quadruple(truelabel));

            IntermediateStatement sta = (IntermediateStatement) x.sta1.accept(this);
            list = list.merge(sta.operations);
            ie.operations = list;
            ie.setNext(merge(expre.getFalse(), sta.getNext()));
            ie.setOuterList(sta.getOuterList());
            return ie;
        } else if (x.sta1 != null && x.esta == null && x.sta2 != null) { //if else
            IntermediateStatement ie = new IntermediateStatement();
            QuadrupleList list = new QuadrupleList();
            IntermediateExpression expre = (IntermediateExpression) x.expre.accept(this);
            Label truelabel = new Label();
            Label falselabel = new Label();
            Label fuera = new Label("");
            ArrayList<Label> saltos = new ArrayList();
            saltos.add(fuera);

            complete(expre.getTrue(), truelabel);
            list = list.merge(expre.operations);
            list.add(new Quadruple(truelabel));

            IntermediateStatement sta = (IntermediateStatement) x.sta1.accept(this);
            list = list.merge(sta.operations);
            list.add(new Quadruple("", "", "", Quadruple.Operations.GOTO, fuera));

            complete(expre.getFalse(), falselabel);
            list.add(new Quadruple(falselabel));
            IntermediateStatement sta_else = (IntermediateStatement) x.sta2.accept(this);
            list = list.merge(sta_else.operations);

            ie.operations = list;
            ie.setNext(merge(sta.getNext(), merge(sta_else.getNext(), saltos)));
            ie.setOuterList(sta.getOuterList());
            return ie;

        } else {//if con elseif y/o else

            IntermediateStatement ie = new IntermediateStatement();
            QuadrupleList list = new QuadrupleList();
            Label truelabel = new Label();
            Label falselabel = new Label();
            Label fuera = new Label();
            ArrayList<Label> saltos = new ArrayList();
            saltos.add(fuera);
            IntermediateExpression expre = (IntermediateExpression) x.expre.accept(this);

            complete(expre.getFalse(), falselabel);
            complete(expre.getTrue(), truelabel);
            list = list.merge(expre.operations);
            list.add(new Quadruple(truelabel));

            IntermediateStatement sta_true = (IntermediateStatement) x.sta1.accept(this);
            list = list.merge(sta_true.operations);
            list.add(new Quadruple("", "", "", Quadruple.Operations.GOTO, fuera));

            for (int i = 0; i < x.esta.size(); i++) {
                Label trueif = new Label();
                ElseIfStatement elsif = x.esta.elementAt(i);
                IntermediateExpression expre1 = (IntermediateExpression) elsif.expre.accept(this);
                complete(expre1.getTrue(), trueif);

                list.add(new Quadruple(falselabel));
                list = list.merge(expre1.operations);
                list.add(new Quadruple(trueif));
                IntermediateStatement stas = (IntermediateStatement) elsif.statements.accept(this);
                list = list.merge(stas.operations);
                list.add(new Quadruple("", "", "", Quadruple.Operations.GOTO, fuera));
                falselabel = new Label();
                complete(expre1.getFalse(), falselabel);
                ie.setOuterList(stas.getOuterList());

            }
            list.add(new Quadruple(falselabel));
            if (x.sta2 != null) {
                IntermediateStatement else_sta = (IntermediateStatement) x.sta2.accept(this);
                list = list.merge(else_sta.operations);
                ie.setOuterList(else_sta.getOuterList());
            }
            ie.operations = list;
            ie.setNext(saltos);
            
            return ie;
        }
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
        IntermediateStatement ie = new IntermediateStatement();
        IntermediateExpression expre = (IntermediateExpression) x.expre.accept(this);
        Label truelabel = new Label();
        Label beginlabel = new Label();
        ie.operations.add(new Quadruple(beginlabel));
        ie.operations = ie.operations.merge(expre.operations);
        ie.operations.add(new Quadruple(truelabel));

        IntermediateStatement sta = (IntermediateStatement) x.sta.accept(this);
        ie.operations = ie.operations.merge(sta.operations);
        ie.operations.add(new Quadruple("", "", "", Quadruple.Operations.GOTO, beginlabel));

        complete(expre.getTrue(), truelabel);
        ie.setNext(expre.getFalse());
        ie.setOuterList(sta.getOuterList());
        return ie;

    }

    @Override
    public IntermediateForm traverse(ExitStatement x) {
        IntermediateStatement ie = new IntermediateStatement();
        IntermediateExpression exp1 = (IntermediateExpression) x.expre.accept(this);
        ie.operations = ie.operations.merge(exp1.operations);

        Label irAlSalir = new Label();
        Label salida = new Label("");
        complete(exp1.getTrue(), irAlSalir);
        ie.operations.add(new Quadruple(irAlSalir));
        ie.operations.add(new Quadruple("", "", "", Quadruple.Operations.GOTO, salida));
        ie.setNext(exp1.getFalse());
        ie.getOuterList().add(salida);
        return ie;
    }

    @Override
    public IntermediateForm traverse(LoopStatement x) {
        IntermediateStatement ie = new IntermediateStatement();
        Label inicio = new Label();
        ie.operations.add(new Quadruple(inicio));
        IntermediateStatement sta = (IntermediateStatement) x.sta.accept(this);
        complete(sta.getNext(), inicio);
        ie.operations = ie.operations.merge(sta.operations);
        ie.operations.add(new Quadruple("", "", "", Quadruple.Operations.GOTO, inicio));
        ie.setOuterList(sta.getOuterList());
        return ie;
    }

    @Override
    public IntermediateForm traverse(ForStatement x) {
        IntermediateStatement ie = new IntermediateStatement();
        IntermediateExpression start = (IntermediateExpression) x.start.accept(this);
        IntermediateExpression end = (IntermediateExpression) x.end.accept(this);
        IntermediateExpression id = (IntermediateExpression) x.id.accept(this);
        QuadrupleList list = new QuadrupleList();
        list = list.merge(start.operations);
        list.add(new Quadruple(id.getPlace().toString(), start.getPlace().toString(), "", Quadruple.Operations.ASSIGN));
        list = list.merge(end.operations);
        Label inicio = new Label();
        Label truelabel = new Label();
        Label falselabel = new Label("");
        Label increment = new Label();
        list.add(new Quadruple(inicio));
        list.add(new Quadruple("", id.getPlace().toString(), end.getPlace().toString(), Quadruple.Operations.IF_LEQ, truelabel));
        list.add(new Quadruple("", "", "", Quadruple.Operations.GOTO, falselabel));
        list.add(new Quadruple(truelabel));
        IntermediateStatement statements = (IntermediateStatement) x.sta.accept(this);
        complete(statements.getNext(), increment);
        list = list.merge(statements.operations);
        list.add(new Quadruple(increment));
        list.add(new Quadruple("_"+x.id.id+"_"+this.scope,"_"+x.id.id+"_"+this.scope,"1",Quadruple.Operations.ADD));
        list.add(new Quadruple("", "", "", Quadruple.Operations.GOTO, inicio));
        ie.operations = list;
        ie.getNext().add(falselabel);
        ie.setOuterList(statements.getOuterList());
        return ie;
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
        IntermediateStatement ie = new IntermediateStatement();
        String temp_scope=new String(this.scope);
        String current_scope= new String(this.scope+Scope.getNewScope());
        this.scope= new String(current_scope);
        this.current_function= "_" + x.postid.id + "_"+temp_scope;
        Label name = new Label("_" + x.postid.id + "_"+temp_scope);
        ie.operations.add(new Quadruple(name));
        IntermediateStatement sta = (IntermediateStatement) x.poststa.accept(this);
        ie.operations = ie.operations.merge(sta.operations);
        ie.operations.add(new Quadruple("","_" + x.postid.id + "_"+temp_scope, "", Quadruple.Operations.FUNCTION_END));
        
        for (int i = 0; i < x.presta.size(); i++) {
            IntermediateStatement funcs = (IntermediateStatement) x.presta.elementAt(i).accept(this);
            ie.operations = ie.operations.merge(funcs.operations);
        }
        
        this.scope = temp_scope;
        return ie;
    }

    @Override
    public IntermediateForm traverse(FunctionStatement x) {
        IntermediateStatement ie = new IntermediateStatement();
        String temp_scope = new String(this.scope);
        String current_scope = new String(this.scope + Scope.getNewScope());
        this.scope = new String(current_scope);
        this.current_function= "_" + x.postid.id+ "_"+ temp_scope;
        Label name = new Label("_" + x.postid.id+ "_"+ temp_scope);
        ie.operations.add(new Quadruple(name));
        IntermediateStatement sta = (IntermediateStatement) x.poststa.accept(this);
        ie.operations = ie.operations.merge(sta.operations);
         ie.operations.add(new Quadruple("","_" + x.postid.id+ "_"+ temp_scope,"",Quadruple.Operations.FUNCTION_END));
        for (int i = 0; i < x.presta.size(); i++) {
            IntermediateStatement funcs = (IntermediateStatement) x.presta.elementAt(i).accept(this);
            ie.operations = ie.operations.merge(funcs.operations);
        }
       
        this.scope = temp_scope;
        return ie;
    }

    @Override
    public IntermediateForm traverse(DeclarationStatement x) {
        if (x instanceof ProcedureStatement) {
            return ((ProcedureStatement) x).accept(this);
        } else if (x instanceof FunctionStatement) {
            return ((FunctionStatement) x).accept(this);
        } else {
            return new IntermediateStatement();
        }
    }

    @Override
    public IntermediateForm traverse(Declarations x) {
        IntermediateStatement ie = new IntermediateStatement();
        for (int i = 0; i < x.size(); i++) {
            IntermediateStatement sta = (IntermediateStatement) x.elementAt(i).accept(this);
            ie.operations = ie.operations.merge(sta.operations);
        }
        return ie;
    }

    @Override
    public IntermediateForm traverse(ProgramInit x) {
        IntermediateStatement ie = new IntermediateStatement();
        
        String current_scope= Scope.getNewScope();
        this.scope = new String(current_scope);
        
        Label name = new Label("_" + x.postid.id + "_"+ current_scope);
        ie.operations.add(new Quadruple(name));
        IntermediateStatement sta = (IntermediateStatement) x.stas.accept(this);
        ie.operations = ie.operations.merge(sta.operations);
        ie.operations.add(new Quadruple(Quadruple.Operations.CLOSE));
        
        for (int i = 0; i < x.declarations.size(); i++) {
            IntermediateStatement funcs = (IntermediateStatement) x.declarations.elementAt(i).accept(this);
            ie.operations = ie.operations.merge(funcs.operations);
        }
        return ie;
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
        SymbolTableNode node = this.semanticTable.getSymboltable().findSymbol(x.id.toString(),this.scope);
        IntermediateExpression ie = new IntermediateExpression();
        IntermediateExpression arguments = (IntermediateExpression) x.args.accept(this);
        ie.operations = ie.operations.merge(arguments.operations);
        ie.operations.add(new Quadruple("", "_" + x.id.id+"_"+node.getScope(), Integer.toString(x.args.size()), Quadruple.Operations.CALL));
        ie.setPlace(new Temporal("RET"));
        return ie;
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
        if (x instanceof Expression) {
            return ((Expression) x).accept(this);
        } else if (x instanceof AssignmentStatement) {
            return ((AssignmentStatement) x).accept(this);
        } else if (x instanceof IOStatement) {
            return ((IOStatement) x).accept(this);
        } else if (x instanceof IfStatement) {
            return ((IfStatement) x).accept(this);
        } else if (x instanceof WhileStatement) {
            return ((WhileStatement) x).accept(this);
        } else if (x instanceof ExitStatement) {
            return ((ExitStatement) x).accept(this);
        } else if (x instanceof LoopStatement) {
            return ((LoopStatement) x).accept(this);
        } else if (x instanceof ForStatement) {
            return ((ForStatement) x).accept(this);
        } else if (x instanceof ReturnStatement) {
            return ((ReturnStatement) x).accept(this);
        } else {
            return new IntermediateStatement();
        }
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
        ie.setPlace(new Temporal("\"" + x.str + "\""));
        if (!stringsTable.contains(x.str)) {
            stringsTable.add(x.str);
        }
        return ie;
    }

    @Override
    public IntermediateForm traverse(Statements x) {
        IntermediateStatement ie = new IntermediateStatement();
        for (int i = 0; i < x.size(); i++) {
            IntermediateStatement sta = (IntermediateStatement) x.elementAt(i).accept(this);
            Label next = new Label();
            ie.operations = ie.operations.merge(sta.operations);
            ie.operations.add(new Quadruple(next));
            complete(sta.getNext(), next);
            if (x.elementAt(i) instanceof WhileStatement || x.elementAt(i) instanceof ForStatement || x.elementAt(i) instanceof LoopStatement) {
               complete(sta.getOuterList(), next);
            }else{
            ie.setOuterList(merge(ie.getOuterList(),sta.getOuterList()));
            }

        }
        return ie;
    }

}
