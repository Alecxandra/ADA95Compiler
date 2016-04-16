/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST_TREE;

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
}
