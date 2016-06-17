/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FINAL_CODE;

import AST_TREE.BooleanType;
import AST_TREE.IntegerType;
import INTERM_LANG.IntermediateStatement;
import INTERM_LANG.Quadruple;
import TRAVERSE_TREE.SemanticAnalysis;
import ada95compiler.VTableNode;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author cbanegas
 */
public class FinalCodeBuilder {
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
    private List<Double> doublesTable;
    
     public FinalCodeBuilder(SemanticAnalysis semanticTable, File out, IntermediateStatement intermediateForm, List<String> stringsTable, List<Double> doublesTable) throws IOException {
        this.semanticTable = semanticTable;
        this.out = new BufferedWriter(new FileWriter(out));
        this.avalibleTemps = new HashMap();
        this.finalTemps = new HashMap();
        this.stringsTable = stringsTable;
        this.doublesTable = doublesTable;

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
         
         StringBuilder final_code_body = new StringBuilder();
         for (int i = 0; i < this.intermediateForm.operations.size(); i++) {
            Quadruple quad = this.intermediateForm.operations.elementAt(i);
            switch(quad.getType()){
                case ADD:
                    break;
                case MIN:
                    break;
                case UMIN:
                    break;
                case MUL:
                    break; 
                case DIV:
                    break;
                case IF_GEQ:
                    break;
                case IF_LEQ:
                    break;
                case IF_GT:
                    break;
                case IF_LT:
                    break;
                case IF_NEQ:
                    break;
                case IF_EQ:
                    break;
                case ASSIGN:
                    break;
                case PARAM:
                    break;
                case CALL:
                    break;
                case GOTO:
                    break;
                case PRINT:
                    break;
                case READ:
                    break;
                case LABEL:
                    break;
                case EXIT:
                    break;
                case VOID_RET:
                    break;
                case POWER:
                    break;
                case FUNCTION_END:
                    break;
                case CLOSE:
                    break;
            
            }
        }
        return "";
        
    }
}
