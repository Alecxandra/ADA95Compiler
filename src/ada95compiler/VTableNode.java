/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ada95compiler;

import AST_TREE.Type;

/**
 *
 * @author alecx
 */

// IN     - #1
// OUT    - #2
// INOUT  - #3

public class VTableNode extends SymbolTableNode{
    private Type type; 
    private boolean Param;
    private int form;
    private int direction;

    public VTableNode(Type type, boolean Param, int form, int direction, String Id) {
        super(Id);
        this.type = type;
        this.Param = Param;
        this.form = form;
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isParam() {
        return Param;
    }

    public void setParam(boolean Param) {
        this.Param = Param;
    }

    public int getForm() {
        return form;
    }

    public void setForm(int form) {
        this.form = form;
    }
    
}
