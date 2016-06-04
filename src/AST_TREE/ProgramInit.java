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
public class ProgramInit {
    public Identifier preid;
    public Declarations declarations;
    public Statements stas;
    public Identifier postid;
    private int line;
    private int column;

    public ProgramInit(Identifier preid, Declarations declarations, Statements stas, Identifier postid) {
        this.preid = preid;
        this.declarations = declarations;
        this.stas = stas;
        this.postid = postid;
    }

    public ProgramInit() {
    }
    
    public void accept(ParentTraverse pt) {
        pt.traverse(this);
    }
     public void accept (TypeTraverse tt){
      tt.traverse(this);
    }
    public IntermediateForm accept(IntermediateTraverse it){
        return it.traverse(this);
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
    
}
