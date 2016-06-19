/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FINAL_CODE;

import AST_TREE.BooleanType;
import AST_TREE.IntegerType;
import AST_TREE.StringType;
import INTERM_LANG.IntermediateStatement;
import INTERM_LANG.Quadruple;
import TRAVERSE_TREE.SemanticAnalysis;
import ada95compiler.VTableNode;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author cbanegas
 */
public class FinalCodeBuilder {
    String regex ="[\"][\\w\\W]*[\"]";
    public static final String $v0 = "$v0";

    public static final String $a0 = "$a0";
    public static final String $a1 = "$a1";
    public static final String $a2 = "$a2";
    public static final String $a3 = "$a3";

    public static final String $t0 = "$t0";
    public static final String $t1 = "$t1";
    public static final String $t2 = "$t2";
    public static final String $t3 = "$t3";
    public static final String $t4 = "$t4";
    public static final String $t5 = "$t5";
    public static final String $t6 = "$t6";
    public static final String $t7 = "$t7";

    public static final String $s0 = "$s0";
    public static final String $s1 = "$s1";
    public static final String $s2 = "$s2";
    public static final String $s3 = "$s3";
    public static final String $s4 = "$s4";
    public static final String $s5 = "$s5";
    public static final String $s6 = "$s6";
    public static final String $s7 = "$s7";

    public static final String $sp = "$sp";
    public static final String $fp = "$fp";
    public static final String $ra = "$ra";
    
    private enum OperationType {
        INTEGER_OPERATION, BOOLEAN_OPERATION
    }
    
    
    private class Info {
        public String reg;
        public OperationType type;

        public Info(String reg, OperationType type) {
            this.reg = reg;
            this.type = type;
        }
    }
    
    private SemanticAnalysis semanticTable;
    private BufferedWriter out;
    private HashMap<String, Boolean> avalibleTemps;
    private HashMap<String, Info> finalTemps;
    private IntermediateStatement intermediateForm;
    private List<String> stringsTable;
    
     public FinalCodeBuilder(SemanticAnalysis semanticTable, File out, IntermediateStatement intermediateForm, List<String> stringsTable) throws IOException {
        this.semanticTable = semanticTable;
        this.out = new BufferedWriter(new FileWriter(out));
        this.avalibleTemps = new HashMap();
        this.finalTemps = new HashMap();
        this.stringsTable = stringsTable;

        // Filling hashmap
        avalibleTemps.put($v0, true);

        avalibleTemps.put($a0, true);
        avalibleTemps.put($a1, true);
        avalibleTemps.put($a2, true);
        avalibleTemps.put($a3, true);

        avalibleTemps.put($t0, true);
        avalibleTemps.put($t1, true);
        avalibleTemps.put($t2, true);
        avalibleTemps.put($t3, true);
        avalibleTemps.put($t4, true);
        avalibleTemps.put($t5, true);
        avalibleTemps.put($t6, true);
        avalibleTemps.put($t7, true);

        avalibleTemps.put($s0, true);
        avalibleTemps.put($s1, true);
        avalibleTemps.put($s2, true);
        avalibleTemps.put($s3, true);
        avalibleTemps.put($s4, true);
        avalibleTemps.put($s5, true);
        avalibleTemps.put($s6, true);
        avalibleTemps.put($s7, true);
        

        this.intermediateForm = intermediateForm;
    }
     
    private String getAvaliableTemp() {
        for (int i = 0; i < 8; i++) {
            if (avalibleTemps.get("$t" + i)) {
                avalibleTemps.put("$t" + i, false);
                return "$t" + i;
            }
        }

        return null;
    }
    
    private String getAviableArguments(){
        for (int i = 0; i < 4; i++) {
            if (avalibleTemps.get("$a" + i)) {
                avalibleTemps.put("$a" + i, false);
                return "$a" + i;
            }
        }
       return null;
    }
    
    private String getAviableSTemps(){
        for (int i = 0; i < 4; i++) {
            if (avalibleTemps.get("$s" + i)) {
                avalibleTemps.put("$s" + i, false);
                return "$s" + i;
            }
        }
       return null;
    }
     
    
    private void setAvaliable(String reg) {
        avalibleTemps.put(reg, true);
    }

    public void writeFinalCode(String code) throws IOException {
        this.out.append(code);
        out.flush();
        out.close();
    }
    
