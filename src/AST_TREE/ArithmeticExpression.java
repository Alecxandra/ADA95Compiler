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
 * @author alecx
 */
public abstract class ArithmeticExpression extends Expression{
    @Override
    public abstract void accept(ParentTraverse pt);
    public abstract Type accept(TypeTraverse tt);
}
