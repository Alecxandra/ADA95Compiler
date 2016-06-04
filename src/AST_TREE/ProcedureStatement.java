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
public class ProcedureStatement extends DeclarationStatement{
    public Identifier preid;
    public Identifier postid;
    public ParamsList list;
    public Declarations presta;
    public Statements poststa;

    public ProcedureStatement(Identifier id, ParamsList list, Declarations presta, Statements poststa,Identifier post) {
        this.preid = id;
        this.list = list;
        this.presta = presta;
        this.poststa = poststa;
        this.postid= post;
    }

    public ProcedureStatement(Identifier id, Declarations presta, Statements poststa,Identifier post) {
        this.preid = id;
        this.presta = presta;
        this.poststa = poststa;
        this.postid= post;
        this.list = new ParamsList();
    }
    
    public ProcedureStatement() {
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
