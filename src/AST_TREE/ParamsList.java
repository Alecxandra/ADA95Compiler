/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST_TREE;

import TRAVERSE_TREE.ParentTraverse;
import TRAVERSE_TREE.TypeTraverse;
import java.util.ArrayList;

/**
 *
 * @author alecx
 */
public class ParamsList {
    private ArrayList<ParamsModifier> list;

    public ParamsList() {
        list= new ArrayList();
    }
    
    
    
    public void addE(ParamsModifier x){
      list.add(x);
    }
    
    public ParamsModifier elementAt(int i){
      return list.get(i);
    
    }
    
    public int size(){
        return list.size();
    }
    public void accept(ParentTraverse pt) {
       pt.traverse(this);
    }
     public void accept (TypeTraverse tt){
      tt.traverse(this);
    }
}
