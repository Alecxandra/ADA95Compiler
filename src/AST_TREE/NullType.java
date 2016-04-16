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
public class NullType extends Type{
     @Override
    public void accept(ParentTraverse pt) {
       pt.traverse(this);
    }
    
    @Override
    public boolean equals(Object o) {
        return o instanceof NullType;
    }
    
     public void accept (TypeTraverse tt){
      tt.traverse(this);
    }
}