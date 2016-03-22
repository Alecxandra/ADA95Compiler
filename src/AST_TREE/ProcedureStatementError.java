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
public class ProcedureStatementError extends ProcedureStatement{
    public int column;
    public int line;
    public String message;

    public ProcedureStatementError(int column, int line, String message, Declarations declare, Statements sta) {
        this.column = column;
        this.line = line;
        this.message = message;
        this.presta=declare;
        this.poststa=sta;
    }
    @Override
    public void accept(ParentTraverse pt) {
       pt.traverse(this);
    }
}
