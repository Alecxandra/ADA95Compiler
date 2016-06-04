/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INTERM_LANG;

import java.util.Vector;

/**
 *
 * @author cbanegas
 */
public class QuadrupleList {
    private Vector<Quadruple> list;

    public QuadrupleList() {
        this.list = new Vector();
    }
    
    public void add(Quadruple n) {
        list.add(n);
    }
    
    public Quadruple elementAt(int i) {
        return list.get(i);
    }
    
    public int size() {
        return list.size();
    }
    
    public QuadrupleList merge(QuadrupleList other) {
        Vector<Quadruple> neoList = new Vector();
        neoList.addAll(this.list);
        neoList.addAll(other.list);
        QuadrupleList neo = new QuadrupleList();
        neo.list = neoList;
        return neo;
    }
}
