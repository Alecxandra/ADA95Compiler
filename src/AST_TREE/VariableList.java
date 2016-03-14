/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST_TREE;

import java.util.ArrayList;

/**
 *
 * @author alecx
 */
public class VariableList {
    private ArrayList<Identifier> list;

    public VariableList() {
     list = new ArrayList();
    }
    
     public void addE(Identifier x){
      list.add(x);
    }
    
    public Identifier elementAt(int i){
      return list.get(i);
    
    }
    
    public int size(){
    return list.size();
    }
    
    
}
