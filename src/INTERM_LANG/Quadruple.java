/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INTERM_LANG;

/**
 *
 * @author cbanegas
 */
public class Quadruple {
    public enum Operations {ADD, MIN, UMIN, MUL, DIV, IF_GEQ, IF_LEQ, IF_GT, IF_LT, IF_NEQ, IF_EQ, ASSIGN, PARAM, CALL, GOTO, PRINT, READ, LABEL, EXIT, VOID_RET, POWER, FUNCTION_END, CLOSE }
    private String store;
    private String op1;
    private String op2;
    private Operations type;
    private Label l;
    private String scope;

    public Quadruple(String store, String op1, String op2, Operations type) {
        this.store = store;
        this.op1 = op1;
        this.op2 = op2;
        this.type = type;
    }
    public Quadruple (Operations type){
       this.type= type;
    }
    
    public Quadruple(String store, String op1, String op2, Operations type, String scope) {
        this.store = store;
        this.op1 = op1;
        this.op2 = op2;
        this.type = type;
        this.scope = scope;
    }

    public Quadruple(String store, String op1, String op2, Operations type, Label l) {
        this.store = store;
        this.op1 = op1;
        this.op2 = op2;
        this.type = type;
        this.l = l;
    }
    
    public Quadruple(Label l) {
        this.l = l;
        this.type = Operations.LABEL;
    }
    
    public String getStore() {
        return store;
    }

    public String getOp1() {
        return op1;
    }

    public String getOp2() {
        return op2;
    }

    public Operations getType() {
        return type;
    }
    
    public Label getLabel() {
        return l;
    }
}