    public String buildFinalCode() {
        StringBuilder final_code = new StringBuilder() ;
        ArrayList<VTableNode> s0_variables =this.semanticTable.getSymboltable().getAllVars("s0");
        final_code.append(".data\n");
        for (int i = 0; i < s0_variables.size(); i++) {
           VTableNode node = s0_variables.get(i);
            if ( node.getType().equals(new IntegerType())) {
                final_code.append("_"+node.getId()+":\t.word\t0");
            }else if(node.getType().equals(new BooleanType())){
                final_code.append("_"+node.getId()+":\t.byte\t' '");
            }else{
            /* aqui seria el codigo del float (no esta en el requerimiento)*/
            }
            final_code.append('\n');
        }
        
        for (int i = 0; i < this.stringsTable.size(); i++) {
            final_code.append("_msg"+i+":\t.asciiz\t\""+this.stringsTable.get(i)+"\"\n");
        }
         final_code.append("\n.text\n.globl main\n\n");
         final_code.append("main: \n\n");
         
         StringBuilder final_code_body = new StringBuilder();
         for (int i = 0; i < this.intermediateForm.operations.size(); i++) {
            Quadruple quad = this.intermediateForm.operations.elementAt(i);
            switch(quad.getType()){
                case ADD: {
                 String t1 = null;   
                 String t3 = null;
                 OperationType type = null;
                    if (this.finalTemps.get(quad.getOp1()) != null) {
                        t1 = finalTemps.get(quad.getOp1()).reg;
                        type = finalTemps.get(quad.getOp1()).type;  
                    }else{
                       if(quad.getOp1().matches("[0-9]*")){ /* suma de integers */
                         t1 = getAviableSTemps();
                         final_code_body.append("li "+ t1+ ","+ quad.getOp1()+"\n");
                         type = OperationType.INTEGER_OPERATION;
                       }else{ /* es un id, se usa lw*/
                           /* obteniendo el scope del id */
                           String identifier = quad.getOp1();
                           String[] parse = identifier.split("_");
                           VTableNode var = (VTableNode)this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);
                           if(var.getType().equals(new IntegerType())){
                             t1 = getAvaliableTemp();
                             final_code_body.append("lw "+ t1+", "+"_"+parse[1]+"\n");
                             type = OperationType.INTEGER_OPERATION;
                           }
                       }
                    }
                    
                    String t2 = null;
                    if (this.finalTemps.get(quad.getOp2()) != null) {
                        t2 = finalTemps.get(quad.getOp2()).reg;
                        type = finalTemps.get(quad.getOp2()).type;
                    }else{
                       if(quad.getOp2().matches("[0-9]*")){ /* suma de integers */
                         t2 = getAviableSTemps();
                         final_code_body.append("li "+ t2+ ", "+ quad.getOp2()+"\n");
                         type = OperationType.INTEGER_OPERATION;
                       }else{ /* es un id, se usa lw*/
                           /* obteniendo el scope del id */
                           String identifier = quad.getOp2();
                           String[] parse = identifier.split("_");
                           VTableNode var = (VTableNode)this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);
                           if(var.getType().equals(new IntegerType())){
                             t2 = getAvaliableTemp();
                             final_code_body.append("lw "+ t2+", "+"_"+parse[1]+"\n");
                             type = OperationType.INTEGER_OPERATION;
                           }
                       }
                    }
                    
                    if( type == OperationType.INTEGER_OPERATION){
                        t3= getAvaliableTemp();
                        final_code_body.append("add "+t3+", "+t1+", "+t2+"\n");
                        String var_result= quad.getStore();
                        String[] parse = var_result.split("_");
                        if(var_result.contains("_")){ /* es identificador */
                         final_code_body.append("sw "+t3+","+"_"+parse[1]+"\n");
                          setAvaliable(t3);
                        }else{ /*es temporal*/
                           this.finalTemps.put(quad.getStore(),new Info(t3,type));
                          if(this.finalTemps.get(quad.getOp1()) != null){
                            this.finalTemps.remove(quad.getOp1());
                          }
                          
                          if(this.finalTemps.get(quad.getOp2()) != null){
                            this.finalTemps.remove(quad.getOp2());  
                          }
                        }
                      
                    }
                    
                  setAvaliable(t1);
                  
                  setAvaliable(t2);
                    
                  break;
                }
                    
                case MIN: {
                   String t1 = null;   
                 String t3 = null;
                 OperationType type = null;
                    if (this.finalTemps.get(quad.getOp1()) != null) {
                        t1 = finalTemps.get(quad.getOp1()).reg;
                        type = finalTemps.get(quad.getOp1()).type;  
                    }else{
                       if(quad.getOp1().matches("[0-9]*")){ /* suma de integers */
                         t1 = getAviableSTemps();
                         final_code_body.append("li "+ t1+ ","+ quad.getOp1()+"\n");
                         type = OperationType.INTEGER_OPERATION;
                       }else{ /* es un id, se usa lw*/
                           /* obteniendo el scope del id */
                           String identifier = quad.getOp1();
                           String[] parse = identifier.split("_");
                           VTableNode var = (VTableNode)this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);
                           if(var.getType().equals(new IntegerType())){
                             t1 = getAvaliableTemp();
                             final_code_body.append("lw "+ t1+", "+"_"+parse[1]+"\n");
                             type = OperationType.INTEGER_OPERATION;
                           }
                       }
                    }
                    
                    String t2 = null;
                    if (this.finalTemps.get(quad.getOp2()) != null) {
                        t2 = finalTemps.get(quad.getOp2()).reg;
                        type = finalTemps.get(quad.getOp2()).type;
                    }else{
                       if(quad.getOp2().matches("[0-9]*")){ /* suma de integers */
                         t2 = getAviableSTemps();
                         final_code_body.append("li "+ t2+ ", "+ quad.getOp2()+"\n");
                         type = OperationType.INTEGER_OPERATION;
                       }else{ /* es un id, se usa lw*/
                           /* obteniendo el scope del id */
                           String identifier = quad.getOp2();
                           String[] parse = identifier.split("_");
                           VTableNode var = (VTableNode)this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);
                           if(var.getType().equals(new IntegerType())){
                             t2 = getAvaliableTemp();
                             final_code_body.append("lw "+ t2+", "+"_"+parse[1]+"\n");
                             type = OperationType.INTEGER_OPERATION;
                           }
                       }
                    }
                    
