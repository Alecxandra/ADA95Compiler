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
 * @author alecx
 */
public class ArgumentList {
    private ArrayList<Expression> list;

    public ArgumentList() {
       this.list = new ArrayList();
    }
    
    public void addE(Expression x){
      list.add(x);
    }
    
    public Expression elementAt(int i){
      return list.get(i);
    
    }
    
    public int size(){
        return list.size();
    }
    
    public IntermediateForm accept(IntermediateTraverse it){
        return it.traverse(this);
    }
}
