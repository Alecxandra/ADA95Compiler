/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST_TREE;

import TRAVERSE_TREE.ParentTraverse;
import TRAVERSE_TREE.TypeTraverse;
import java.util.Objects;

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
     public void accept (TypeTraverse tt){
      tt.traverse(this);
    }


    @Override
    public boolean equals(Object obj) {
      if ( obj instanceof Identifier){
        return this.id.equals(((Identifier)obj).id);
      
      }else{
       return false;
      }
    }
     
     
}
