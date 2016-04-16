/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST_TREE;

import TRAVERSE_TREE.ParentTraverse;
import TRAVERSE_TREE.TypeTraverse;

/**
 *
 * @author cbanegas
 */
public class IfStatementError extends IfStatement{
    
    public int column;
    public int line;
    public String message;
    public Statements sta;

    public IfStatementError(int column, int line, String message, Statements sta) {
        this.column = column;
        this.line = line;
        this.message = message;
        this.sta1=sta;
    }
    
     public IfStatementError(int column, int line, String message, Statements sta1, ElsifStatements sta) {
        this.column = column;
        this.line = line;
        this.message = message;
        this.sta1=sta1;
        this.esta=sta;
    }
     
     public IfStatementError(int column, int line, String message, Statements sta1, Statements sta2) {
        this.column = column;
        this.line = line;
        this.message = message;
        this.sta1=sta1;
        this.sta2=sta2;
    } 
     
      public IfStatementError(int column, int line, String message, Statements sta1,ElsifStatements esta, Statements sta2) {
        this.column = column;
        this.line = line;
        this.message = message;
        this.sta1=sta1;
        this.sta2=sta2;
        this.esta= esta;
    } 
    
    @Override
    public void accept(ParentTraverse pt) {
        pt.traverse(this);
    }
     public void accept (TypeTraverse tt){
      tt.traverse(this);
    }
}
