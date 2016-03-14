/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST_TREE;

import TRAVERSE_TREE.ParentTraverse;

/**
 *
 * @author alecx
 */
public class Not extends LogicalExpression{
    
    public Expression exp;

    public Not(Expression exp) {
        this.exp = exp;
    }
    

    @Override
    public void accept(ParentTraverse pt) {
       pt.traverse(this);
    }
    
}
