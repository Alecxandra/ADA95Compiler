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
public class IfStatementError extends IfStatement{
    
    public int column;
    public int line;
    public String message;

    public IfStatementError(int column, int line, String message) {
        this.column = column;
        this.line = line;
        this.message = message;
    }
    
    @Override
    public void accept(ParentTraverse pt) {
        pt.traverse(this);
    }
    
}