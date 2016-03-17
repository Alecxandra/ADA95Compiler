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
public class AssignmentStatement extends Statement{
   public Identifier id;
   public Expression expre;

    public AssignmentStatement(Identifier id, Expression expre) {
        this.id = id;
        this.expre = expre;
    }
    
    @Override
    public void accept(ParentTraverse pt) {
        pt.traverse(this);
    }
    
}
