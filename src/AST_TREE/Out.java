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
public class Out extends ParamsModifier{
     public VariableList list;
    public Type type;

    public Out(VariableList list, Type type) {
        this.list = list;
        this.type = type;
    }
    
    @Override
    public void accept(ParentTraverse pt) {
       pt.traverse(this);
    }
}