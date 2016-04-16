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
public class ParamsTableNode {
    private Type type;
    private int form;

    public ParamsTableNode(Type type, int form) {
        this.type = type;
        this.form = form;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getForm() {
        return form;
    }

    public void setForm(int form) {
        this.form = form;
    }
    
    
}
