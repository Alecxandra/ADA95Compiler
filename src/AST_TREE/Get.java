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
public class Get extends IOStatement{
    public Identifier id;

    public Get(Identifier id) {
        this.id = id;
    }
    
    @Override
    public void accept(ParentTraverse pt) {
        pt.traverse(this);
    }
    
}
