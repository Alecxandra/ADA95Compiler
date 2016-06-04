/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST_TREE;

import INTERM_LANG.IntermediateForm;
import TRAVERSE_TREE.IntermediateTraverse;
import TRAVERSE_TREE.ParentTraverse;
import TRAVERSE_TREE.TypeTraverse;

/**
 *
 * @author alecx
 */
public class ErrorType extends Type{

    @Override
    public void accept(ParentTraverse pt) {
        pt.traverse(this);
    }

    @Override
    public Type accept(TypeTraverse tt) {
        return tt.traverse(this);
    }
    @Override
    public boolean equals(Object o) {
        return o instanceof ErrorType;
    }
    
    @Override
    public String toString() {
        return this.getClass().getSimpleName().replaceAll("Type", "");
    }

    @Override
    public IntermediateForm accept(IntermediateTraverse it){
        return it.traverse(this);
    }
}
