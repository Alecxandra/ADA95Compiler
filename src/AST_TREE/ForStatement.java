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

/**
 *
 * @author cbanegas
 */
public class ForStatement extends Statement {
    public Identifier id;
    public Expression start;
    public Expression end;
    public Statements sta;
    
    public ForStatement(Identifier id, Expression int1, Expression int2, Statements sta) {
        this.id = id;
        this.start = int1;
        this.end = int2;
        this.sta = sta;
    }
    
    public ForStatement() {
    }

    @Override
    public void accept(ParentTraverse pt) {
        pt.traverse(this);
    }
    
     public Type accept (TypeTraverse tt){
      return tt.traverse(this);
    }
    public IntermediateForm accept(IntermediateTraverse it){
        return it.traverse(this);
    }
}
