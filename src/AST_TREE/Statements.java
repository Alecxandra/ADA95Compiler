/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST_TREE;

import INTERM_LANG.IntermediateForm;
import TRAVERSE_TREE.IntermediateTraverse;
import java.util.ArrayList;

/**
 *
 * @author cbanegas
 */
public class Statements {
   ArrayList<Statement> list;

   public Statements() {
      list = new ArrayList();
   }
   
   public void addE(Statement x){
      list.add(0, x);
    }
    
   public Statement elementAt(int i){
      return list.get(i);
    
   }
    
   public int size(){
       return list.size();
   }
   
   public IntermediateForm accept(IntermediateTraverse it){
        return it.traverse(this);
    }
}
