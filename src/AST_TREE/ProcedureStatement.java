/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST_TREE;

import TRAVERSE_TREE.ParentTraverse;

/**
 *
 * @author alecx
 */
public class ProcedureStatement extends Statement{
    public Identifier preid;
    public Identifier postid;
    public ParamsList list;
    public Statements presta;
    public Statements poststa;

    public ProcedureStatement(Identifier id, ParamsList list, Statements presta, Statements poststa,Identifier post) {
        this.preid = id;
        this.list = list;
        this.presta = presta;
        this.poststa = poststa;
        this.postid= post;
    }

    public ProcedureStatement(Identifier id, Statements presta, Statements poststa,Identifier post) {
        this.preid = id;
        this.presta = presta;
        this.poststa = poststa;
        this.postid= post;
    }
    
    
    @Override
    public void accept(ParentTraverse pt) {
        pt.traverse(this);
    }
    
}
