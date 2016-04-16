/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ada95compiler;

import AST_TREE.BooleanType;
import AST_TREE.FloatType;
import AST_TREE.IntegerType;
import AST_TREE.StringType;
import AST_TREE.Type;
import java.util.ArrayList;

/**
 *
 * @author alecx
 */
public class SymbolTable {
    
    private ArrayList<SymbolTableNode> symbolList;
    /* Bytes */
    public static int integer_size = 4;
    public static int float_size = 8;
    public static int boolean_size = 1;
    public static int string_size = 4;

    public SymbolTable() {
        symbolList= new ArrayList();
    }
    
    
    public static int SizeOf(Type type){
      if( type instanceof IntegerType){
         return integer_size;
      }else if ( type instanceof FloatType){
         return float_size;
      }else if( type instanceof BooleanType){
         return boolean_size;
      }else if( type instanceof StringType){
         return string_size;
      }else{
       return -1;
      }   
      
    }
    
    public SymbolTableNode findSymbol(String id){
        for (int i = 0; i < symbolList.size(); i++) {
            if( symbolList.get(i).getId().equals(id)){
              return symbolList.get(i);
            }
        }
        return null;
    }
    
    public boolean addSymbol(SymbolTableNode symbol){
       if(findSymbol(symbol.getId()) != null){
         return false;
       }
       symbolList.add(symbol);
       return true;
    }
    
    
    
}