/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FINAL_CODE;

import INTERM_LANG.IntermediateStatement;
import TRAVERSE_TREE.SemanticAnalysis;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author cbanegas
 */
public class FinalCodeBuilder {
    public static final String $v0 = "$v0";
    public static final String $v1 = "$v1";

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
    
    public static final String $f0 = "$f0";
    public static final String $f2 = "$f2";
    public static final String $f4 = "$f4";
    public static final String $f6 = "$f6";
    public static final String $f8 = "$f8";
    public static final String $f10 = "$f10";
    public static final String $f12 = "$f12";
    public static final String $f14 = "$f14";
    public static final String $f16 = "$f16";
    public static final String $f18 = "$f18";
    public static final String $f20 = "$f20";
    public static final String $f22 = "$f22";
    public static final String $f24 = "$f24";
    public static final String $f26 = "$f26";
    public static final String $f28 = "$f28";
    public static final String $f30 = "$f30";
    
    private enum OperationType {
        INTEGER_OPERATION, DOUBLE_OPERATION, CHAR_OPERATION
    }
    
    private static final String GLOBAL_SCOPE = "s0";
    
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
        avalibleTemps.put($v1, true);

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
        
        avalibleTemps.put($f0, true);
        avalibleTemps.put($f2, true);
        avalibleTemps.put($f4, true);
        avalibleTemps.put($f6, true);
        avalibleTemps.put($f8, true);
        avalibleTemps.put($f10, true);
        avalibleTemps.put($f12, false);
        avalibleTemps.put($f14, true);
        avalibleTemps.put($f16, true);
        avalibleTemps.put($f18, true);
        avalibleTemps.put($f20, true);
        avalibleTemps.put($f22, true);
        avalibleTemps.put($f24, true);
        avalibleTemps.put($f26, true);
        avalibleTemps.put($f28, true);
        avalibleTemps.put($f30, true);

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
    
    private String getAvaliableDoubleTemp() {
        for (int i = 2; i < 31; i += 2) {
            if (avalibleTemps.get("$f" + i)) {
                avalibleTemps.put("$f" + i, false);
                return "$f" + i;
            }
        }
        
        return null;
    }
    
    private void setAvaliableTemp(String reg) {
        avalibleTemps.put(reg, true);
    }

    public void writeFinalCode(String code) throws IOException {
        this.out.append(code);
        out.flush();
        out.close();
    }
    
    public String buildFinalCode() {
        return "";
        
    }
}