                    if( type == OperationType.INTEGER_OPERATION){
                        t3= getAvaliableTemp();
                        final_code_body.append("sub "+t3+", "+t1+", "+t2+"\n");
                        String var_result= quad.getStore();
                        String[] parse = var_result.split("_");
                        if(var_result.contains("_")){ /* es identificador */
                         final_code_body.append("sw "+t3+","+"_"+parse[1]+"\n");
                          setAvaliable(t3);
                        }else{ /*es temporal*/
                           this.finalTemps.put(quad.getStore(),new Info(t3,type));
                          if(this.finalTemps.get(quad.getOp1()) != null){
                            this.finalTemps.remove(quad.getOp1());
                          }
                          
                          if(this.finalTemps.get(quad.getOp2()) != null){
                            this.finalTemps.remove(quad.getOp2());  
                          }
                        }
                      
                    }
                    
                  setAvaliable(t1);
                  
                  setAvaliable(t2);
                  
                  break;
                }
                  
                case UMIN:{
                  break;
                }
                    
                case MUL:{
                  String t1 = null;   
                 String t3 = null;
                 OperationType type = null;
                    if (this.finalTemps.get(quad.getOp1()) != null) {
                        t1 = finalTemps.get(quad.getOp1()).reg;
                        type = finalTemps.get(quad.getOp1()).type;  
                    }else{
                       if(quad.getOp1().matches("[0-9]*")){ /* suma de integers */
                         t1 = getAviableSTemps();
                         final_code_body.append("li "+ t1+ ","+ quad.getOp1()+"\n");
                         type = OperationType.INTEGER_OPERATION;
                       }else{ /* es un id, se usa lw*/
                           /* obteniendo el scope del id */
                           String identifier = quad.getOp1();
                           String[] parse = identifier.split("_");
                           VTableNode var = (VTableNode)this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);
                           if(var.getType().equals(new IntegerType())){
                             t1 = getAvaliableTemp();
                             final_code_body.append("lw "+ t1+", "+"_"+parse[1]+"\n");
                             type = OperationType.INTEGER_OPERATION;
                           }
                       }
                    }
                    
                    String t2 = null;
                    if (this.finalTemps.get(quad.getOp2()) != null) {
                        t2 = finalTemps.get(quad.getOp2()).reg;
                        type = finalTemps.get(quad.getOp2()).type;
                    }else{
                       if(quad.getOp2().matches("[0-9]*")){ /* suma de integers */
                         t2 = getAviableSTemps();
                         final_code_body.append("li "+ t2+ ", "+ quad.getOp2()+"\n");
                         type = OperationType.INTEGER_OPERATION;
                       }else{ /* es un id, se usa lw*/
                           /* obteniendo el scope del id */
                           String identifier = quad.getOp2();
                           String[] parse = identifier.split("_");
                           VTableNode var = (VTableNode)this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);
                           if(var.getType().equals(new IntegerType())){
                             t2 = getAvaliableTemp();
                             final_code_body.append("lw "+ t2+", "+"_"+parse[1]+"\n");
                             type = OperationType.INTEGER_OPERATION;
                           }
                       }
                    }
                    
                    if( type == OperationType.INTEGER_OPERATION){
                        t3= getAvaliableTemp();
                        final_code_body.append("mul "+t3+", "+t1+", "+t2+"\n");
                        String var_result= quad.getStore();
                        String[] parse = var_result.split("_");
                        if(var_result.contains("_")){ /* es identificador */
                         final_code_body.append("sw "+t3+","+"_"+parse[1]+"\n");
                          setAvaliable(t3);
                        }else{ /*es temporal*/
                           this.finalTemps.put(quad.getStore(),new Info(t3,type));
                          if(this.finalTemps.get(quad.getOp1()) != null){
                            this.finalTemps.remove(quad.getOp1());
                          }
                          
                          if(this.finalTemps.get(quad.getOp2()) != null){
                            this.finalTemps.remove(quad.getOp2());  
                          }
                        }
                      
                    }
                    
                  setAvaliable(t1);
                  
                  setAvaliable(t2); 
                  break;
                }
                case DIV:{
                   String t1 = null;   
                 String t3 = null;
                 OperationType type = null;
                    if (this.finalTemps.get(quad.getOp1()) != null) {
                        t1 = finalTemps.get(quad.getOp1()).reg;
                        type = finalTemps.get(quad.getOp1()).type;  
                    }else{
                       if(quad.getOp1().matches("[0-9]*")){ /* suma de integers */
                         t1 = getAviableSTemps();
                         final_code_body.append("li "+ t1+ ","+ quad.getOp1()+"\n");
                         type = OperationType.INTEGER_OPERATION;
                       }else{ /* es un id, se usa lw*/
                           /* obteniendo el scope del id */
                           String identifier = quad.getOp1();
                           String[] parse = identifier.split("_");
                           VTableNode var = (VTableNode)this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);
                           if(var.getType().equals(new IntegerType())){
                             t1 = getAvaliableTemp();
                             final_code_body.append("lw "+ t1+", "+"_"+parse[1]+"\n");
                             type = OperationType.INTEGER_OPERATION;
                           }
                       }
                    }
                    
                    String t2 = null;
                    if (this.finalTemps.get(quad.getOp2()) != null) {
                        t2 = finalTemps.get(quad.getOp2()).reg;
                        type = finalTemps.get(quad.getOp2()).type;
                    }else{
                       if(quad.getOp2().matches("[0-9]*")){ /* suma de integers */
                         t2 = getAviableSTemps();
                         final_code_body.append("li "+ t2+ ", "+ quad.getOp2()+"\n");
                         type = OperationType.INTEGER_OPERATION;
                       }else{ /* es un id, se usa lw*/
                           /* obteniendo el scope del id */
                           String identifier = quad.getOp2();
                           String[] parse = identifier.split("_");
                           VTableNode var = (VTableNode)this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);
                           if(var.getType().equals(new IntegerType())){
                             t2 = getAvaliableTemp();
                             final_code_body.append("lw "+ t2+", "+"_"+parse[1]+"\n");
                             type = OperationType.INTEGER_OPERATION;
                           }
                       }
                    }
                    
                    if( type == OperationType.INTEGER_OPERATION){
                        t3= getAvaliableTemp();
                        final_code_body.append("div "+t3+", "+t1+", "+t2+"\n");
                        String var_result= quad.getStore();
                        String[] parse = var_result.split("_");
                        if(var_result.contains("_")){ /* es identificador */
                         final_code_body.append("sw "+t3+","+"_"+parse[1]+"\n");
                          setAvaliable(t3);
                        }else{ /*es temporal*/
                           this.finalTemps.put(quad.getStore(),new Info(t3,type));
                          if(this.finalTemps.get(quad.getOp1()) != null){
                            this.finalTemps.remove(quad.getOp1());
                          }
                          
                          if(this.finalTemps.get(quad.getOp2()) != null){
                            this.finalTemps.remove(quad.getOp2());  
                          }
                        }
                      
                    }
                    
                  setAvaliable(t1);
                  
                  setAvaliable(t2);
                   break; 
                }
                    
                case IF_GEQ:{
                  String t1 = null;
                  String t2 = null;
                  OperationType type =null;
                  
                  if(this.finalTemps.get(quad.getOp1()) != null){
                    t1 = this.finalTemps.get(quad.getOp1()).reg;
                    type = this.finalTemps.get(quad.getOp1()).type;
                  }else{
                     if(quad.getOp1().matches("[0-9]+")){ /* opcion integer*/
                      t1 = getAvaliableTemp();
                      final_code_body.append("li "+t1+","+quad.getOp1()+"\n");
                      type = OperationType.INTEGER_OPERATION;
                     }else{ /* si es identificador*/
                      String variable=quad.getOp1();
                      String[] parse = variable.split("_");
                      VTableNode var = (VTableNode)this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);
                      if(var.getType().equals(new IntegerType())){
                        t1 = getAvaliableTemp();
                        final_code_body.append("lw "+t1+", "+"_"+parse[1]+"\n");
                        type = OperationType.INTEGER_OPERATION;
                      }
                     }
                  }  
                  
                  if(this.finalTemps.get(quad.getOp2()) != null){
                    t2 = this.finalTemps.get(quad.getOp2()).reg;
                    type = this.finalTemps.get(quad.getOp2()).type;
                  }else{
                     if(quad.getOp2().matches("[0-9]+")){ /* opcion integer*/
                      t2 = getAvaliableTemp();
                      final_code_body.append("li "+t2+","+quad.getOp2()+"\n");
                      type = OperationType.INTEGER_OPERATION;
                     }else{ /* si es identificador*/
                      String variable=quad.getOp2();
                      String[] parse = variable.split("_");
                      VTableNode var = (VTableNode)this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);
                      if(var.getType().equals(new IntegerType())){
                        t2 = getAvaliableTemp();
                        final_code_body.append("lw "+t2+","+"_"+parse[1]+"\n");
                        type = OperationType.INTEGER_OPERATION;
                      }
                     }
                  }
                  
                  if (type == OperationType.INTEGER_OPERATION){
                    final_code_body.append("bge "+t1+", "+t2+", "+ quad.getLabel().toString()+"\n");
                  }
                  
                  setAvaliable(t1);
                  setAvaliable(t2);
                  
                  break;
                }
                
                case IF_LEQ:{
                 String t1 = null;
                  String t2 = null;
                  OperationType type =null;
                  
                  if(this.finalTemps.get(quad.getOp1()) != null){
                    t1 = this.finalTemps.get(quad.getOp1()).reg;
                    type = this.finalTemps.get(quad.getOp1()).type;
                  }else{
                     if(quad.getOp1().matches("[0-9]+")){ /* opcion integer*/
                      t1 = getAvaliableTemp();
                      final_code_body.append("li "+t1+","+quad.getOp1()+"\n");
                      type = OperationType.INTEGER_OPERATION;
                     }else{ /* si es identificador*/
                      String variable=quad.getOp1();
                      String[] parse = variable.split("_");
                      VTableNode var = (VTableNode)this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);
                      if(var.getType().equals(new IntegerType())){
                        t1 = getAvaliableTemp();
                        final_code_body.append("lw "+t1+", "+"_"+parse[1]+"\n");
                        type = OperationType.INTEGER_OPERATION;
                      }
                     }
                  }  
                  
                  if(this.finalTemps.get(quad.getOp2()) != null){
                    t2 = this.finalTemps.get(quad.getOp2()).reg;
                    type = this.finalTemps.get(quad.getOp2()).type;
                  }else{
                     if(quad.getOp2().matches("[0-9]+")){ /* opcion integer*/
                      t2 = getAvaliableTemp();
                      final_code_body.append("li "+t2+","+quad.getOp2()+"\n");
                      type = OperationType.INTEGER_OPERATION;
                     }else{ /* si es identificador*/
                      String variable=quad.getOp2();
                      String[] parse = variable.split("_");
                      VTableNode var = (VTableNode)this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);
                      if(var.getType().equals(new IntegerType())){
                        t2 = getAvaliableTemp();
                        final_code_body.append("lw "+t2+", "+"_"+parse[1]+"\n");
                        type = OperationType.INTEGER_OPERATION;
                      }
                     }
                  }
                  
                  if (type == OperationType.INTEGER_OPERATION){
                    final_code_body.append("ble "+t1+", "+t2+", "+ quad.getLabel().toString()+"\n");
                  }
                  
                  setAvaliable(t1);
                  setAvaliable(t2);
                  break;
                }
                case IF_GT:{
                  String t1 = null;
                  String t2 = null;
                  OperationType type =null;
                  
                  if(this.finalTemps.get(quad.getOp1()) != null){
                    t1 = this.finalTemps.get(quad.getOp1()).reg;
                    type = this.finalTemps.get(quad.getOp1()).type;
                  }else{
                     if(quad.getOp1().matches("[0-9]+")){ /* opcion integer*/
                      t1 = getAvaliableTemp();
                      final_code_body.append("li "+t1+","+quad.getOp1()+"\n");
                      type = OperationType.INTEGER_OPERATION;
                     }else{ /* si es identificador*/
                      String variable=quad.getOp1();
                      String[] parse = variable.split("_");
                      VTableNode var = (VTableNode)this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);
                      if(var.getType().equals(new IntegerType())){
                        t1 = getAvaliableTemp();
                        final_code_body.append("lw "+t1+", "+"_"+parse[1]+"\n");
                        type = OperationType.INTEGER_OPERATION;
                      }
                     }
                  }  
                  
                  if(this.finalTemps.get(quad.getOp2()) != null){
                    t2 = this.finalTemps.get(quad.getOp2()).reg;
                    type = this.finalTemps.get(quad.getOp2()).type;
                  }else{
                     if(quad.getOp2().matches("[0-9]+")){ /* opcion integer*/
                      t2 = getAvaliableTemp();
                      final_code_body.append("li "+t2+","+quad.getOp2()+"\n");
                      type = OperationType.INTEGER_OPERATION;
                     }else{ /* si es identificador*/
                      String variable=quad.getOp2();
                      String[] parse = variable.split("_");
                      VTableNode var = (VTableNode)this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);
                      if(var.getType().equals(new IntegerType())){
                        t2 = getAvaliableTemp();
                        final_code_body.append("lw "+t2+", "+"_"+ parse[1]+"\n");
                        type = OperationType.INTEGER_OPERATION;
                      }
                     }
                  }
                  
                  if (type == OperationType.INTEGER_OPERATION){
                    final_code_body.append("bgt "+t1+", "+t2+", "+ quad.getLabel().toString()+"\n");
                  }
                  
                  setAvaliable(t1);
                  setAvaliable(t2);
                  
                    break;
                }
                
                case IF_LT:{
                     String t1 = null;
                  String t2 = null;
                  OperationType type =null;
                  
                  if(this.finalTemps.get(quad.getOp1()) != null){
                    t1 = this.finalTemps.get(quad.getOp1()).reg;
                    type = this.finalTemps.get(quad.getOp1()).type;
                  }else{
                     if(quad.getOp1().matches("[0-9]+")){ /* opcion integer*/
                      t1 = getAvaliableTemp();
                      final_code_body.append("li "+t1+","+quad.getOp1()+"\n");
                      type = OperationType.INTEGER_OPERATION;
                     }else{ /* si es identificador*/
                      String variable=quad.getOp1();
                      String[] parse = variable.split("_");
                      VTableNode var = (VTableNode)this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);
                      if(var.getType().equals(new IntegerType())){
                        t1 = getAvaliableTemp();
                        final_code_body.append("lw "+t1+", "+"_"+ parse[1]+"\n");
                        type = OperationType.INTEGER_OPERATION;
                      }
                     }
                  }  
                  
                  if(this.finalTemps.get(quad.getOp2()) != null){
                    t2 = this.finalTemps.get(quad.getOp2()).reg;
                    type = this.finalTemps.get(quad.getOp2()).type;
                  }else{
                     if(quad.getOp2().matches("[0-9]+")){ /* opcion integer*/
                      t2 = getAvaliableTemp();
                      final_code_body.append("li "+t2+","+quad.getOp2()+"\n");
                      type = OperationType.INTEGER_OPERATION;
                     }else{ /* si es identificador*/
                      String variable=quad.getOp2();
                      String[] parse = variable.split("_");
                      VTableNode var = (VTableNode)this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);
                      if(var.getType().equals(new IntegerType())){
                        t2 = getAvaliableTemp();
                        final_code_body.append("lw "+t2+", "+"_"+ parse[1]+"\n");
                        type = OperationType.INTEGER_OPERATION;
                      }
                     }
                  }
                  
                  if (type == OperationType.INTEGER_OPERATION){
                    final_code_body.append("blt "+t1+", "+t2+", "+ quad.getLabel().toString()+"\n");
                  }
                  
                  setAvaliable(t1);
                  setAvaliable(t2);
                    
                    break;
                }
                    
                case IF_NEQ:{
                       String t1 = null;
                  String t2 = null;
                  OperationType type =null;
                  
                  if(this.finalTemps.get(quad.getOp1()) != null){
                    t1 = this.finalTemps.get(quad.getOp1()).reg;
                    type = this.finalTemps.get(quad.getOp1()).type;
                  }else{
                     if(quad.getOp1().matches("[0-9]+")){ /* opcion integer*/
                      t1 = getAvaliableTemp();
                      final_code_body.append("li "+t1+","+quad.getOp1()+"\n");
                      type = OperationType.INTEGER_OPERATION;
                     }else{ /* si es identificador*/
                      String variable=quad.getOp1();
                      String[] parse = variable.split("_");
                      VTableNode var = (VTableNode)this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);
                      if(var.getType().equals(new IntegerType())){
                        t1 = getAvaliableTemp();
                        final_code_body.append("lw "+t1+", "+"_"+ parse[1]+"\n");
                        type = OperationType.INTEGER_OPERATION;
                      }
                     }
                  }  
                  
                  if(this.finalTemps.get(quad.getOp2()) != null){
                    t2 = this.finalTemps.get(quad.getOp2()).reg;
                    type = this.finalTemps.get(quad.getOp2()).type;
                  }else{
                     if(quad.getOp2().matches("[0-9]+")){ /* opcion integer*/
                      t2 = getAvaliableTemp();
                      final_code_body.append("li "+t2+","+quad.getOp2()+"\n");
                      type = OperationType.INTEGER_OPERATION;
                     }else{ /* si es identificador*/
                      String variable=quad.getOp2();
                      String[] parse = variable.split("_");
                      VTableNode var = (VTableNode)this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);
                      if(var.getType().equals(new IntegerType())){
                        t2 = getAvaliableTemp();
                        final_code_body.append("lw "+t2+", "+"_"+ parse[1]+"\n");
                        type = OperationType.INTEGER_OPERATION;
                      }
                     }
                  }
                  
                  if (type == OperationType.INTEGER_OPERATION){
                    final_code_body.append("bne "+t1+", "+t2+", "+ quad.getLabel().toString()+"\n");
                  }
                  
                  setAvaliable(t1);
                  setAvaliable(t2);
                    
                  break;
                }
                   
                case IF_EQ:{
                        String t1 = null;
                  String t2 = null;
                  OperationType type =null;
                  
                  if(this.finalTemps.get(quad.getOp1()) != null){
                    t1 = this.finalTemps.get(quad.getOp1()).reg;
                    type = this.finalTemps.get(quad.getOp1()).type;
                  }else{
                     if(quad.getOp1().matches("[0-9]+")){ /* opcion integer*/
                      t1 = getAvaliableTemp();
                      final_code_body.append("li "+t1+","+quad.getOp1()+"\n");
                      type = OperationType.INTEGER_OPERATION;
                     }else{ /* si es identificador*/
                      String variable=quad.getOp1();
                      String[] parse = variable.split("_");
                      VTableNode var = (VTableNode)this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);
                      if(var.getType().equals(new IntegerType())){
                        t1 = getAvaliableTemp();
                        final_code_body.append("lw "+t1+", "+"_"+ parse[1]+"\n");
                        type = OperationType.INTEGER_OPERATION;
                      }
                     }
                  }  
                  
                  if(this.finalTemps.get(quad.getOp2()) != null){
                    t2 = this.finalTemps.get(quad.getOp2()).reg;
                    type = this.finalTemps.get(quad.getOp2()).type;
                  }else{
                     if(quad.getOp2().matches("[0-9]+")){ /* opcion integer*/
                      t2 = getAvaliableTemp();
                      final_code_body.append("li "+t2+","+quad.getOp2()+"\n");
                      type = OperationType.INTEGER_OPERATION;
                     }else{ /* si es identificador*/
                      String variable=quad.getOp2();
                      String[] parse = variable.split("_");
                      VTableNode var = (VTableNode)this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);
                      if(var.getType().equals(new IntegerType())){
                        t2 = getAvaliableTemp();
                        final_code_body.append("lw "+t2+", "+"_"+ parse[1]+"\n");
                        type = OperationType.INTEGER_OPERATION;
                      }
                     }
                  }
                  
                  if (type == OperationType.INTEGER_OPERATION){
                    final_code_body.append("beq "+t1+", "+t2+", "+ quad.getLabel().toString()+"\n");
                  }
                  
                  setAvaliable(t1);
                  setAvaliable(t2); 
                  break;
                }
                    
                case ASSIGN:{
                   OperationType type = null;
                    String t1 = null;
                    
                   if(quad.getOp1().matches("[0-9]+")){ /* es integer*/
                     t1 = getAvaliableTemp();
                     final_code_body.append("li" +t1+","+  quad.getOp1()+"\n");
                   }
                   
                    if(this.finalTemps.get(quad.getOp1())!=null){
                        t1= this.finalTemps.get(quad.getOp1()).reg;
                        type= this.finalTemps.get(quad.getOp1()).type;
                     }
                    
                      String identifier = quad.getStore();
                      String[] parse = identifier.split("_");
                      final_code_body.append("sw "+t1+", "+"_"+parse[1]+"\n");
                      setAvaliable(t1);
                      if(this.finalTemps.get(quad.getOp1()) != null){
                        this.finalTemps.remove(quad.getOp1());
                      }
                   break;
                }
                case PARAM:{
                    /*  AQUIIIII   */
                   break;
                }
                    
                case CALL:{
                   break;
                }
                   
                case GOTO:{
                   final_code_body.append("b "+quad.getLabel()+"\n");
                   break;
                }
                    
                case PRINT:{
                    if(quad.getOp1().matches(regex)){
                      final_code_body.append("li "+ $v0 + ", 4\n");
                      final_code_body.append("la "+$a0+", _msg"+ Integer.toString(stringsTable.indexOf(quad.getOp1().replaceAll("\"", ""))) +"\n");
                      final_code_body.append("syscall\n");
                    
                    }else if(quad.getOp1().matches("[0-9]+")){/* es integer */
                      String t1 = getAvaliableTemp();
                      final_code_body.append("li "+$v0+", 4\n");
                      final_code_body.append("li "+t1+", "+quad.getOp1()+"\n");
                      final_code_body.append("move "+$a0+", "+t1+"\n");
                      final_code_body.append("syscall\n");
                      setAvaliable(t1);
                    }else{ /* es identificador */
                      String identifier = quad.getOp1();
                      String[] parse = identifier.split("_");
                      if(this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]) != null){
                        VTableNode var = (VTableNode)this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);
                        if(var.getType().equals(new IntegerType())){
                           final_code_body.append("li "+$v0+", 1\n");
                           final_code_body.append("lw "+ $a0+", "+"_"+parse[1]+"\n");
                           final_code_body.append("syscall\n");
                        }else if( var.getType().equals(new StringType())){
                           final_code_body.append("li "+$v0+", 4\n");
                           final_code_body.append("lw "+ $a0+", "+"_"+parse[1]+"\n");
                           final_code_body.append("syscall\n");
                        }else if( var.getType().equals(new BooleanType())){
                           final_code_body.append("li "+$v0+", 1\n");
                           final_code_body.append("lw "+ $a0+", "+"_"+parse[1]+"\n");
                           final_code_body.append("syscall\n");
                        }
                      }else{
                        String t1= this.finalTemps.get(quad.getOp1()).reg;
                        OperationType type = this.finalTemps.get(quad.getOp1()).type;
                        
                        if(type == OperationType.INTEGER_OPERATION){
                          final_code_body.append("li "+$v0 +", 1\n");
                          final_code_body.append("move "+ $a0+", "+t1+"\n");
                          final_code_body.append("syscall\n");
                          
                        }else if(type == OperationType.BOOLEAN_OPERATION){
                          final_code_body.append("li "+$v0 +", 1\n");
                          final_code_body.append("move "+ $a0+", "+t1+"\n");
                          final_code_body.append("syscall\n"); 
                        }
                        setAvaliable(t1);
                        this.finalTemps.remove(quad.getOp1());
                      }
                    }
                    
                    break;
                }
                   
                case READ:{
                    String identifier = quad.getOp1();
                    String[] parse = identifier.split("_");
                    VTableNode var = (VTableNode)this.semanticTable.getSymboltable().findSymbol(parse[1],parse[2]);
                    
                    if(var.getType().equals(new IntegerType())){
                      final_code_body.append("li "+$v0+", 5\n");
                      final_code_body.append("syscall\n");
                      final_code_body.append("sw "+$v0 +", _"+parse[1]+ "\n");
                    }
                    
                    break;
                }
                   
                case LABEL:{
                  final_code_body.append(quad.getLabel()+": \n");
                  break;
                }
                
                case VOID_RET:{
                   break;
                }
                    
                case POWER:{
                    break;
                }
                   
                case FUNCTION_END:{
                   break;
                }
                   
                case CLOSE:{
                    final_code_body.append("li "+ $v0+", 10\n");
                    final_code_body.append("syscall\n");
                    break;
                }
                   
            
            }
        }
        return final_code.append(final_code_body.toString()).toString();
    }
}
