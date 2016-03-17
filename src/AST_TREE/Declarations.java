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
public class Declarations {
    ArrayList<DeclarationStatement> list;

    public Declarations() {
        list = new ArrayList();
    }
      public void addE(DeclarationStatement x){
      list.add(x);
    }
    
    public DeclarationStatement elementAt(int i){
      return list.get(i);
    
    }
    
    public int size(){
        return list.size();
    }
    
}
