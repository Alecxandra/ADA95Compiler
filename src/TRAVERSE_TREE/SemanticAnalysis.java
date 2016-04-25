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
        return new BooleanType();
    }

    @Override
    public Type traverse(FalseType x) {
      return new BooleanType();  
    }

    @Override
    public Type traverse(FloatLiteral x) {
        return new FloatType();
    }

    @Override
    public Type traverse(LogicalExpression x) {
        if(x instanceof And){
         return ((And)x).accept(this);
        }else if(x instanceof Or){
         return ((Or)x).accept(this);
        }else if(x instanceof Not){
         return ((Not)x).accept(this);
        }else if(x instanceof TrueType){
         return ((TrueType)x).accept(this);
        }else if(x instanceof FalseType){
        return ((FalseType)x).accept(this);
        }else{
        return new ErrorType();
        }
    }

    @Override
    public Type traverse(LiteralExpression x) {
        if(x instanceof FloatLiteral){
          return ((FloatLiteral)x).accept(this);
        }else if(x instanceof IntegerLiteral){
         return ((IntegerLiteral)x).accept(this);
        }else if(x instanceof StringLiteral){ 
          return ((IntegerLiteral)x).accept(this);
        }else{
         return new ErrorType();
        }
    }

    @Override
    public Type traverse(Identifier x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(Expression x) {
      if(x instanceof Identifier){
        return ((Identifier)x).accept(this);  
      }else if(x instanceof LiteralExpression){
       return ((LiteralExpression)x).accept(this);
      }else if(x instanceof ArithmeticExpression){
       return ((ArithmeticExpression)x).accept(this);
      }else if(x instanceof Expression){
       return ((Expression)x).accept(this);
      }else if(x instanceof BooleanExpression){
       return ((BooleanExpression)x).accept(this);
      }else if(x instanceof LogicalExpression){
       return ((LogicalExpression)x).accept(this);
      }else if(x instanceof FunctionCall){
       return ((FunctionCall)x).accept(this);   
      }else{
       return new ErrorType();
      }  
    }

    @Override
    public Type traverse(ArithmeticExpression x) {
        if(x instanceof Add){
        return ((Add)x).accept(this);  
      }else if(x instanceof Min){
       return ((Min)x).accept(this);
      }else if(x instanceof Mul){
       return ((Mul)x).accept(this);
      }else if(x instanceof Div){
       return ((Div)x).accept(this);
      }else if(x instanceof Power){
       return ((Power)x).accept(this);
      }else if(x instanceof Umin){
       return ((Umin)x).accept(this);   
      }else{
       return new ErrorType();
      }
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
    public Type traverse(Not x)  {
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
        Type expre = (x.expre).accept(this);
        if(!(expre instanceof BooleanType)){
            print_error("La expresión de la declaración IF no es correcta.",0,0);
        }
        
        Statements sta1 = x.sta1;
        Statements sta2 = x.sta2;
        ElsifStatements esta = x.esta;
          
        if(sta1 != null){
            for (int i = 0; i < sta1.size(); i++) {
                sta1.elementAt(i).accept(this);
            }
        }
        if(esta != null){
            for (int i = 0; i < esta.size(); i++) {
                esta.elementAt(i).accept(this);
            }
        }
        if(sta2 != null){
            for (int i = 0; i < sta2.size(); i++) {
                sta2.elementAt(i).accept(this);
            }
        }

        return new NullType();  
    }

    @Override
    public Type traverse(ElseIfStatement x) {
        Type expre = (x.expre).accept(this);
        if(!(expre instanceof BooleanType)){
            print_error("La expresión de la declaración ELSE-IF no es correcta.",0,0);
        }
        
        Statements sta1 = x.statements;
        
        if(sta1 != null){
            for (int i = 0; i < sta1.size(); i++) {
                sta1.elementAt(i).accept(this);
            }
        }
        
        return new NullType();
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
        Type type1 = x.end.accept(this);
        Type type2 = x.start.accept(this);
        if(!(type1 instanceof IntegerType && type2 instanceof IntegerType)){
            print_error("El ",0,0);
        } 
        
        Statements sta1 = x.sta;
        
        if(sta1 != null){
            for (int i = 0; i < sta1.size(); i++) {
                sta1.elementAt(i).accept(this);
            }
        }
        
        return new NullType();
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

    @Override
    public Type traverse(Statement x) {
      if(x instanceof Expression){
        return ((Expression)x).accept(this);  
      }else if(x instanceof AssignmentStatement){
       return ((AssignmentStatement)x).accept(this);
      }else if(x instanceof IOStatement){
       return ((IOStatement)x).accept(this);
      }else if(x instanceof IfStatement){
       return ((IfStatement)x).accept(this);
      }else if(x instanceof WhileStatement){
       return ((WhileStatement)x).accept(this);
      }else if(x instanceof ExitStatement){
       return ((ExitStatement)x).accept(this);
      }else if(x instanceof LoopStatement){
       return ((LoopStatement)x).accept(this);
      }else if(x instanceof ForStatement){
       return ((ForStatement)x).accept(this);
      }else if(x instanceof ReturnStatement){
       return ((ReturnStatement)x).accept(this);   
      }else{
       return new ErrorType();
      }
    }
    
}
