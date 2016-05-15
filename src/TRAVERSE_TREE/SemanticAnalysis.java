/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TRAVERSE_TREE;

import AST_TREE.*;
import ada95compiler.FTableNode;
import ada95compiler.Scope;
import ada95compiler.SymbolTable;
import ada95compiler.SymbolTableNode;
import ada95compiler.VTableNode;
import java.util.ArrayList;


/**
 *
 * @author alecx
 */
public class SemanticAnalysis implements TypeTraverse{
   private SymbolTable symboltable;
   private boolean has_error;
   private String scope;
   private String current_id;
    public SemanticAnalysis(SymbolTable symboltable) {
        this.symboltable = symboltable;
        has_error=false;
        scope="";
    }
    
    public void print_error(String message, int line, int column){
        System.err.println("ERROR: "+message+"en la linea:"+ line+", columna: "+column);
        has_error=true;
    }
    
    public boolean haserror(){
    return has_error;
    }
 
    
    @Override
    public Type traverse(Type x) { /* Listo*/
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(IntegerType x) { /* Listo*/
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(BooleanType x) { /* Listo*/
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(FloatType x) { /* Listo*/
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
          return ((StringLiteral)x).accept(this);
        }else{
         return new ErrorType();
        }
    }

    @Override
    public Type traverse(Identifier x) {
        SymbolTableNode node = this.symboltable.findSymbol(x.id,this.scope);
        if(node == null){
           print_error("El identificador "+x.id+" no ha sido declarado",0,0);
           return new ErrorType();
        }else{
         if(node instanceof VTableNode){
             VTableNode param = (VTableNode)node;
             if (param.getForm() == VTableNode.OUT) {
                 print_error("La variable "+param.getId()+" es de tipo OUT y no puede usarse como R-Value",0,0);
                 return new ErrorType();
             }
           return ((VTableNode)node).getType();
         }else{
          print_error("El identificador "+x.id+" no es una variable ",0,0);
          return new ErrorType();
         }
        }
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
      print_error("no se permite sumar expresiones de tipo "+type1.getClass().getSimpleName().replaceAll("Type","")+" y "+type2.getClass().getSimpleName().replaceAll("Type",""),0,0);
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
      print_error("no se permite restar expresiones de tipo "+type1.getClass().getSimpleName().replaceAll("Type","")+" y "+type2.getClass().getSimpleName().replaceAll("Type",""),0,0);
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
      print_error("no se permite multiplicar expresiones de tipo "+type1.getClass().getSimpleName().replaceAll("Type","")+" y "+type2.getClass().getSimpleName().replaceAll("Type",""),0,0);
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
      print_error("no se permite dividir expresiones de tipo "+type1.getClass().getSimpleName().replaceAll("Type","")+" y "+type2.getClass().getSimpleName().replaceAll("Type",""),0,0);
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
    public Type traverse(ArgumentList x) {/* listo */
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(VariableList x) {/* listo */
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(DeclareStatement x) {
        for (int i = 0; i < x.list.size(); i++) {
            VTableNode node = new VTableNode(x.type,0,0,x.list.elementAt(i).id,new String(this.scope));
            if(!this.symboltable.addSymbol(node)){
               print_error("El identificador "+x.list.elementAt(i).id+" ya esta declarado en este ámbito",0,0);          
            }     
        }
       return new NullType();
    }

    @Override
    public Type traverse(AssignmentStatement x) {
        SymbolTableNode node = this.symboltable.findSymbol(x.id.id, this.scope);
        Type type = x.expre.accept(this);
        if (node == null) {
          print_error("la variable "+x.id.id+" no ha sido declarada",0,0);
          return new ErrorType();
        }
        
        if(!(node instanceof VTableNode)){
            print_error("El identificador "+x.id.id+" no es una variable",0,0);
            return new ErrorType();
        }else{
            VTableNode param =(VTableNode)node; 
            if (param.getForm() == VTableNode.IN) {
                print_error("La variable "+param.getId()+" es de tipo IN, su valor no puede ser modificado ",0,0);
                return new ErrorType();
            }
            
         if (!(((VTableNode)node).getType().equals(type))) {
            print_error("no se puede asignar una expresion de tipo " + type.toString() +"a una variable de tipo "+((VTableNode)node).getType().toString(),0,0);
            return new ErrorType();
        }
        }
        
        
        return ((VTableNode)node).getType();
    }

    @Override
    public Type traverse(IOStatement x) {
        if (x instanceof Get) {
            return ((Get)x).accept(this);
        }else {
            return ((Put)x).accept(this);
        }
    }

    @Override
    public Type traverse(Get x) {
        Type type = x.id.accept(this);
        if (type instanceof ErrorType) {
            print_error(" en evaluacion de la expresion de get",0,0);
            return new ErrorType(); 
        }
       return new NullType(); 
    }

    @Override
    public Type traverse(ReturnStatement x) {
        System.out.println(" scope actual "+this.scope);
        String new_scope="";
        for (int i = this.scope.length()-1; i >=0 ; i--) {
             System.out.println("i: "+i+" letra "+ this.scope.charAt(i));
            if(this.scope.charAt(i)== 's'){
              new_scope= this.scope.substring(0,i);
                System.out.println(" nuevo scope "+new_scope);
              break;
            }
        }
        FTableNode node = this.symboltable.getFunction(new_scope,this.current_id);
        if (node == null) {
            System.out.println("error en el nodo de FTableNode");   
        }else if(node.getReturn_type().equals(new NullType())){
         print_error("Expresion ilegal, se encontro un return en un procedimiento",0,0);
        }else if(!(node.getReturn_type().equals(x.expre))){
         print_error("La expresion del return no es del mismo tipo que la funcion, se esperaba un "+node.getReturn_type(),0,0);
        }
        return new NullType();
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
    public Type traverse(ElsifStatements x) {/* listo */
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
        Type type = x.accept(this);
        if(!(type instanceof BooleanType)){
         print_error("La condicion del Exit when no es una expresion booleana",0,0);
        }
        return new NullType();
    }

    @Override
    public Type traverse(LoopStatement x) {
        for (int i = 0; i < x.sta.size(); i++) {
            x.sta.elementAt(i).accept(this);
        }
        return new NullType();
    }

    @Override
    public Type traverse(ForStatement x) {
        Type type1 = x.end.accept(this);
        Type type2 = x.start.accept(this);
        SymbolTableNode node = this.symboltable.findSymbol(x.id.id, this.scope);
        if(node == null ){
          print_error("La variable "+ node.getId()+" en el for, no ha sido declarada",0,0);
        }
        VTableNode param = (VTableNode)node;
        if (param.getForm()== VTableNode.IN) {
            print_error("el contador "+ param.getId()+" es de tipo IN, su valor no puede ser modificado",0,0);
        }
        
        if(!(param.getType().equals(new IntegerType()))){
          print_error("la variable "+param.getId()+" debe ser de tipo Integer",0,0);
          return new ErrorType();
        }
        
        if(!(type1 instanceof IntegerType && type2 instanceof IntegerType)){
            print_error("las expresiones del rango del for deben ser Integer",0,0);
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
    public Type traverse(In x) {/*listo*/
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(Out x) {/*listo*/
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(InOut x) {/*listo*/
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(ParamsModifier x) {/*listo*/
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(ParamsList x) {/*listo*/
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(ProcedureStatement x) {
       if (!x.preid.id.equals(x.postid.id)) {
            print_error("Los identificadores del procedimiento no coinciden",0,0);
        }
        String temp_scope=new String(this.scope);
        String current_scope= new String(this.scope+Scope.getNewScope());
        this.scope= new String(current_scope);
        FTableNode node = new FTableNode(new NullType(), x.preid.id,temp_scope);
        for (int i = 0; i <x.list.size(); i++) {
            if (x.list.elementAt(i) instanceof In) {
                
                In param= (In)x.list.elementAt(i);
                  for (int j = 0; j < param.list.size(); j++) {
                      VTableNode paramnode = new VTableNode(param.type,1,0,param.list.elementAt(j).id,current_scope);
                      
                      if(!this.symboltable.addSymbol(paramnode)){
                        print_error("El identificador "+paramnode.getId()+" ya esta declarado en este ámbito",0,0);          
                       }else{
                       node.Add(paramnode);
                      }  
                  }
                  
            }else if(x.list.elementAt(i) instanceof Out){
                
                 Out param= (Out)x.list.elementAt(i);
                 for (int j = 0; j < param.list.size(); j++) {
                     VTableNode paramnode= new VTableNode(param.type,2,0,param.list.elementAt(j).id,current_scope);
                     
                     
                     if(!this.symboltable.addSymbol(paramnode)){
                        print_error("El identificador "+paramnode.getId()+" ya esta declarado en este ámbito",0,0);          
                      }else{
                       node.Add(paramnode);   
                     } 
                  }
            }else if(x.list.elementAt(i) instanceof InOut){
               
                InOut param= (InOut)x.list.elementAt(i);
                for (int j = 0; j < param.list.size(); j++) {
                      VTableNode paramnode=new VTableNode(param.type,3,0,param.list.elementAt(j).id,current_scope); 
                     
                     if(!this.symboltable.addSymbol(paramnode)){
                        print_error("El identificador "+paramnode.getId()+" ya esta declarado en este ámbito",0,0);          
                     }else{
                       node.Add(paramnode);
                     } 
                  }
            }
        }
        
        if(!this.symboltable.addSymbol(node)){
               print_error("El identificador "+x.preid+" ya esta declarado en este ámbito",0,0);          
            }
       
        this.current_id= x.preid.id;
        
        
        for (int i = 0; i < x.presta.size(); i++) {
            x.presta.elementAt(i).accept(this);
        }
        
        for (int i = 0; i < x.poststa.size(); i++) {
            x.poststa.elementAt(i).accept(this);
        }
        
        this.scope= new String(temp_scope);
        return new NullType();
    }

    @Override
    public Type traverse(FunctionStatement x) {
        if (!x.preid.id.equals(x.postid.id)) {
            print_error("Los identificadores de la función no coinciden",0,0);
        }
        String temp_scope=new String(this.scope);
        String current_scope= new String(this.scope+Scope.getNewScope());
        this.scope= new String(current_scope);
        FTableNode node = new FTableNode(x.type, x.preid.id,temp_scope);
        for (int i = 0; i < x.params.size(); i++) {
            if (x.params.elementAt(i) instanceof In) {
                
                In param= (In)x.params.elementAt(i);
                  for (int j = 0; j < param.list.size(); j++) {
                      VTableNode paramnode = new VTableNode(param.type,1,0,param.list.elementAt(j).id,current_scope);
                      
                      if(!this.symboltable.addSymbol(paramnode)){
                        print_error("El identificador "+paramnode.getId()+" ya esta declarado en este ámbito",0,0);          
                       }else{
                        node.Add(paramnode);
                      }  
                  }
                  
            }else if(x.params.elementAt(i) instanceof Out){
                
                 Out param= (Out)x.params.elementAt(i);
                 for (int j = 0; j < param.list.size(); j++) {
                     VTableNode paramnode= new VTableNode(param.type,2,0,param.list.elementAt(j).id,current_scope);
                     
                     
                     if(!this.symboltable.addSymbol(paramnode)){
                        print_error("El identificador "+paramnode.getId()+" ya esta declarado en este ámbito",0,0);          
                      }else{
                       node.Add(paramnode);   
                     } 
                  }
            }else if(x.params.elementAt(i) instanceof InOut){
               
                InOut param= (InOut)x.params.elementAt(i);
                for (int j = 0; j < param.list.size(); j++) {
                      VTableNode paramnode=new VTableNode(param.type,3,0,param.list.elementAt(j).id,current_scope); 
                     
                     if(!this.symboltable.addSymbol(paramnode)){
                        print_error("El identificador "+paramnode.getId()+" ya esta declarado en este ámbito",0,0);          
                     }else{
                       node.Add(paramnode);
                     } 
                  }
            }
        }
        
        if(!this.symboltable.addSymbol(node)){
               print_error("El identificador "+x.preid+" ya esta declarado en este ámbito",0,0);          
            }
        
        this.current_id= x.preid.id;
        
        for (int i = 0; i < x.presta.size(); i++) {
            x.presta.elementAt(i).accept(this);
        }
        
        for (int i = 0; i < x.poststa.size(); i++) {
            x.poststa.elementAt(i).accept(this);
        }
        this.scope= new String(temp_scope);
        return new NullType();
    }

    @Override
    public Type traverse(DeclarationStatement x) {
        if (x instanceof DeclareStatement) {
            return ((DeclareStatement)x).accept(this);
        }else if(x instanceof ProcedureStatement){
            return ((ProcedureStatement)x).accept(this);
        }else if(x instanceof FunctionStatement){
            return ((FunctionStatement)x).accept(this);
        }else{
        return new ErrorType();
        }
    }

    @Override
    public Type traverse(Declarations x) {/*listo*/
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(ProgramInit x) {
        if(!x.preid.equals(x.postid)){
            print_error("Los identificadores del procedimiento no coinciden",0,0);
        }
        String current_scope= Scope.getNewScope();
        this.scope = new String(current_scope);
        FTableNode node = new FTableNode(new NullType(),x.preid.id,current_scope); 
        symboltable.addSymbol(node);
        
        Declarations declarations = x.declarations;
        for (int i = 0; i < declarations.size(); i++) {
            declarations.elementAt(i).accept(this);
        }
        this.current_id= x.preid.id;
        Statements sta = x.stas;
        
        for (int i = 0; i < sta.size(); i++) {
            sta.elementAt(i).accept(this);
        }
     return new NullType();   
    }

    @Override
    public Type traverse(WhileStatementError x) {
        Type type = x.expre.accept(this);
        if (!(type instanceof BooleanType)) {
          print_error("la expresion del while no es booleana",0,0);  
        }
        for (int i = 0; i < x.sta.size(); i++) {
            x.sta.elementAt(i).accept(this);
        }
        return new NullType();
    }

    @Override
    public Type traverse(ExitStatementError x) {
        Type type = x.expre.accept(this);
        if (!(type instanceof BooleanType)) {
            print_error("La expresion del Exit When no es booleana",0,0);
        }
        return new ErrorType();
    }

    @Override
    public Type traverse(LoopStatementError x) {
        for (int i = 0; i < x.sta.size(); i++) {
          x.sta.elementAt(i).accept(this);
        }
        return new ErrorType();
    }

    @Override
    public Type traverse(ForStatementError x) {
        Type type1 = x.end.accept(this);
        Type type2 = x.start.accept(this);
        SymbolTableNode node = this.symboltable.findSymbol(x.id.id, this.scope);
        if(node == null ){
          print_error("La variable "+ node.getId()+" en el for, no ha sido declarada",0,0);
        }
        VTableNode param = (VTableNode)node;
        if (param.getForm()== VTableNode.IN) {
            print_error("el contador "+ param.getId()+" es de tipo IN, su valor no puede ser modificado",0,0);
        }
        
        if(!(param.getType().equals(new IntegerType()))){
          print_error("la variable "+param.getId()+" debe ser de tipo Integer",0,0);
          return new ErrorType();
        }
        
        if(!(type1 instanceof IntegerType && type2 instanceof IntegerType)){
            print_error("las expresiones del rango del for deben ser Integer",0,0);
        } 
       
        Statements sta1 = x.sta;
        
        if(sta1 != null){
            for (int i = 0; i < sta1.size(); i++) {
                sta1.elementAt(i).accept(this);
            }
        }
        
        return new ErrorType();
    }

    @Override
    public Type traverse(ProcedureStatementError x) {
        
        if (!x.preid.id.equals(x.postid.id)) {
            print_error("Los identificadores del procedimiento no coinciden",0,0);
        }
        String temp_scope=new String(this.scope);
        String current_scope= new String(this.scope+Scope.getNewScope());
        this.scope= new String(current_scope);
        FTableNode node = new FTableNode(new NullType(), x.preid.id,temp_scope);
        for (int i = 0; i <x.list.size(); i++) {
            if (x.list.elementAt(i) instanceof In) {
                
                In param= (In)x.list.elementAt(i);
                  for (int j = 0; j < param.list.size(); j++) {
                      VTableNode paramnode = new VTableNode(param.type,1,0,param.list.elementAt(j).id,current_scope);
                      
                      if(!this.symboltable.addSymbol(paramnode)){
                        print_error("El identificador "+paramnode.getId()+" ya esta declarado en este ámbito",0,0);          
                       }else{
                       node.Add(paramnode);
                      }  
                  }
                  
            }else if(x.list.elementAt(i) instanceof Out){
                
                 Out param= (Out)x.list.elementAt(i);
                 for (int j = 0; j < param.list.size(); j++) {
                     VTableNode paramnode= new VTableNode(param.type,2,0,param.list.elementAt(j).id,current_scope);
                     
                     
                     if(!this.symboltable.addSymbol(paramnode)){
                        print_error("El identificador "+paramnode.getId()+" ya esta declarado en este ámbito",0,0);          
                      }else{
                       node.Add(paramnode);   
                     } 
                  }
            }else if(x.list.elementAt(i) instanceof InOut){
               
                InOut param= (InOut)x.list.elementAt(i);
                for (int j = 0; j < param.list.size(); j++) {
                      VTableNode paramnode=new VTableNode(param.type,3,0,param.list.elementAt(j).id,current_scope); 
                     
                     if(!this.symboltable.addSymbol(paramnode)){
                        print_error("El identificador "+paramnode.getId()+" ya esta declarado en este ámbito",0,0);          
                     }else{
                       node.Add(paramnode);
                     } 
                  }
            }
        }
        this.scope= new String(temp_scope);
        if(!this.symboltable.addSymbol(node)){
               print_error("El identificador "+x.preid+" ya esta declarado en este ámbito",0,0);          
            }
        
        this.current_id= x.preid.id;
        
        for (int i = 0; i < x.presta.size(); i++) {
            x.presta.elementAt(i).accept(this);
        }
        
        for (int i = 0; i < x.poststa.size(); i++) {
            x.poststa.elementAt(i).accept(this);
        }
        return new ErrorType();
    }

    @Override
    public Type traverse(DeclareStatementError x) {
        for (int i = 0; i < x.list.size(); i++) {
            VTableNode node = new VTableNode(x.type,0,0,x.list.elementAt(i).id,new String(this.scope));
            if(!this.symboltable.addSymbol(node)){
               print_error("El identificador "+x.list.elementAt(i).id+" ya esta declarado en este ámbito",0,0);          
            }     
        }
       return new ErrorType();
    }

    @Override
    public Type traverse(AssignmentStatementError x) {
        SymbolTableNode node = this.symboltable.findSymbol(x.id.id, this.scope);
        Type type = x.expre.accept(this);
        if (node == null) {
          print_error("la variable "+x.id.id+" no ha sido declarada",0,0);
          return new ErrorType();
        }
        
        if(!(node instanceof VTableNode)){
            print_error("El identificador "+x.id.id+" no es una variable",0,0);
            return new ErrorType();
        }else{
            VTableNode param =(VTableNode)node; 
            if (param.getForm() == VTableNode.IN) {
                print_error("La variable "+param.getId()+" es de tipo IN, su valor no puede ser modificado ",0,0);
                return new ErrorType();
            }
            
         if (!(((VTableNode)node).getType().equals(type))) {
            print_error("no se puede asignar una expresion de tipo " + type.toString() +"a una variable de tipo "+((VTableNode)node).getType().toString(),0,0);
            return new ErrorType();
        }
        }
        
        
        return new ErrorType();
    }

    @Override
    public Type traverse(FunctionCallError x) {
        FTableNode node = (FTableNode)this.symboltable.findSymbol(x.id.id,this.scope);
        if(node == null){
          print_error("la funcion "+x.id.id+" no se ha declarado",0,0);
          return new ErrorType();
        }else{
           ArrayList<Type> arguments = new ArrayList(); 
            for (int i = 0; i < x.args.size(); i++) {
               Type arg=x.args.elementAt(i).accept(this);
               arguments.add(arg);
            }
            if(node.getParams().size() != arguments.size()){
             print_error("nunero erroneo de argumentos se esperaba "+ node.getParams().toString(),0,0);
             return new ErrorType();
            }
            
            for (int i = 0; i < node.getParams().size(); i++) {
                if(!node.getParams().get(i).equals(i)){
                  print_error("Tipos de argumentos invalidos se esperaba: "+node.getParams().toString(),0,0);
                  return new ErrorType();
                }
            }
        }
        return new ErrorType();
    }

    @Override
    public Type traverse(IfStatementError x) {
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

        return new ErrorType();
    }

    @Override
    public Type traverse(ElseIfStatementError x) {
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
        
        return new ErrorType();
    }

    @Override
    public Type traverse(FunctionStatementError x) {
        if (!x.preid.id.equals(x.postid.id)) {
            print_error("Los identificadores de la función no coinciden",0,0);
        }
        String temp_scope=new String(this.scope);
        String current_scope= new String(this.scope+Scope.getNewScope());
        this.scope= new String(current_scope);
        FTableNode node = new FTableNode(x.type, x.preid.id,temp_scope);
        for (int i = 0; i < x.params.size(); i++) {
            if (x.params.elementAt(i) instanceof In) {
                
                In param= (In)x.params.elementAt(i);
                  for (int j = 0; j < param.list.size(); j++) {
                      VTableNode paramnode = new VTableNode(param.type,1,0,param.list.elementAt(j).id,current_scope);
                      
                      if(!this.symboltable.addSymbol(paramnode)){
                        print_error("El identificador "+paramnode.getId()+" ya esta declarado en este ámbito",0,0);          
                       }else{
                       node.Add(paramnode);
                      }  
                  }
                  
            }else if(x.params.elementAt(i) instanceof Out){
                
                 Out param= (Out)x.params.elementAt(i);
                 for (int j = 0; j < param.list.size(); j++) {
                     VTableNode paramnode= new VTableNode(param.type,2,0,param.list.elementAt(j).id,current_scope);
                     
                     
                     if(!this.symboltable.addSymbol(paramnode)){
                        print_error("El identificador "+paramnode.getId()+" ya esta declarado en este ámbito",0,0);          
                      }else{
                       node.Add(paramnode);   
                     } 
                  }
            }else if(x.params.elementAt(i) instanceof InOut){
               
                InOut param= (InOut)x.params.elementAt(i);
                for (int j = 0; j < param.list.size(); j++) {
                      VTableNode paramnode=new VTableNode(param.type,3,0,param.list.elementAt(j).id,current_scope); 
                     
                     if(!this.symboltable.addSymbol(paramnode)){
                        print_error("El identificador "+paramnode.getId()+" ya esta declarado en este ámbito",0,0);          
                     }else{
                       node.Add(paramnode);
                     } 
                  }
            }
        }
        this.scope= new String(temp_scope);
        if(!this.symboltable.addSymbol(node)){
               print_error("El identificador "+x.preid+" ya esta declarado en este ámbito",0,0);          
            }
        this.current_id= x.preid.id;
        
        for (int i = 0; i < x.presta.size(); i++) {
            x.presta.elementAt(i).accept(this);
        }
        for (int i = 0; i < x.poststa.size(); i++) {
            x.poststa.elementAt(i).accept(this);
        }
        return new ErrorType();
    }

    @Override
    public Type traverse(ReturnStatementError x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(PutError x) {
        Type type = x.accept(this);
        if (type instanceof ErrorType) {
            print_error(" en evaluacion de la expresion de put",0,0);
            return new ErrorType(); 
        }
       return new ErrorType();
    }

    @Override
    public Type traverse(GetError x) {
        Type type = x.accept(this);
        if (type instanceof ErrorType) {
            print_error(" en evaluacion de la expresion de get",0,0);
            return new ErrorType(); 
        }
       return new ErrorType();
    }

    @Override
    public Type traverse(ProgramInitError x) {
        if(!x.preid.equals(x.postid)){
            print_error("Los identificadores del procedimiento no coinciden",0,0);
        }
        String current_scope= Scope.getNewScope();
        this.scope = new String(current_scope);
        FTableNode node = new FTableNode(new NullType(),x.preid.id,current_scope); 
        symboltable.addSymbol(node);
        
        Declarations declarations = x.declarations;
        for (int i = 0; i < declarations.size(); i++) {
            declarations.elementAt(i).accept(this);
        }
        this.current_id= x.preid.id;
        Statements sta = x.stas;
        
        for (int i = 0; i < sta.size(); i++) {
            sta.elementAt(i).accept(this);
        }
     return new ErrorType();
    }

    @Override
    public Type traverse(StringType x) { /* Listo */
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type traverse(NullType x) {/* Listo*/
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

    @Override
    public Type traverse(IntegerLiteral x) {
        return new IntegerType();
    }

    @Override
    public Type traverse(StringLiteral x) {
        return new StringType();
    }

    @Override
    public Type traverse(FunctionCall x) {
        FTableNode node = (FTableNode)this.symboltable.findSymbol(x.id.id,this.scope);
        if(node == null){
          print_error("la funcion "+x.id.id+" no se ha declarado",0,0);
          return new ErrorType();
        }else{
           ArrayList<Type> arguments = new ArrayList(); 
            for (int i = 0; i < x.args.size(); i++) {
               Type arg=x.args.elementAt(i).accept(this);
               arguments.add(arg);
            }
            if(node.getParams().size() != arguments.size()){
             print_error("nunero erroneo de argumentos se esperaba "+ node.getParams().toString(),0,0);
             return node.getReturn_type();
            }
            
            for (int i = 0; i < node.getParams().size(); i++) {
                if(!node.getParams().get(i).getType().equals(arguments.get(i))) {
                  print_error("Tipos de argumentos invalidos se esperaba: "+node.getParams().toString(),0,0);
                  return node.getReturn_type();
                }
            }
        }
        return node.getReturn_type();
    }

    @Override
    public Type traverse(Put x) {
      Type type = x.expre.accept(this);
        if (type instanceof ErrorType) {
            print_error(" en evaluacion de la expresion de put",0,0);
            return new ErrorType(); 
        }
       return new NullType();
    }
    
}
