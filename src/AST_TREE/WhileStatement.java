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
public class WhileStatement extends Statement {
    public Expression expre;
    public Statements sta;

    public WhileStatement() {
    }
    

    public WhileStatement(Expression expre, Statements sta) {
        this.expre = expre;
        this.sta = sta;
    }

    @Override
    public void accept(ParentTraverse pt) {
        pt.traverse(this);
    }
   
    @Override
     public Type accept (TypeTraverse tt){
      return tt.traverse(this);
    }
}
