/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ada95compiler;

import AST_TREE.BooleanType;
import AST_TREE.IntegerType;
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
    private int column;
    private int line;
    private int direction;
    private String child_scope;
    
    public FTableNode(Type return_type, String Id, String Scope, String child_scope) {
        super(Id,Scope);
        this.return_type = return_type;
        this.params= new ArrayList();
        this.hasReturn = false;
        this.direction = 0;
        this.child_scope= child_scope;
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

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }
    
    public int incrementDirection(Type t){
        if(t instanceof IntegerType){
            this.direction += 4;
            return this.direction;
        }else if(t instanceof BooleanType){
            this.direction += 1;
            return this.direction;
        }else{
            return -1;
        }
    } 

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public String getChild_scope() {
        return child_scope;
    }

    public void setChild_scope(String child_scope) {
        this.child_scope = child_scope;
    }
    
}
