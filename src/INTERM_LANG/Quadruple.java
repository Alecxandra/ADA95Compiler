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
    public enum Operations {ADD, MIN, MUL, DIV, IF_GEQ, IF_LEQ, IF_GT, IF_LT, IF_NEQ, IF_EQ, ASSIGN, PARAM, CALL, GOTO, PRINT, READ, LABEL, EXIT, VOID_RET }
    private String store;
    private String op1;
    private String op2;
    private Operations type;
    private Label l;
    
    
}
