/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ada95compiler;

/**
 *
 * @author alecx
 */
public abstract class SymbolTableNode {
    protected String Id;
    protected String Scope;

    public SymbolTableNode(String Id, String Scope) {
        this.Id = Id;
        this.Scope= Scope;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getScope() {
        return Scope;
    }

    public void setScope(String Scope) {
        this.Scope = Scope;
    }
    
    
}
