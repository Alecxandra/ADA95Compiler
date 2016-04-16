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
public class FunctionCall extends Expression{
    public Identifier id;
    public ArgumentList args;

    public FunctionCall(Identifier id, ArgumentList args) {
        this.id = id;
        this.args = args;
    }

    public FunctionCall(Identifier id) {
        this.id = id;
    }
    
    public FunctionCall() {
    }
    

    @Override
    public void accept(ParentTraverse pt) {
        pt.traverse(this);
    }
    
     public void accept (TypeTraverse tt){
      tt.traverse(this);
    }
}
