/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST_TREE;

import TRAVERSE_TREE.ParentTraverse;
import TRAVERSE_TREE.TypeTraverse;

/**
 *
 * @author cbanegas
 */
public class IfStatement extends Statement{
    public Expression expre;
    public Statements sta1;
    public Statements sta2;
    public ElsifStatements esta;

    public IfStatement(Expression expre, Statements sta1) {
        this.expre = expre;
        this.sta1 = sta1;
    }
    
    public IfStatement(Expression expre, Statements sta1, ElsifStatements esta) {
        this.expre = expre;
        this.sta1 = sta1;
        this.esta = esta;
    }
    
    public IfStatement(Expression expre, Statements sta1, Statements sta2) {
        this.expre = expre;
        this.sta1 = sta1;
        this.sta2 = sta2;
    }

    public IfStatement(Expression expre, Statements sta1, ElsifStatements esta, Statements sta2) {
        this.expre = expre;
        this.sta1 = sta1;
        this.esta = esta;
        this.sta2 = sta2;
    }
    
    public IfStatement() {
    }
    
    @Override
    public void accept(ParentTraverse pt) {
        pt.traverse(this);
    }
     public void accept (TypeTraverse tt){
      tt.traverse(this);
    }
}
