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
public class FunctionStatement extends Statement{
    public Identifier preid;
    public Identifier postid;
    public ParamsList params;
    public Statements presta;
    public Statements poststa;
    public Type       type;

    public FunctionStatement(Identifier preid, Identifier postid, ParamsList params, Statements presta, Statements poststa, Type type) {
        this.preid = preid;
        this.postid = postid;
        this.params = params;
        this.presta = presta;
        this.poststa = poststa;
        this.type= type;
    }

    public FunctionStatement(Identifier preid, Identifier postid, Statements presta, Statements poststa, Type type) {
        this.preid = preid;
        this.postid = postid;
        this.presta = presta;
        this.poststa = poststa;
        this.type= type;
    }
    
    
    @Override
    public void accept(ParentTraverse pt) {
        pt.traverse(this);
    }
    
}
