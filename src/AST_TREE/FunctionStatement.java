/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST_TREE;

import INTERM_LANG.IntermediateForm;
import TRAVERSE_TREE.IntermediateTraverse;
import TRAVERSE_TREE.ParentTraverse;
import TRAVERSE_TREE.TypeTraverse;

/**
 *
 * @author alecx
 */
public class FunctionStatement extends DeclarationStatement{
    public Identifier preid;
    public Identifier postid;
    public ParamsList params;
    public Declarations presta;
    public Statements poststa;
    public Type       type;

    public FunctionStatement(Identifier preid, Identifier postid, ParamsList params, Declarations presta, Statements poststa, Type type) {
        this.preid = preid;
        this.postid = postid;
        this.params = params;
        this.presta = presta;
        this.poststa = poststa;
        this.type= type;
    }

    public FunctionStatement(Identifier preid, Identifier postid, Declarations presta, Statements poststa, Type type) {
        this.preid = preid;
        this.postid = postid;
        this.presta = presta;
        this.poststa = poststa;
        this.type= type;
        this.params = new ParamsList();
    }
    
    public FunctionStatement() {
    }
    
    @Override
    public void accept(ParentTraverse pt) {
        pt.traverse(this);
    }
    
     public Type accept (TypeTraverse tt){
      return tt.traverse(this);
    }
    public IntermediateForm accept(IntermediateTraverse it){
        return it.traverse(this);
    }
}
