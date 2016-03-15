/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST_TREE;

import TRAVERSE_TREE.ParentTraverse;
import java.util.ArrayList;

/**
 *
 * @author cbanegas
 */
public class ParameterList extends Statement{
    private ArrayList<Expression> list;

    public ParameterList() {
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

    @Override
    public void accept(ParentTraverse pt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
