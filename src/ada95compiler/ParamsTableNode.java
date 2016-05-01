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
public class ParamsTableNode extends VTableNode{

    public ParamsTableNode(Type type, int form, int direction, String Id, String Scope) {
        super(type, form, direction, Id, Scope);
    }
    
}
