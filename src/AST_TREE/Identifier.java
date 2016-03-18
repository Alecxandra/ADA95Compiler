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
public class Identifier extends Expression{
    public String id;

    public Identifier(String id) {
        this.id = id;
    }
     
    public Identifier() {
    }
     
    @Override
    public void accept(ParentTraverse pt) {
        pt.traverse(this);
    }
    
}