/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ada95compiler;

import AST_TREE.ProgramInit;
import AST_TREE.Statements;
import FINAL_CODE.FinalCodeBuilder;
import INTERM_LANG.IntermediateStatement;
import TRAVERSE_TREE.IntermediateCode;
import TRAVERSE_TREE.SemanticAnalysis;
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
            //Analisis Lexico y Sintactico 
            File file = new File("./archivo.adb");
            AdaLexer lexer = new AdaLexer(new InputStreamReader(new FileInputStream(file)));
            ParserAda parse= new ParserAda(lexer);
            parse.parse();
            ProgramInit programa= parse.getProgram();
            //Analisis semantico
            SymbolTable symboltable= new SymbolTable();
            SemanticAnalysis semantic = new SemanticAnalysis(symboltable);
            programa.accept(semantic);
            if(!semantic.haserror()){
                File intermediateCodeFile = new File(file.getAbsolutePath().replace(".adb", "") + ".o");
                IntermediateCode ic = new IntermediateCode(intermediateCodeFile, semantic);
                IntermediateStatement interForm = (IntermediateStatement) ic.traverse(programa);
                ic.createFile(interForm.buildIntermediateCode());
                File finalCodeFile = new File(file.getAbsoluteFile().getAbsolutePath().replace(".adb", "") + ".s");
                FinalCodeBuilder fcb = new FinalCodeBuilder(semantic, finalCodeFile, interForm, ic.getStringsTable());
                fcb.writeFinalCode(fcb.buildFinalCode());   
            }
            
            
        } catch (Exception ex) {
            
        }
    }
    
}
