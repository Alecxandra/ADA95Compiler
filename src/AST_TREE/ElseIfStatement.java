/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST_TREE;

import INTERM_LANG.IntermediateForm;
import TRAVERSE_TREE.IntermediateTraverse;
import TRAVERSE_TREE.ParentTraverse;
import TRAVERSE_TREE.TypeTraverse;
import java.util.ArrayList;

/**
 *
 * @author cbanegas
 */
public class ElseIfStatement {
   public Expression expre;
   public Statements statements;
   private int line;
   private int column;

    public ElseIfStatement(Expression expre, Statements statements) {
        this.expre = expre;
        this.statements = statements;
    }
    
    public ElseIfStatement() {
    }
   
    public void accept(ParentTraverse pt) {
        pt.traverse(this);
    }
    public void accept (TypeTraverse tt){
      tt.traverse(this);
    }
    
    public IntermediateForm accept(IntermediateTraverse it){
        return it.traverse(this);
    }
    
    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
