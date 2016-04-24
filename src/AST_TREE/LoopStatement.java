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
public class LoopStatement extends Statement {
    public Statements sta;

    public LoopStatement(Statements sta) {
        this.sta = sta;
    }
    
    public LoopStatement() {
    }

    @Override
    public void accept(ParentTraverse pt) {
        pt.traverse(this);
    }
     public Type accept (TypeTraverse tt){
      return tt.traverse(this);
    }
}
