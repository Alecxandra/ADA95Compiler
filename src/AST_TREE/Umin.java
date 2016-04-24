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
public class Umin extends ArithmeticExpression{
    
    public Expression exp;

    public Umin(Expression exp) {
        this.exp = exp;
    }

    @Override
    public void accept(ParentTraverse pt) {
        pt.traverse(this);
    }
    
     public Type accept (TypeTraverse tt){
      return tt.traverse(this);
    }
}
