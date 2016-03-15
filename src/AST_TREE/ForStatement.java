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
    public Integer int1;
    public Integer int2;
    public Statements sta;
    
    public ForStatement(Identifier id, Integer int1, Integer int2, Statements sta) {
        this.id = id;
        this.int1 = int1;
        this.int2 = int2;
        this.sta = sta;
    }

    @Override
    public void accept(ParentTraverse pt) {
        pt.traverse(this);
    }
    
}
