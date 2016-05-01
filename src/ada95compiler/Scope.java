/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ada95compiler;

/**
 *
 * @author alecx
 */
public class Scope {
    private static int count=0;
    
    public static String getNewScope(){
     return String.format("s%d", count++);
    }
}
