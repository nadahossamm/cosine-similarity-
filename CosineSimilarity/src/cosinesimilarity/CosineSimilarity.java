/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cosinesimilarity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Nada Hossam
 */
public class CosineSimilarity {

      

    public static void main(String[] args) {
        String[] files;
        files = new String[]{
            "F:/year4 t 2/ir/Assignments/docs/100.txt",
           "F:/year4 t 2/ir/Assignments/docs/101.txt",
            "F:/year4 t 2/ir/Assignments/docs/102.txt",
            "F:/year4 t 2/ir/Assignments/docs/103.txt",
        };
        Cosine c= new Cosine();
        c.run(files);
    }
    
}
