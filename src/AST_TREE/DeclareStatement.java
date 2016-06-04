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
public class DeclareStatement extends DeclarationStatement {

    public VariableList list;
    public Type type;

    public DeclareStatement(VariableList list, Type type) {
        this.list = list;
        this.type = type;
    }
    
    public DeclareStatement() {
    }
    
    @Override
    public void accept(ParentTraverse pt) {
        pt.traverse(this);
    }
    
    @Override
     public Type accept (TypeTraverse tt){
      return tt.traverse(this);
    }
     
    public IntermediateForm accept(IntermediateTraverse it){
        return it.traverse(this);
    }
}
