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
public class InOut extends ParamsModifier{
    public VariableList list;
    public Type type;

    public InOut(VariableList list, Type type) {
        this.list = list;
        this.type = type;
    }
    
    @Override
    public void accept(ParentTraverse pt) {
       pt.traverse(this);
    }
     public void accept (TypeTraverse tt){
      tt.traverse(this);
    }
}

