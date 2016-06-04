/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INTERM_LANG;

import java.util.ArrayList;

/**
 *
 * @author cbanegas
 */
public class IntermediateExpression extends IntermediateStatement {
    private Temporal place;
    private ArrayList<Label> t;
    private ArrayList<Label> f;
    
    public IntermediateExpression() { 
        this.t = new ArrayList();
        this.f = new ArrayList();
    }

    public Temporal getPlace() {
        return place;
    }

    public ArrayList<Label> getTrue() {
        return t;
    }

    public ArrayList<Label> getFalse() {
        return f;
    }

    public void setPlace(Temporal place) {
        this.place = place;
    }

    public void setTrue(ArrayList<Label> t) {
        this.t = t;
    }

    public void setFalse(ArrayList<Label> f) {
        this.f = f;
    }
}
