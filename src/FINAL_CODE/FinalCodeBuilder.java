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
import ada95compiler.FTableNode;
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

    String regex = "[\"][\\w\\W]*[\"]";
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

    private String getAviableArguments() {
        for (int i = 0; i < 4; i++) {
            if (avalibleTemps.get("$a" + i)) {
                avalibleTemps.put("$a" + i, false);
                return "$a" + i;
            }
        }
        return null;
    }

    private String getAviableSTemps() {
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
        StringBuilder final_code = new StringBuilder();
        ArrayList<VTableNode> s0_variables = this.semanticTable.getSymboltable().getAllVars("s0");
        final_code.append(".data\n");
        for (int i = 0; i < s0_variables.size(); i++) {
            VTableNode node = s0_variables.get(i);
            if (node.getType().equals(new IntegerType())) {
                final_code.append("_" + node.getId() + ":\t.word\t0");
            } else if (node.getType().equals(new BooleanType())) {
                final_code.append("_" + node.getId() + ":\t.byte\t' '");
            } else {
                /* aqui seria el codigo del float (no esta en el requerimiento)*/
            }
            final_code.append('\n');
        }

        for (int i = 0; i < this.stringsTable.size(); i++) {
            final_code.append("_msg" + i + ":\t.asciiz\t\"" + this.stringsTable.get(i) + "\"\n");
        }
        final_code.append("\n.text\n.globl main\n\n");
        final_code.append("main: \n\n");

        StringBuilder final_code_body = new StringBuilder();
        for (int i = 0; i < this.intermediateForm.operations.size(); i++) {
            Quadruple quad = this.intermediateForm.operations.elementAt(i);
            switch (quad.getType()) {
                case ADD: {
                    String t1 = null;
                    String t3 = null;
                    OperationType type = null;
                    if (this.finalTemps.get(quad.getOp1()) != null) {
                        t1 = finalTemps.get(quad.getOp1()).reg;
                        type = finalTemps.get(quad.getOp1()).type;
                    } else {
                        if (quad.getOp1().matches("[0-9]*")) { /* suma de integers */

                            t1 = getAvaliableTemp();
                            final_code_body.append("li " + t1 + "," + quad.getOp1() + "\n");
                            type = OperationType.INTEGER_OPERATION;
                        } else { /* es un id, se usa lw*/
                            /* obteniendo el scope del id */

                            String identifier = quad.getOp1();
                            String[] parse = identifier.split("_");
                            VTableNode var = (VTableNode) this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);

                            if (var.getScope().equals("s0")) {
                                if (var.getType().equals(new IntegerType())) {
                                    t1 = getAvaliableTemp();
                                    final_code_body.append("lw " + t1 + ", " + "_" + parse[1] + "\n");
                                    type = OperationType.INTEGER_OPERATION;
                                }
                            } else {
                                if (var.isParam()) {
                                    t1 = getAvaliableTemp();
                                    final_code_body.append("move " + t1 + ", " + var.getCurrent_reg() + "\n");
                                    type = OperationType.INTEGER_OPERATION;
                                } else {
                                    t1 = getAvaliableTemp();
                                    final_code_body.append("lw " + t1 + ", -" + var.getFinalcode_direction() + "($fp)\n");
                                    type = OperationType.INTEGER_OPERATION;
                                }
                            }
                        }
                    }

                    String t2 = null;
                    if (this.finalTemps.get(quad.getOp2()) != null) {
                        t2 = finalTemps.get(quad.getOp2()).reg;
                        type = finalTemps.get(quad.getOp2()).type;
                    } else {
                        if (quad.getOp2().matches("[0-9]*")) { /* suma de integers */

                            t2 = getAvaliableTemp();
                            final_code_body.append("li " + t2 + ", " + quad.getOp2() + "\n");
                            type = OperationType.INTEGER_OPERATION;
                        } else { /* es un id, se usa lw*/
                            /* obteniendo el scope del id */

                            String identifier = quad.getOp2();
                            String[] parse = identifier.split("_");
                            VTableNode var = (VTableNode) this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);

                            if (var.getScope().equals("s0")) {
                                if (var.getType().equals(new IntegerType())) {
                                    t2 = getAvaliableTemp();
                                    final_code_body.append("lw " + t2 + ", " + "_" + parse[1] + "\n");
                                    type = OperationType.INTEGER_OPERATION;
                                }
                            } else {
                                if (var.isParam()) {
                                    t2 = getAvaliableTemp();
                                    final_code_body.append("move " + t2 + ", " + var.getCurrent_reg() + "\n");
                                    type = OperationType.INTEGER_OPERATION;
                                } else {
                                    t2 = getAvaliableTemp();
                                    final_code_body.append("lw " + t2 + ", -" + var.getFinalcode_direction() + "($fp)\n");
                                    type = OperationType.INTEGER_OPERATION;
                                }
                            }
                        }
                    }

                    if (type == OperationType.INTEGER_OPERATION) {
                        t3 = getAvaliableTemp();
                        final_code_body.append("add " + t3 + ", " + t1 + ", " + t2 + "\n");
                        String var_result = quad.getStore();
                        if (var_result.contains("_")) { /* es identificador */

                            String[] parse = var_result.split("_");
                            VTableNode storeNode = (VTableNode) this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);
                            if (storeNode.getScope().equals("s0")) {
                                final_code_body.append("sw " + t3 + "," + "_" + parse[1] + "\n");
                            } else {
                                if (storeNode.isParam()) {
                                    final_code_body.append("move " + storeNode.getCurrent_reg() + "," + "_" + t3 + "\n");
                                } else {
                                    final_code_body.append("sw " + t3 + ", -" + storeNode.getFinalcode_direction() + "($fp)\n");
                                }
                            }
                            setAvaliable(t3);
                        } else { /*es temporal*/

                            this.finalTemps.put(quad.getStore(), new Info(t3, type));
                            if (this.finalTemps.get(quad.getOp1()) != null) {
                                this.finalTemps.remove(quad.getOp1());
                            }

                            if (this.finalTemps.get(quad.getOp2()) != null) {
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
                    } else {
                        if (quad.getOp1().matches("[0-9]*")) { /* suma de integers */

                            t1 = getAvaliableTemp();
                            final_code_body.append("li " + t1 + "," + quad.getOp1() + "\n");
                            type = OperationType.INTEGER_OPERATION;
                        } else { /* es un id, se usa lw*/
                            /* obteniendo el scope del id */

                            String identifier = quad.getOp1();
                            String[] parse = identifier.split("_");
                            VTableNode var = (VTableNode) this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);

                            if (var.getScope().equals("s0")) {
                                if (var.getType().equals(new IntegerType())) {
                                    t1 = getAvaliableTemp();
                                    final_code_body.append("lw " + t1 + ", " + "_" + parse[1] + "\n");
                                    type = OperationType.INTEGER_OPERATION;
                                }
                            } else {
                                if (var.isParam()) {
                                    t1 = getAvaliableTemp();
                                    final_code_body.append("move " + t1 + ", " + var.getCurrent_reg() + "\n");
                                    type = OperationType.INTEGER_OPERATION;
                                } else {
                                    t1 = getAvaliableTemp();
                                    final_code_body.append("lw " + t1 + ", -" + var.getFinalcode_direction() + "($fp)\n");
                                    type = OperationType.INTEGER_OPERATION;
                                }
                            }
                        }
                    }

                    String t2 = null;
                    if (this.finalTemps.get(quad.getOp2()) != null) {
                        t2 = finalTemps.get(quad.getOp2()).reg;
                        type = finalTemps.get(quad.getOp2()).type;
                    } else {
                        if (quad.getOp2().matches("[0-9]*")) { /* suma de integers */

                            t2 = getAvaliableTemp();
                            final_code_body.append("li " + t2 + ", " + quad.getOp2() + "\n");
                            type = OperationType.INTEGER_OPERATION;
                        } else { /* es un id, se usa lw*/
                            /* obteniendo el scope del id */

                            String identifier = quad.getOp2();
                            String[] parse = identifier.split("_");
                            VTableNode var = (VTableNode) this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);

                            if (var.getScope().equals("s0")) {
                                if (var.getType().equals(new IntegerType())) {
                                    t2 = getAvaliableTemp();
                                    final_code_body.append("lw " + t2 + ", " + "_" + parse[1] + "\n");
                                    type = OperationType.INTEGER_OPERATION;
                                }
                            } else {
                                if (var.isParam()) {
                                    t2 = getAvaliableTemp();
                                    final_code_body.append("move " + t2 + ", " + var.getCurrent_reg() + "\n");
                                    type = OperationType.INTEGER_OPERATION;
                                } else {
                                    t2 = getAvaliableTemp();
                                    final_code_body.append("lw " + t2 + ", -" + var.getFinalcode_direction() + "($fp)\n");
                                    type = OperationType.INTEGER_OPERATION;
                                }
                            }
                        }
                    }

                    if (type == OperationType.INTEGER_OPERATION) {
                        t3 = getAvaliableTemp();
                        final_code_body.append("sub " + t3 + ", " + t1 + ", " + t2 + "\n");
                        String var_result = quad.getStore();
                        if (var_result.contains("_")) { /* es identificador */

                            String[] parse = var_result.split("_");
                            VTableNode storeNode = (VTableNode) this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);
                            if (storeNode.getScope().equals("s0")) {
                                final_code_body.append("sw " + t3 + "," + "_" + parse[1] + "\n");
                            } else {
                                if (storeNode.isParam()) {
                                    final_code_body.append("move " + storeNode.getCurrent_reg() + "," + "_" + t3 + "\n");
                                } else {
                                    final_code_body.append("sw " + t3 + ", -" + storeNode.getFinalcode_direction() + "($fp)\n");
                                }
                            }
                            setAvaliable(t3);
                        } else { /*es temporal*/

                            this.finalTemps.put(quad.getStore(), new Info(t3, type));
                            if (this.finalTemps.get(quad.getOp1()) != null) {
                                this.finalTemps.remove(quad.getOp1());
                            }

                            if (this.finalTemps.get(quad.getOp2()) != null) {
                                this.finalTemps.remove(quad.getOp2());
                            }
                        }

                    }

                    setAvaliable(t1);

                    setAvaliable(t2);


                    break;
                }

                case UMIN: {
                    break;
                }

                case MUL: {
                   String t1 = null;
                    String t3 = null;
                    OperationType type = null;
                    if (this.finalTemps.get(quad.getOp1()) != null) {
                        t1 = finalTemps.get(quad.getOp1()).reg;
                        type = finalTemps.get(quad.getOp1()).type;
                    } else {
                        if (quad.getOp1().matches("[0-9]*")) { /* suma de integers */

                            t1 = getAvaliableTemp();
                            final_code_body.append("li " + t1 + "," + quad.getOp1() + "\n");
                            type = OperationType.INTEGER_OPERATION;
                        } else { /* es un id, se usa lw*/
                            /* obteniendo el scope del id */

                            String identifier = quad.getOp1();
                            String[] parse = identifier.split("_");
                            VTableNode var = (VTableNode) this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);

                            if (var.getScope().equals("s0")) {
                                if (var.getType().equals(new IntegerType())) {
                                    t1 = getAvaliableTemp();
                                    final_code_body.append("lw " + t1 + ", " + "_" + parse[1] + "\n");
                                    type = OperationType.INTEGER_OPERATION;
                                }
                            } else {
                                if (var.isParam()) {
                                    t1 = getAvaliableTemp();
                                    final_code_body.append("move " + t1 + ", " + var.getCurrent_reg() + "\n");
                                    type = OperationType.INTEGER_OPERATION;
                                } else {
                                    t1 = getAvaliableTemp();
                                    final_code_body.append("lw " + t1 + ", -" + var.getFinalcode_direction() + "($fp)\n");
                                    type = OperationType.INTEGER_OPERATION;
                                }
                            }
                        }
                    }

                    String t2 = null;
                    if (this.finalTemps.get(quad.getOp2()) != null) {
                        t2 = finalTemps.get(quad.getOp2()).reg;
                        type = finalTemps.get(quad.getOp2()).type;
                    } else {
                        if (quad.getOp2().matches("[0-9]*")) { /* suma de integers */

                            t2 = getAvaliableTemp();
                            final_code_body.append("li " + t2 + ", " + quad.getOp2() + "\n");
                            type = OperationType.INTEGER_OPERATION;
                        } else { /* es un id, se usa lw*/
                            /* obteniendo el scope del id */

                            String identifier = quad.getOp2();
                            String[] parse = identifier.split("_");
                            VTableNode var = (VTableNode) this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);

                            if (var.getScope().equals("s0")) {
                                if (var.getType().equals(new IntegerType())) {
                                    t2 = getAvaliableTemp();
                                    final_code_body.append("lw " + t2 + ", " + "_" + parse[1] + "\n");
                                    type = OperationType.INTEGER_OPERATION;
                                }
                            } else {
                                if (var.isParam()) {
                                    t2 = getAvaliableTemp();
                                    final_code_body.append("move " + t2 + ", " + var.getCurrent_reg() + "\n");
                                    type = OperationType.INTEGER_OPERATION;
                                } else {
                                    t2 = getAvaliableTemp();
                                    final_code_body.append("lw " + t2 + ", -" + var.getFinalcode_direction() + "($fp)\n");
                                    type = OperationType.INTEGER_OPERATION;
                                }
                            }
                        }
                    }

                    if (type == OperationType.INTEGER_OPERATION) {
                        t3 = getAvaliableTemp();
                        final_code_body.append("mul " + t3 + ", " + t1 + ", " + t2 + "\n");
                        String var_result = quad.getStore();
                        if (var_result.contains("_")) { /* es identificador */

                            String[] parse = var_result.split("_");
                            VTableNode storeNode = (VTableNode) this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);
                            if (storeNode.getScope().equals("s0")) {
                                final_code_body.append("sw " + t3 + "," + "_" + parse[1] + "\n");
                            } else {
                                if (storeNode.isParam()) {
                                    final_code_body.append("move " + storeNode.getCurrent_reg() + "," + "_" + t3 + "\n");
                                } else {
                                    final_code_body.append("sw " + t3 + ", -" + storeNode.getFinalcode_direction() + "($fp)\n");
                                }
                            }
                            setAvaliable(t3);
                        } else { /*es temporal*/

                            this.finalTemps.put(quad.getStore(), new Info(t3, type));
                            if (this.finalTemps.get(quad.getOp1()) != null) {
                                this.finalTemps.remove(quad.getOp1());
                            }

                            if (this.finalTemps.get(quad.getOp2()) != null) {
                                this.finalTemps.remove(quad.getOp2());
                            }
                        }

                    }

                    setAvaliable(t1);

                    setAvaliable(t2);

                    break;
                }
                case DIV: {
                   String t1 = null;
                    String t3 = null;
                    OperationType type = null;
                    if (this.finalTemps.get(quad.getOp1()) != null) {
                        t1 = finalTemps.get(quad.getOp1()).reg;
                        type = finalTemps.get(quad.getOp1()).type;
                    } else {
                        if (quad.getOp1().matches("[0-9]*")) { /* suma de integers */

                            t1 = getAvaliableTemp();
                            final_code_body.append("li " + t1 + "," + quad.getOp1() + "\n");
                            type = OperationType.INTEGER_OPERATION;
                        } else { /* es un id, se usa lw*/
                            /* obteniendo el scope del id */

                            String identifier = quad.getOp1();
                            String[] parse = identifier.split("_");
                            VTableNode var = (VTableNode) this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);

                            if (var.getScope().equals("s0")) {
                                if (var.getType().equals(new IntegerType())) {
                                    t1 = getAvaliableTemp();
                                    final_code_body.append("lw " + t1 + ", " + "_" + parse[1] + "\n");
                                    type = OperationType.INTEGER_OPERATION;
                                }
                            } else {
                                if (var.isParam()) {
                                    t1 = getAvaliableTemp();
                                    final_code_body.append("move " + t1 + ", " + var.getCurrent_reg() + "\n");
                                    type = OperationType.INTEGER_OPERATION;
                                } else {
                                    t1 = getAvaliableTemp();
                                    final_code_body.append("lw " + t1 + ", -" + var.getFinalcode_direction() + "($fp)\n");
                                    type = OperationType.INTEGER_OPERATION;
                                }
                            }
                        }
                    }

                    String t2 = null;
                    if (this.finalTemps.get(quad.getOp2()) != null) {
                        t2 = finalTemps.get(quad.getOp2()).reg;
                        type = finalTemps.get(quad.getOp2()).type;
                    } else {
                        if (quad.getOp2().matches("[0-9]*")) { /* suma de integers */

                            t2 = getAvaliableTemp();
                            final_code_body.append("li " + t2 + ", " + quad.getOp2() + "\n");
                            type = OperationType.INTEGER_OPERATION;
                        } else { /* es un id, se usa lw*/
                            /* obteniendo el scope del id */

                            String identifier = quad.getOp2();
                            String[] parse = identifier.split("_");
                            VTableNode var = (VTableNode) this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);

                            if (var.getScope().equals("s0")) {
                                if (var.getType().equals(new IntegerType())) {
                                    t2 = getAvaliableTemp();
                                    final_code_body.append("lw " + t2 + ", " + "_" + parse[1] + "\n");
                                    type = OperationType.INTEGER_OPERATION;
                                }
                            } else {
                                if (var.isParam()) {
                                    t2 = getAvaliableTemp();
                                    final_code_body.append("move " + t2 + ", " + var.getCurrent_reg() + "\n");
                                    type = OperationType.INTEGER_OPERATION;
                                } else {
                                    t2 = getAvaliableTemp();
                                    final_code_body.append("lw " + t2 + ", -" + var.getFinalcode_direction() + "($fp)\n");
                                    type = OperationType.INTEGER_OPERATION;
                                }
                            }
                        }
                    }

                    if (type == OperationType.INTEGER_OPERATION) {
                        t3 = getAvaliableTemp();
                        final_code_body.append("div " + t3 + ", " + t1 + ", " + t2 + "\n");
                        String var_result = quad.getStore();
                        if (var_result.contains("_")) { /* es identificador */

                            String[] parse = var_result.split("_");
                            VTableNode storeNode = (VTableNode) this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);
                            if (storeNode.getScope().equals("s0")) {
                                final_code_body.append("sw " + t3 + "," + "_" + parse[1] + "\n");
                            } else {
                                if (storeNode.isParam()) {
                                    final_code_body.append("move " + storeNode.getCurrent_reg() + "," + "_" + t3 + "\n");
                                } else {
                                    final_code_body.append("sw " + t3 + ", -" + storeNode.getFinalcode_direction() + "($fp)\n");
                                }
                            }
                            setAvaliable(t3);
                        } else { /*es temporal*/

                            this.finalTemps.put(quad.getStore(), new Info(t3, type));
                            if (this.finalTemps.get(quad.getOp1()) != null) {
                                this.finalTemps.remove(quad.getOp1());
                            }

                            if (this.finalTemps.get(quad.getOp2()) != null) {
                                this.finalTemps.remove(quad.getOp2());
                            }
                        }

                    }

                    setAvaliable(t1);

                    setAvaliable(t2);

                    break;
                }

                case IF_GEQ: {
                    String t1 = null;
                    String t2 = null;
                    OperationType type = null;

                    if (this.finalTemps.get(quad.getOp1()) != null) {
                        t1 = this.finalTemps.get(quad.getOp1()).reg;
                        type = this.finalTemps.get(quad.getOp1()).type;
                    } else {
                        if (quad.getOp1().matches("[0-9]+")) { /* opcion integer*/

                            t1 = getAvaliableTemp();
                            final_code_body.append("li " + t1 + "," + quad.getOp1() + "\n");
                            type = OperationType.INTEGER_OPERATION;
                        } else { /* si es identificador*/
                            String variable = quad.getOp1();
                            String[] parse = variable.split("_");
                            VTableNode var = (VTableNode) this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);
                            
                            if(var.getScope().equals("s0")){ /* esta en el scope 0*/
                                if (var.getType().equals(new IntegerType())) {
                                  t1 = getAvaliableTemp();
                                  final_code_body.append("lw " + t1 + ", " + "_" + parse[1] + "\n");
                                  type = OperationType.INTEGER_OPERATION;
                                }
                            }else{/* esta dentro de una funcion */
                               if(var.isParam()){
                                 t1 = getAvaliableTemp();
                                 final_code_body.append("move "+t1+ ", "+var.getCurrent_reg()+"\n");
                                 type = OperationType.INTEGER_OPERATION;
                               }else{
                                 t1 = getAvaliableTemp();
                                 final_code_body.append("lw "+t1+", -"+var.getFinalcode_direction()+"($fp)\n");
                                 type = OperationType.INTEGER_OPERATION;
                               }
                            
                            }
                            
                            
                        }
                    }

                    if (this.finalTemps.get(quad.getOp2()) != null) {
                        t2 = this.finalTemps.get(quad.getOp2()).reg;
                        type = this.finalTemps.get(quad.getOp2()).type;
                    } else {
                        if (quad.getOp2().matches("[0-9]+")) { /* opcion integer*/

                            t2 = getAvaliableTemp();
                            final_code_body.append("li " + t2 + "," + quad.getOp2() + "\n");
                            type = OperationType.INTEGER_OPERATION;
                        } else { /* si es identificador*/

                            String variable = quad.getOp2();
                            String[] parse = variable.split("_");
                            VTableNode var = (VTableNode) this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);
                            if (var.getType().equals(new IntegerType())) {
                                t2 = getAvaliableTemp();
                                final_code_body.append("lw " + t2 + "," + "_" + parse[1] + "\n");
                                type = OperationType.INTEGER_OPERATION;
                            }
                        }
                    }

                    if (type == OperationType.INTEGER_OPERATION) {
                        final_code_body.append("bge " + t1 + ", " + t2 + ", " + quad.getLabel().toString() + "\n");
                    }

                    setAvaliable(t1);
                    setAvaliable(t2);

                    break;
                }

                case IF_LEQ: {
                   String t1 = null;
                    String t2 = null;
                    OperationType type = null;

                    if (this.finalTemps.get(quad.getOp1()) != null) {
                        t1 = this.finalTemps.get(quad.getOp1()).reg;
                        type = this.finalTemps.get(quad.getOp1()).type;
                    } else {
                        if (quad.getOp1().matches("[0-9]+")) { /* opcion integer*/

                            t1 = getAvaliableTemp();
                            final_code_body.append("li " + t1 + "," + quad.getOp1() + "\n");
                            type = OperationType.INTEGER_OPERATION;
                        } else { /* si es identificador*/
                            String variable = quad.getOp1();
                            String[] parse = variable.split("_");
                            VTableNode var = (VTableNode) this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);
                            
                            if(var.getScope().equals("s0")){ /* esta en el scope 0*/
                                if (var.getType().equals(new IntegerType())) {
                                  t1 = getAvaliableTemp();
                                  final_code_body.append("lw " + t1 + ", " + "_" + parse[1] + "\n");
                                  type = OperationType.INTEGER_OPERATION;
                                }
                            }else{/* esta dentro de una funcion */
                               if(var.isParam()){
                                 t1 = getAvaliableTemp();
                                 final_code_body.append("move "+t1+ ", "+var.getCurrent_reg()+"\n");
                                 type = OperationType.INTEGER_OPERATION;
                               }else{
                                 t1 = getAvaliableTemp();
                                 final_code_body.append("lw "+t1+", -"+var.getFinalcode_direction()+"($fp)\n");
                                 type = OperationType.INTEGER_OPERATION;
                               }
                            
                            }
                            
                            
                        }
                    }

                    if (this.finalTemps.get(quad.getOp2()) != null) {
                        t2 = this.finalTemps.get(quad.getOp2()).reg;
                        type = this.finalTemps.get(quad.getOp2()).type;
                    } else {
                        if (quad.getOp2().matches("[0-9]+")) { /* opcion integer*/

                            t2 = getAvaliableTemp();
                            final_code_body.append("li " + t2 + "," + quad.getOp2() + "\n");
                            type = OperationType.INTEGER_OPERATION;
                        } else { /* si es identificador*/

                            String variable = quad.getOp2();
                            String[] parse = variable.split("_");
                            VTableNode var = (VTableNode) this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);
                            if (var.getType().equals(new IntegerType())) {
                                t2 = getAvaliableTemp();
                                final_code_body.append("lw " + t2 + "," + "_" + parse[1] + "\n");
                                type = OperationType.INTEGER_OPERATION;
                            }
                        }
                    }

                    if (type == OperationType.INTEGER_OPERATION) {
                        final_code_body.append("beq " + t1 + ", " + t2 + ", " + quad.getLabel().toString() + "\n");
                    }

                    setAvaliable(t1);
                    setAvaliable(t2);
                    break;
                }
                case IF_GT: {
                    String t1 = null;
                    String t2 = null;
                    OperationType type = null;

                    if (this.finalTemps.get(quad.getOp1()) != null) {
                        t1 = this.finalTemps.get(quad.getOp1()).reg;
                        type = this.finalTemps.get(quad.getOp1()).type;
                    } else {
                        if (quad.getOp1().matches("[0-9]+")) { /* opcion integer*/

                            t1 = getAvaliableTemp();
                            final_code_body.append("li " + t1 + "," + quad.getOp1() + "\n");
                            type = OperationType.INTEGER_OPERATION;
                        } else { /* si es identificador*/
                            String variable = quad.getOp1();
                            String[] parse = variable.split("_");
                            VTableNode var = (VTableNode) this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);
                            
                            if(var.getScope().equals("s0")){ /* esta en el scope 0*/
                                if (var.getType().equals(new IntegerType())) {
                                  t1 = getAvaliableTemp();
                                  final_code_body.append("lw " + t1 + ", " + "_" + parse[1] + "\n");
                                  type = OperationType.INTEGER_OPERATION;
                                }
                            }else{/* esta dentro de una funcion */
                               if(var.isParam()){
                                 t1 = getAvaliableTemp();
                                 final_code_body.append("move "+t1+ ", "+var.getCurrent_reg()+"\n");
                                 type = OperationType.INTEGER_OPERATION;
                               }else{
                                 t1 = getAvaliableTemp();
                                 final_code_body.append("lw "+t1+", -"+var.getFinalcode_direction()+"($fp)\n");
                                 type = OperationType.INTEGER_OPERATION;
                               }
                            
                            }
                            
                            
                        }
                    }

                    if (this.finalTemps.get(quad.getOp2()) != null) {
                        t2 = this.finalTemps.get(quad.getOp2()).reg;
                        type = this.finalTemps.get(quad.getOp2()).type;
                    } else {
                        if (quad.getOp2().matches("[0-9]+")) { /* opcion integer*/

                            t2 = getAvaliableTemp();
                            final_code_body.append("li " + t2 + "," + quad.getOp2() + "\n");
                            type = OperationType.INTEGER_OPERATION;
                        } else { /* si es identificador*/

                            String variable = quad.getOp2();
                            String[] parse = variable.split("_");
                            VTableNode var = (VTableNode) this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);
                            if (var.getType().equals(new IntegerType())) {
                                t2 = getAvaliableTemp();
                                final_code_body.append("lw " + t2 + "," + "_" + parse[1] + "\n");
                                type = OperationType.INTEGER_OPERATION;
                            }
                        }
                    }

                    if (type == OperationType.INTEGER_OPERATION) {
                        final_code_body.append("bgt " + t1 + ", " + t2 + ", " + quad.getLabel().toString() + "\n");
                    }

                    setAvaliable(t1);
                    setAvaliable(t2);

                    break;
                }

                case IF_LT: {
                    String t1 = null;
                    String t2 = null;
                    OperationType type = null;

                    if (this.finalTemps.get(quad.getOp1()) != null) {
                        t1 = this.finalTemps.get(quad.getOp1()).reg;
                        type = this.finalTemps.get(quad.getOp1()).type;
                    } else {
                        if (quad.getOp1().matches("[0-9]+")) { /* opcion integer*/

                            t1 = getAvaliableTemp();
                            final_code_body.append("li " + t1 + "," + quad.getOp1() + "\n");
                            type = OperationType.INTEGER_OPERATION;
                        } else { /* si es identificador*/
                            String variable = quad.getOp1();
                            String[] parse = variable.split("_");
                            VTableNode var = (VTableNode) this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);
                            
                            if(var.getScope().equals("s0")){ /* esta en el scope 0*/
                                if (var.getType().equals(new IntegerType())) {
                                  t1 = getAvaliableTemp();
                                  final_code_body.append("lw " + t1 + ", " + "_" + parse[1] + "\n");
                                  type = OperationType.INTEGER_OPERATION;
                                }
                            }else{/* esta dentro de una funcion */
                               if(var.isParam()){
                                 t1 = getAvaliableTemp();
                                 final_code_body.append("move "+t1+ ", "+var.getCurrent_reg()+"\n");
                                 type = OperationType.INTEGER_OPERATION;
                               }else{
                                 t1 = getAvaliableTemp();
                                 final_code_body.append("lw "+t1+", -"+var.getFinalcode_direction()+"($fp)\n");
                                 type = OperationType.INTEGER_OPERATION;
                               }
                            
                            }
                            
                            
                        }
                    }

                    if (this.finalTemps.get(quad.getOp2()) != null) {
                        t2 = this.finalTemps.get(quad.getOp2()).reg;
                        type = this.finalTemps.get(quad.getOp2()).type;
                    } else {
                        if (quad.getOp2().matches("[0-9]+")) { /* opcion integer*/

                            t2 = getAvaliableTemp();
                            final_code_body.append("li " + t2 + "," + quad.getOp2() + "\n");
                            type = OperationType.INTEGER_OPERATION;
                        } else { /* si es identificador*/

                            String variable = quad.getOp2();
                            String[] parse = variable.split("_");
                            VTableNode var = (VTableNode) this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);
                            if (var.getType().equals(new IntegerType())) {
                                t2 = getAvaliableTemp();
                                final_code_body.append("lw " + t2 + "," + "_" + parse[1] + "\n");
                                type = OperationType.INTEGER_OPERATION;
                            }
                        }
                    }

                    if (type == OperationType.INTEGER_OPERATION) {
                        final_code_body.append("blt " + t1 + ", " + t2 + ", " + quad.getLabel().toString() + "\n");
                    }

                    setAvaliable(t1);
                    setAvaliable(t2);

                    break;
                }

                case IF_NEQ: {
                    String t1 = null;
                    String t2 = null;
                    OperationType type = null;

                    if (this.finalTemps.get(quad.getOp1()) != null) {
                        t1 = this.finalTemps.get(quad.getOp1()).reg;
                        type = this.finalTemps.get(quad.getOp1()).type;
                    } else {
                        if (quad.getOp1().matches("[0-9]+")) { /* opcion integer*/

                            t1 = getAvaliableTemp();
                            final_code_body.append("li " + t1 + "," + quad.getOp1() + "\n");
                            type = OperationType.INTEGER_OPERATION;
                        } else { /* si es identificador*/
                            String variable = quad.getOp1();
                            String[] parse = variable.split("_");
                            VTableNode var = (VTableNode) this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);
                            
                            if(var.getScope().equals("s0")){ /* esta en el scope 0*/
                                if (var.getType().equals(new IntegerType())) {
                                  t1 = getAvaliableTemp();
                                  final_code_body.append("lw " + t1 + ", " + "_" + parse[1] + "\n");
                                  type = OperationType.INTEGER_OPERATION;
                                }
                            }else{/* esta dentro de una funcion */
                               if(var.isParam()){
                                 t1 = getAvaliableTemp();
                                 final_code_body.append("move "+t1+ ", "+var.getCurrent_reg()+"\n");
                                 type = OperationType.INTEGER_OPERATION;
                               }else{
                                 t1 = getAvaliableTemp();
                                 final_code_body.append("lw "+t1+", -"+var.getFinalcode_direction()+"($fp)\n");
                                 type = OperationType.INTEGER_OPERATION;
                               }
                            
                            }
                            
                            
                        }
                    }

                    if (this.finalTemps.get(quad.getOp2()) != null) {
                        t2 = this.finalTemps.get(quad.getOp2()).reg;
                        type = this.finalTemps.get(quad.getOp2()).type;
                    } else {
                        if (quad.getOp2().matches("[0-9]+")) { /* opcion integer*/

                            t2 = getAvaliableTemp();
                            final_code_body.append("li " + t2 + "," + quad.getOp2() + "\n");
                            type = OperationType.INTEGER_OPERATION;
                        } else { /* si es identificador*/

                            String variable = quad.getOp2();
                            String[] parse = variable.split("_");
                            VTableNode var = (VTableNode) this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);
                            if (var.getType().equals(new IntegerType())) {
                                t2 = getAvaliableTemp();
                                final_code_body.append("lw " + t2 + "," + "_" + parse[1] + "\n");
                                type = OperationType.INTEGER_OPERATION;
                            }
                        }
                    }

                    if (type == OperationType.INTEGER_OPERATION) {
                        final_code_body.append("bne " + t1 + ", " + t2 + ", " + quad.getLabel().toString() + "\n");
                    }

                    setAvaliable(t1);
                    setAvaliable(t2);

                    break;
                }

                case IF_EQ: {
                   String t1 = null;
                    String t2 = null;
                    OperationType type = null;

                    if (this.finalTemps.get(quad.getOp1()) != null) {
                        t1 = this.finalTemps.get(quad.getOp1()).reg;
                        type = this.finalTemps.get(quad.getOp1()).type;
                    } else {
                        if (quad.getOp1().matches("[0-9]+")) { /* opcion integer*/

                            t1 = getAvaliableTemp();
                            final_code_body.append("li " + t1 + "," + quad.getOp1() + "\n");
                            type = OperationType.INTEGER_OPERATION;
                        } else { /* si es identificador*/
                            String variable = quad.getOp1();
                            String[] parse = variable.split("_");
                            VTableNode var = (VTableNode) this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);
                            
                            if(var.getScope().equals("s0")){ /* esta en el scope 0*/
                                if (var.getType().equals(new IntegerType())) {
                                  t1 = getAvaliableTemp();
                                  final_code_body.append("lw " + t1 + ", " + "_" + parse[1] + "\n");
                                  type = OperationType.INTEGER_OPERATION;
                                }
                            }else{/* esta dentro de una funcion */
                               if(var.isParam()){
                                 t1 = getAvaliableTemp();
                                 final_code_body.append("move "+t1+ ", "+var.getCurrent_reg()+"\n");
                                 type = OperationType.INTEGER_OPERATION;
                               }else{
                                 t1 = getAvaliableTemp();
                                 final_code_body.append("lw "+t1+", -"+var.getFinalcode_direction()+"($fp)\n");
                                 type = OperationType.INTEGER_OPERATION;
                               }
                            
                            }
                            
                            
                        }
                    }

                    if (this.finalTemps.get(quad.getOp2()) != null) {
                        t2 = this.finalTemps.get(quad.getOp2()).reg;
                        type = this.finalTemps.get(quad.getOp2()).type;
                    } else {
                        if (quad.getOp2().matches("[0-9]+")) { /* opcion integer*/

                            t2 = getAvaliableTemp();
                            final_code_body.append("li " + t2 + "," + quad.getOp2() + "\n");
                            type = OperationType.INTEGER_OPERATION;
                        } else { /* si es identificador*/

                            String variable = quad.getOp2();
                            String[] parse = variable.split("_");
                            VTableNode var = (VTableNode) this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);
                            if (var.getType().equals(new IntegerType())) {
                                t2 = getAvaliableTemp();
                                final_code_body.append("lw " + t2 + "," + "_" + parse[1] + "\n");
                                type = OperationType.INTEGER_OPERATION;
                            }
                        }
                    }

                    if (type == OperationType.INTEGER_OPERATION) {
                        final_code_body.append("beq " + t1 + ", " + t2 + ", " + quad.getLabel().toString() + "\n");
                    }

                    setAvaliable(t1);
                    setAvaliable(t2);
                    break;
                }

                case ASSIGN: {
                    OperationType type = null;
                    String t1 = null;

                    if (quad.getOp1().matches("[0-9]+")) { /* es integer*/

                        t1 = getAvaliableTemp();
                        final_code_body.append("li " + t1 + ", " + quad.getOp1() + "\n");
                    } else if (this.finalTemps.get(quad.getOp1()) != null) {
                        t1 = this.finalTemps.get(quad.getOp1()).reg;
                        type = this.finalTemps.get(quad.getOp1()).type;
                    } else if (quad.getOp1().contains("_")) {
                        String[] parse = quad.getOp1().split("_");
                        VTableNode node = (VTableNode) this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);
                        if (!node.getScope().equals("s0")) {
                            if (!node.isParam()) {
                                t1 = getAvaliableTemp();
                                final_code_body.append("lw " + t1 + ", -" + node.getFinalcode_direction() + "($fp)\n");
                            } else {
                                t1 = getAvaliableTemp();
                                final_code_body.append("move " + t1 + ", " + node.getCurrent_reg() + "\n");
                            }
                        }
                    } else if (quad.getOp1().equals("RET")) {
                        t1 = $v0;
                    }

                    String identifier = quad.getStore();
                    String[] parse = identifier.split("_");
                    if (identifier.equals("RET")) {
                        final_code_body.append("move " + $v0 + ", " + t1 + "\n");
                        final_code_body.append("b _fin" + quad.getLabel().toString() + "\n");
                    } else {
                        VTableNode currentVar = (VTableNode) this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);
                        if (!currentVar.getScope().equals("s0")) {
                            if (currentVar.isParam()) {
                                final_code_body.append("move " + currentVar.getCurrent_reg() + ", " + t1 + "\n");
                            } else {
                                final_code_body.append("sw " + t1 + ", -" + currentVar.getFinalcode_direction() + "($fp)" + "\n");
                            }
                        } else {
                            // Si estoy en s0
                            final_code_body.append("sw " + t1 + ", " + "_" + parse[1] + "\n");
                        }

                    }

                    setAvaliable(t1);

                    if (this.finalTemps.get(quad.getOp1()) != null) {
                        this.finalTemps.remove(quad.getOp1());
                    }

                    break;
                }
                case PARAM: {
                    String t1 = null;
                    OperationType type = null;
                    if (quad.getOp1().matches("[0-9]+")) { /* Es Integer */

                        t1 = getAvaliableTemp();
                        final_code_body.append("li " + t1 + ", " + quad.getOp1() + "\n");
                    } else if (quad.getOp1().contains("_")) {/* Es Identificador*/
                        String identifier = quad.getOp1();
                        String[] parse = identifier.split("_");
                        VTableNode node = (VTableNode)this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);
                        if(node.getScope().equals("s0")){
                         t1 = getAvaliableTemp();
                         final_code_body.append("lw " + t1 + ", " + "_" + parse[1] + "\n");
                        } else{
                           if(node.isParam()){
                             t1 = getAvaliableTemp();
                             final_code_body.append("move " + t1 +", "+node.getCurrent_reg()+ "\n");
                           }else{
                             t1 = getAvaliableTemp();
                             final_code_body.append("lw " + t1 +", -"+node.getFinalcode_direction()+"($fp)"+ "\n");
                           }
                        
                        }
                        
                    } else {
                        t1 = this.finalTemps.get(quad.getOp1()).reg;
                        type = this.finalTemps.get(quad.getOp1()).type;
                    }
                    String argument = getAviableArguments();
                    if (argument != null) {
                        final_code_body.append("move " + argument + ", " + t1 + "\n");
                    } else {
                        /* aqui los argumentos se meten en la pila */
                    }

                    setAvaliable(t1);
                    break;
                }

                case CALL: {
                    final_code_body.append("jal " + quad.getOp1() + "\n");
                    for (int j = 0; j <= 3; j++) {
                        setAvaliable("$a" + j);
                    }
                    break;
                }

                case GOTO: {
                    final_code_body.append("b " + quad.getLabel() + "\n");
                    break;
                }

                case PRINT: {
                    if (quad.getOp1().matches(regex)) {
                        final_code_body.append("li " + $v0 + ", 4\n");
                        final_code_body.append("la " + $a0 + ", _msg" + Integer.toString(stringsTable.indexOf(quad.getOp1().replaceAll("\"", ""))) + "\n");
                        final_code_body.append("syscall\n");

                    } else if (quad.getOp1().matches("[0-9]+")) {/* es integer */

                        String t1 = getAvaliableTemp();
                        final_code_body.append("li " + $v0 + ", 4\n");
                        final_code_body.append("li " + t1 + ", " + quad.getOp1() + "\n");
                        final_code_body.append("move " + $a0 + ", " + t1 + "\n");
                        final_code_body.append("syscall\n");
                        setAvaliable(t1);
                    }else if (quad.getOp1().contains("_")) {/* es identificador */
                        String identifier = quad.getOp1();
                        String[] parse = identifier.split("_");
                        if (this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]) != null) {
                            VTableNode var = (VTableNode) this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);
                            if(var.getScope().equals("s0")){
                                if (var.getType().equals(new IntegerType())) {
                                    final_code_body.append("li " + $v0 + ", 1\n");
                                    final_code_body.append("lw " + $a0 + ", " + "_" + parse[1] + "\n");
                                    final_code_body.append("syscall\n");
                                } else if (var.getType().equals(new BooleanType())) {
                                    final_code_body.append("li " + $v0 + ", 1\n");
                                    final_code_body.append("lw " + $a0 + ", " + "_" + parse[1] + "\n");
                                    final_code_body.append("syscall\n");
                                }
                            }else{
                              if(var.isParam()){
                               final_code_body.append("li " + $v0 + ", 1\n");
                               final_code_body.append("move" + $a0 +", " +var.getCurrent_reg()+ "\n");
                               final_code_body.append("syscall\n");
                              }else{
                               final_code_body.append("li " + $v0 + ", 1\n");
                               final_code_body.append("lw" + $a0 +", -" +var.getFinalcode_direction()+"($fp)"+ "\n");
                               final_code_body.append("syscall\n"); 
                              }
                            
                            }
                            
                            
                        }
                    } else { 
                        String t1 = this.finalTemps.get(quad.getOp1()).reg;
                        OperationType type = this.finalTemps.get(quad.getOp1()).type;

                        if (type == OperationType.INTEGER_OPERATION) {
                            final_code_body.append("li " + $v0 + ", 1\n");
                            final_code_body.append("move " + $a0 + ", " + t1 + "\n");
                            final_code_body.append("syscall\n");

                        } else if (type == OperationType.BOOLEAN_OPERATION) {
                            final_code_body.append("li " + $v0 + ", 1\n");
                            final_code_body.append("move " + $a0 + ", " + t1 + "\n");
                            final_code_body.append("syscall\n");
                        }
                        setAvaliable(t1);
                        this.finalTemps.remove(quad.getOp1());
                        
                    }

                    break;
                }

                case READ: {
                    String identifier = quad.getOp1();
                    String[] parse = identifier.split("_");
                    VTableNode var = (VTableNode) this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);
                     if(var.getScope().equals("s0")){
                       if (var.getType().equals(new IntegerType())) {
                        final_code_body.append("li " + $v0 + ", 5\n");
                        final_code_body.append("syscall\n");
                        final_code_body.append("sw " + $v0 + ", _" + parse[1] + "\n");
                       }
                       
                     }else{
                       if(var.isParam()){
                        final_code_body.append("li " + $v0 + ", 5\n");
                        final_code_body.append("syscall\n");
                        final_code_body.append("move " + var.getCurrent_reg() + ", $v0"+ "\n");
                       }else{
                        final_code_body.append("li " + $v0 + ", 5\n");
                        final_code_body.append("syscall\n");
                        final_code_body.append("sw " + $v0 + ", -"+var.getFinalcode_direction()+"($fp)" + "\n");
                       }
                     
                     }
                    

                    break;
                }

                case LABEL: {
                    final_code_body.append(quad.getLabel() + ": \n");
                    if (quad.getLabel().toString().contains("_")) {
                        String[] parse = quad.getLabel().toString().split("_");
                        FTableNode function = (FTableNode) this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);
                        if (!function.getChild_scope().equals("")) {
                            String var_scope = function.getChild_scope();
                            ArrayList<VTableNode> vars = this.semanticTable.getSymboltable().getAllVars(var_scope);
                            int stackcount = 8;
                            int varinside = 0;
                            final_code_body.append("move $fp, $sp \n");
                            final_code_body.append("sw $fp, -4($sp) \n");
                            final_code_body.append("sw $ra, -8($sp) \n");
                            for (int j = 0; j < vars.size(); j++) {
                                if (vars.get(j).isParam()) {
                                    String reg = getAviableSTemps();
                                    vars.get(j).setCurrent_reg(reg);
                                    stackcount += 4;
                                    final_code_body.append("sw " + reg + " , -" + stackcount + "($sp)\n");
                                    String number = "" + reg.charAt(reg.length() - 1);
                                    final_code_body.append("move " + reg + ", " + "$a" + number + "\n");
                                } else {
                                    varinside += 4;
                                    int direction = stackcount + varinside;
                                    vars.get(j).setFinalcode_direction("" + direction);
                                }
                            }
                            final_code_body.append("sub $sp, $sp, " + (stackcount + varinside) + "\n");

                        }

                    }

                    break;
                }

                case VOID_RET: {
                    break;
                }

                case POWER: {
                    break;
                }

                case FUNCTION_END: {
                    final_code_body.append("_fin" + quad.getOp1() + ":" + "\n");
                    final_code_body.append("move $sp, $fp \n");
                    System.out.println(quad.getOp1());
                    String[] parse = quad.getOp1().split("_");
                    FTableNode function = (FTableNode) this.semanticTable.getSymboltable().findSymbol(parse[1], parse[2]);
                    String var_scope = function.getChild_scope();
                    ArrayList<VTableNode> vars = this.semanticTable.getSymboltable().getAllVars(var_scope);
                    int stackcount = 8;
                    int varinside = 0;
                    int contador_s = 0;
                    for (int j = 0; j < vars.size(); j++) {
                        if (vars.get(j).isParam()) {
                            stackcount += 4;
                            final_code_body.append("lw $s" + contador_s + " , -" + stackcount + "($fp)\n");
                            contador_s++;
                        } else {
                            varinside += 4;
                        }
                    }
                    final_code_body.append("lw $ra, -8($fp) \n");
                    final_code_body.append("lw $fp, -4($fp) \n");
                    final_code_body.append("jr $ra\n");

                    break;
                }

                case CLOSE: {
                    final_code_body.append("li " + $v0 + ", 10\n");
                    final_code_body.append("syscall\n");
                    break;
                }

            }
        }
        return final_code.append(final_code_body.toString()).toString();
    }
}
