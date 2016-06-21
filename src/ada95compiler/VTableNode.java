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
//no param -#0
// IN      - #1
// OUT     - #2
// INOUT   - #3

public class VTableNode extends SymbolTableNode{
    private Type type;
    private int form;
    private int direction;
    private String current_reg;
    private String finalcode_direction;
    
    public static final int PARAM = 0;
    public static final int IN = 1;
    public static final int OUT = 2;
    public static final int INOUT = 3;
    
    public VTableNode(Type type, int form, int direction, String Id, String Scope) {
        super(Id, Scope);
        this.type = type;
        this.form = form;
        this.direction = direction;
        this.current_reg="";
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

    

    public int getForm() {
        return form;
    }

    public void setForm(int form) {
        this.form = form;
    }
    
    public boolean isParam(){
      if(form==0){
       return false;
      }else{
      return true;
      }
    }
    
    @Override
    public String toString() {
        return this.type.toString();
    }

    public String getCurrent_reg() {
        return current_reg;
    }

    public void setCurrent_reg(String current_reg) {
        this.current_reg = current_reg;
    }

    public String getFinalcode_direction() {
        return finalcode_direction;
    }

    public void setFinalcode_direction(String finalcode_direction) {
        this.finalcode_direction = finalcode_direction;
    }
    
}
