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
public abstract class BooleanExpression extends Expression{
@Override
public abstract void accept(ParentTraverse pt);
@Override
public abstract Type accept(TypeTraverse tt);
@Override
public abstract IntermediateForm accept(IntermediateTraverse it);
}
