/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST_TREE;

import TRAVERSE_TREE.ParentTraverse;

/**
 *
 * @author cbanegas
 */
public class ForStatement extends Statement {
    public Identifier id;
    public Expression start;
    public Expression end;
    public Statements sta;
    
    public ForStatement(Identifier id, Expression int1, Expression int2, Statements sta) {
        this.id = id;
        this.start = int1;
        this.end = int2;
        this.sta = sta;
    }

    @Override
    public void accept(ParentTraverse pt) {
        pt.traverse(this);
    }
    
}
