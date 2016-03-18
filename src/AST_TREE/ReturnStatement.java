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
public class ReturnStatement extends Statement{

    public Expression expre;

    public ReturnStatement(Expression expre) {
        this.expre = expre;
    }
    
    public ReturnStatement() {
    }
    
    @Override
    public void accept(ParentTraverse pt) {
       pt.traverse(this);
    }
    
}