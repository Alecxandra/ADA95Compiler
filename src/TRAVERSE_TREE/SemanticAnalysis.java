/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TRAVERSE_TREE;

import AST_TREE.*;
import ada95compiler.FTableNode;
import ada95compiler.SymbolTable;


/**
 *
 * @author alecx
 */
public class SemanticAnalysis implements TypeTraverse{
   private SymbolTable symboltable;
   private boolean has_error;

    public SemanticAnalysis(SymbolTable symboltable) {
        this.symboltable = symboltable;
        has_error=false;
    }
    
    public void print_error(String message, int line, int column){
        System.err.println("ERROR: "+message+"en la linea:"+ line+", columna: "+column);
        has_error=true;
    }
    
    public boolean haserror(){
    return has_error;
    }
 
    
    @Override
    public Type traverse(Type x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(IntegerType x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(BooleanType x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(FloatType x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(TrueType x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(FalseType x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(FloatLiteral x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(LogicalExpression x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(LiteralExpression x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(Identifier x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(Expression x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(ArithmeticExpression x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(Add x) {
     Type type1= x.exp1.accept(this);
     Type type2= x.exp2.accept(this);
     
     if(type1 instanceof BooleanType){
      print_error("no se permite sumar expresiones de tipo boolean",0,0);
      return new ErrorType();
     }
     
     if(type1 instanceof StringType){
      print_error("no se permite sumar expresiones de string",0,0);
      return new ErrorType();
     }
     
     if(!(type1.equals(type2))){
      print_error("no se permite sumar expresiones de tipo"+type1.getClass().getSimpleName().replaceAll("Type","")+" y "+type2.getClass().getSimpleName().replaceAll("Type",""),0,0);
      return new ErrorType();
     }
     return type1;
    }

    @Override
    public Type traverse(Min x) {
     Type type1= x.exp1.accept(this);
     Type type2= x.exp2.accept(this);
     
     if(type1 instanceof BooleanType){
       print_error("no se permite restar expresiones de tipo boolean",0,0);
       return new ErrorType();
     }
     
     if(type1 instanceof StringType){
      print_error("no se permite restar expresiones de string",0,0);
      return new ErrorType();
     }
     
     if(!(type1.equals(type2))){
      print_error("no se permite restar expresiones de tipo"+type1.getClass().getSimpleName().replaceAll("Type","")+" y "+type2.getClass().getSimpleName().replaceAll("Type",""),0,0);
      return new ErrorType();
     }
     return type1;
    }
    

    @Override
    public Type traverse(Mul x) {
     Type type1= x.exp1.accept(this);
     Type type2= x.exp2.accept(this);
     
     if(type1 instanceof BooleanType){
      print_error("no se permite multiplicar expresiones de tipo boolean",0,0);
      return new ErrorType();
     }
     
     if(type1 instanceof StringType){
      print_error("no se permite multiplicar expresiones de string",0,0);
      return new ErrorType();
     }
     
     if(!(type1.equals(type2))){
      print_error("no se permite multiplicar expresiones de tipo"+type1.getClass().getSimpleName().replaceAll("Type","")+" y "+type2.getClass().getSimpleName().replaceAll("Type",""),0,0);
      return new ErrorType();
     }
     return type1;
    }

    @Override
    public Type traverse(Div x) {
       Type type1= x.exp1.accept(this);
       Type type2= x.exp2.accept(this);
     
     if(type1 instanceof BooleanType){
      print_error("no se permite dividir expresiones de tipo boolean",0,0);
      return new ErrorType();
     }
     
     if(type1 instanceof StringType){
      print_error("no se permite dividir expresiones de string",0,0);
      return new ErrorType();
     }
     
     if(!(type1.equals(type2))){
      print_error("no se permite dividir expresiones de tipo"+type1.getClass().getSimpleName().replaceAll("Type","")+" y "+type2.getClass().getSimpleName().replaceAll("Type",""),0,0);
      return new ErrorType();
     }
     return type1;
    }

    @Override
    public Type traverse(Power x) {
       Type type1= x.exp1.accept(this);
       Type type2= x.exp1.accept(this);
       
       if(type1 instanceof BooleanType || type2 instanceof BooleanType){
        print_error("no se puede realizar la operacion potencia con expresiones booleanas",0,0);
        return new ErrorType();
       }
       if(type1 instanceof StringType || type2 instanceof StringType){
        print_error("no se puede realizar la operacion potencia con strings",0,0);
        return new ErrorType();
       }
       
       if(type1 instanceof FloatType || type2 instanceof FloatType){
        return new FloatType();
       }
       return new IntegerType();
    }

    @Override
    public Type traverse(Umin x) {
     Type type= x.exp.accept(this);
     
      if(type instanceof StringType){
       print_error("operando no puede ser String",0,0);
       return new ErrorType();
      }
      
      if(type instanceof BooleanType){
       print_error("operando no puede ser boolean",0,0);
       return new ErrorType();
      }
      return type;
    }

    @Override
    public Type traverse(BooleanExpression x) {
        if(x instanceof LessEqual){
          return ((LessEqual)x).accept(this);
        }else if(x instanceof GreaterEqual){
          return ((GreaterEqual)x).accept(this);
        }else if(x instanceof Distinct){
          return ((Distinct)x).accept(this);
        }else if(x instanceof Greater){
          return ((Greater)x).accept(this);
        }else if(x instanceof Less){
         return ((Less)x).accept(this);
        }else if(x instanceof Equal){
         return ((Equal)x).accept(this);
        }else{
          return new ErrorType();  
        }
       
    }

    @Override
    public Type traverse(LessEqual x) {
        Type type1 = x.exp1.accept(this);
        Type type2= x.exp2.accept(this);
        
        if(type1 instanceof StringType || type2 instanceof StringType){
          print_error("no se pueden comparar strings",0,0);
          return new ErrorType();
        }
        if(type1 instanceof BooleanType || type2 instanceof BooleanType){
          print_error("no se pueden comparar booleans",0,0);
          return new ErrorType();
        }
        if(!type1.equals(type2)){
          print_error("no se puede comparar "+type1.getClass().getSimpleName().replaceAll("Type","")+" <= "+type2.getClass().getSimpleName().replaceAll("Type","")+", tipos incompatibles",0,0);
          return new ErrorType();
        }
        
        return new BooleanType(); 
    }

    @Override
    public Type traverse(GreaterEqual x) {
        Type type1 = x.exp1.accept(this);
        Type type2= x.exp2.accept(this);
        
        if(type1 instanceof StringType || type2 instanceof StringType){
          print_error("no se pueden comparar strings",0,0);
          return new ErrorType();
        }
        if(type1 instanceof BooleanType || type2 instanceof BooleanType){
          print_error("no se pueden comparar booleans",0,0);
          return new ErrorType();
        }
        if(!type1.equals(type2)){
          print_error("no se puede comparar "+type1.getClass().getSimpleName().replaceAll("Type","")+" >= "+type2.getClass().getSimpleName().replaceAll("Type","")+", tipos incompatibles",0,0);
          return new ErrorType();
        }
        
        return new BooleanType(); 
    }

    @Override
    public Type traverse(Distinct x) {
        Type type1 = x.exp1.accept(this);
        Type type2= x.exp2.accept(this);
      if(type1 instanceof StringType || type2 instanceof StringType){
          print_error("no se pueden comparar strings",0,0);
          return new ErrorType();
        }
      
     if(!type1.equals(type2)){
          print_error("no se puede comparar "+type1.getClass().getSimpleName().replaceAll("Type","")+" /= "+type2.getClass().getSimpleName().replaceAll("Type","")+", tipos incompatibles",0,0);
          return new ErrorType();
        }
        
        return new BooleanType();   
    }

    @Override
    public Type traverse(Greater x) {
        Type type1 = x.exp1.accept(this);
        Type type2= x.exp2.accept(this);
        
        if(type1 instanceof StringType || type2 instanceof StringType){
          print_error("no se pueden comparar strings",0,0);
          return new ErrorType();
        }
        if(type1 instanceof BooleanType || type2 instanceof BooleanType){
          print_error("no se pueden comparar booleans",0,0);
          return new ErrorType();
        }
        if(!type1.equals(type2)){
          print_error("no se puede comparar "+type1.getClass().getSimpleName().replaceAll("Type","")+" > "+type2.getClass().getSimpleName().replaceAll("Type","")+", tipos incompatibles",0,0);
          return new ErrorType();
        }
        
        return new BooleanType();
    }

    @Override
    public Type traverse(Less x) {
         Type type1 = x.exp1.accept(this);
        Type type2= x.exp2.accept(this);
        
        if(type1 instanceof StringType || type2 instanceof StringType){
          print_error("no se pueden comparar strings",0,0);
          return new ErrorType();
        }
        if(type1 instanceof BooleanType || type2 instanceof BooleanType){
          print_error("no se pueden comparar booleans",0,0);
          return new ErrorType();
        }
        if(!type1.equals(type2)){
          print_error("no se puede comparar "+type1.getClass().getSimpleName().replaceAll("Type","")+" < "+type2.getClass().getSimpleName().replaceAll("Type","")+", tipos incompatibles",0,0);
          return new ErrorType();
        }
        
        return new BooleanType();
    }

    @Override
    public Type traverse(Equal x) {
         Type type1 = x.exp1.accept(this);
        Type type2= x.exp2.accept(this);
        
        if(type1 instanceof StringType || type2 instanceof StringType){
          print_error("no se pueden comparar strings",0,0);
          return new ErrorType();
        }
        if(!type1.equals(type2)){
          print_error("no se puede comparar "+type1.getClass().getSimpleName().replaceAll("Type","")+" = "+type2.getClass().getSimpleName().replaceAll("Type","")+", tipos incompatibles",0,0);
          return new ErrorType();
        }
        
        return new BooleanType();
    }

    @Override
    public Type traverse(And x) {
     Type type1 = x.exp1.accept(this);
     Type type2 = x.exp2.accept(this);
     
     if(type1 instanceof BooleanType && type2 instanceof BooleanType){
      return new BooleanType();
     }else{
      print_error("no se puede comparar "+type1.getClass().getSimpleName().replaceAll("Type", "")+" and "+type2.getClass().getSimpleName().replaceAll("Type", "")+", tipos incompatibles",0,0);
      return new ErrorType();
     }
    }

    @Override
    public Type traverse(Or x) {
     Type type1 = x.exp1.accept(this);
     Type type2 = x.exp2.accept(this);
     
     if(type1 instanceof BooleanType && type2 instanceof BooleanType){
      return new BooleanType();
     }else{
      print_error("no se puede comparar "+type1.getClass().getSimpleName().replaceAll("Type", "")+" or "+type2.getClass().getSimpleName().replaceAll("Type", "")+", tipos incompatibles",0,0);
      return new ErrorType();
     }
     
    }

    @Override
    public Type traverse(Not x) {
     Type type1 = x.exp.accept(this);
     
     if(type1 instanceof BooleanType){
      return new BooleanType();
     }else{
      print_error("no se puede negar el tipo "+type1.getClass().getSimpleName().replaceAll("Type", ""),0,0);
      return new ErrorType();
     }
    }

    @Override
    public Type traverse(ArgumentList x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(VariableList x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(DeclareStatement x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(AssignmentStatement x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(IOStatement x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(Get x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(ReturnStatement x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(IfStatement x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(ElseIfStatement x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(ElsifStatements x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(WhileStatement x) {
        Type type = x.expre.accept(this);
      if(!(type instanceof BooleanType)){
         print_error("La condicion del while no es una expresion boleana",0,0);
      }
        for (int i = 0; i < x.sta.size(); i++) {
            x.sta.elementAt(i).accept(this);
        }
      return new NullType(); 
    }

    @Override
    public Type traverse(ExitStatement x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(LoopStatement x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(ForStatement x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(In x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(Out x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(InOut x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(ParamsModifier x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(ParamsList x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(ProcedureStatement x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(FunctionStatement x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(DeclarationStatement x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(Declarations x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(ProgramInit x) {
        if(!x.preid.equals(x.postid)){
            print_error("Los identificadores del procedimiento no coinciden",0,0);
        }
        
        FTableNode node = new FTableNode(new NullType(),x.preid.id,""); 
        symboltable.addSymbol(node);
        
        Declarations declarations = x.declarations;
        for (int i = 0; i < declarations.size(); i++) {
            declarations.elementAt(i).accept(this);
        }
        
        Statements sta = x.stas;
        
        for (int i = 0; i < sta.size(); i++) {
            sta.elementAt(i).accept(this);
        }
     return new NullType();   
    }

    @Override
    public Type traverse(WhileStatementError x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(ExitStatementError x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(LoopStatementError x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(ForStatementError x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(ProcedureStatementError x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(DeclareStatementError x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(AssignmentStatementError x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(FunctionCallError x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(IfStatementError x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(ElseIfStatementError x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(FunctionStatementError x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(ReturnStatementError x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(PutError x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(GetError x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(ProgramInitError x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(StringType x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(NullType x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
