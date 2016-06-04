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
public class ElsifStatements {
 ArrayList<ElseIfStatement> list;

    public ElsifStatements() {
        list= new ArrayList();
    }
    
  public void addE(ElseIfStatement x){
      list.add(x);
    }
    
    public ElseIfStatement elementAt(int i){
      return list.get(i);
    
    }
    
    public int size(){
        return list.size();
    }
}
