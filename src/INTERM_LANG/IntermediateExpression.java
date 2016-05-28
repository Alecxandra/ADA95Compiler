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
public class IntermediateExpression {
    private Temporal place;
    private Vector<Label> t;
    private Vector<Label> f;
    
    public IntermediateExpression() { 
        this.t = new Vector();
        this.f = new Vector();
    }

    public Temporal getPlace() {
        return place;
    }

    public Vector<Label> getTrue() {
        return t;
    }

    public Vector<Label> getFalse() {
        return f;
    }

    public void setPlace(Temporal place) {
        this.place = place;
    }

    public void setTrue(Vector<Label> t) {
        this.t = t;
    }

    public void setFalse(Vector<Label> f) {
        this.f = f;
    }
}
