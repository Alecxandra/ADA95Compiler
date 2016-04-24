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
public class Min extends ArithmeticExpression{
    public Expression exp1;
    public Expression exp2;

    public Min(Expression exp1, Expression exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;
    }
    
    
    @Override
    public void accept(ParentTraverse pt) {
       pt.traverse(this);
    }
     public Type accept (TypeTraverse tt){
      return tt.traverse(this);
    }
}
