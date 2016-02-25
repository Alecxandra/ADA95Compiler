/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ada95compiler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * 
 */
public class ADA95Compiler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
         try {
            File file = new File("./archivo.adb");
            AdaLexer lexer = new AdaLexer(new InputStreamReader(new FileInputStream(file)));
            ParserAda parse= new ParserAda(lexer);
            parse.parse();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
}
