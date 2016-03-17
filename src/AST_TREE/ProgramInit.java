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
public class ProgramInit {
    public Identifier preid;
    public Declarations declarations;
    public Statements stas;
    public Identifier postid;

    public ProgramInit(Identifier preid, Declarations declarations, Statements stas, Identifier postid) {
        this.preid = preid;
        this.declarations = declarations;
        this.stas = stas;
        this.postid = postid;
    }
    
    public void accept(ParentTraverse pt) {
        pt.traverse(this);
    }
    
}
