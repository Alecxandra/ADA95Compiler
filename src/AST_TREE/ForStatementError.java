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
 * @author alecx
 */
public class ForStatementError extends ForStatement{
    
    public int column;
    public int line;
    public String message;

    public ForStatementError(int column, int line, String message,Statements sta) {
        this.column = column;
        this.line = line;
        this.message = message;
        this.sta= sta;
    }
    
     @Override
    public void accept(ParentTraverse pt) {
       pt.traverse(this);
    }
    
     public void accept (TypeTraverse tt){
      tt.traverse(this);
    }
}
