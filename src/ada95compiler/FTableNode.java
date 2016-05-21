/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ada95compiler;

import AST_TREE.Type;
import java.util.ArrayList;

/**
 *
 * @author alecx
 */
public class FTableNode extends SymbolTableNode{
    private Type return_type;
    private ArrayList<VTableNode> params;
    private boolean hasReturn;
    
    public FTableNode(Type return_type, String Id, String Scope) {
        super(Id,Scope);
        this.return_type = return_type;
        this.params= new ArrayList();
        this.hasReturn = false;
    }
    
    public void Add(VTableNode x){
        this.params.add(x);
    }

    public Type getReturn_type() {
        return return_type;
    }

    public void setReturn_type(Type return_type) {
        this.return_type = return_type;
    }

    public ArrayList<VTableNode> getParams() {
        return params;
    }

    public void setParams(ArrayList<VTableNode> params) {
        this.params = params;
    }

    public boolean getHasReturn() {
        return hasReturn;
    }

    public void setHasReturn(boolean hasReturn) {
        this.hasReturn = hasReturn;
    }
    
}
