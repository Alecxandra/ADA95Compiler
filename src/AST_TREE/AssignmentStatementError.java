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
public class AssignmentStatementError extends AssignmentStatement{
    public int column;
    public int line;
    public String message;

    public AssignmentStatementError(int column, int line, String message) {
        this.column = column;
        this.line = line;
        this.message = message;
    }
    
    @Override
    public void accept(ParentTraverse pt) {
        pt.traverse(this);
    }
     public Type accept (TypeTraverse tt){
      return tt.traverse(this);
    }
    
}
