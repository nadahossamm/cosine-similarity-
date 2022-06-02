/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cosinesimilarity;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import static java.util.Map.Entry.comparingByValue;
import java.util.TreeMap;

/**
 *
 * @author Nada Hossam
 */
public class Cosine {

    private HashMap<String, Integer> allWords = new HashMap<String, Integer>();
    private HashMap<String, Integer> d1 = new HashMap<String, Integer>();
    private HashMap<String, Integer> d2 = new HashMap<String, Integer>();
    private HashMap<String, Integer> d3 = new HashMap<String, Integer>();
    private HashMap<String, Integer> d4 = new HashMap<String, Integer>();

    public void readfiles(String[] files) {
        int i = 0;
        int index = 0;
        for (String fileName : files) {
            try (BufferedReader file = new BufferedReader(new FileReader(fileName))) {
                String ln;
                while ((ln = file.readLine()) != null) {
                    String[] words = ln.split("\\W+");

                    for (String word : words) {
                        word = word.toLowerCase();
                       if(word.equals(""))
                              continue;
                       else
                       {
                           allWords.put(word, 0);
                        d1.put(word, 0);
                        d2.put(word, 0);
                        d3.put(word, 0);
                        d4.put(word, 0); index++;
                       }
                           
                      
                              
                        
                      

                       
                    }
                }
            } catch (IOException e) {
                System.out.println("File " + fileName + " not found. Skip it");
            }
            i++;
        }
    }

    public void addData(String[] files) {
        try (BufferedReader file = new BufferedReader(new FileReader(files[0]))) {
            String ln;
            while ((ln = file.readLine()) != null) {
                String[] words = ln.split("\\W+");

                for (String word : words) {
                     if(word.equals(""))
                               continue;
                    word = word.toLowerCase();
                   int x=d1.get(word);
                          d1.replace(word, x, x+1);
                    //System.out.println(word);
                       
                    

                }
            }
        } catch (IOException e) {
            System.out.println("File " + files[0] + " not found. Skip it");
        }

        try (BufferedReader file = new BufferedReader(new FileReader(files[1]))) {
            String ln;
            while ((ln = file.readLine()) != null) {
                String[] words = ln.split("\\W+");

                for (String word : words) {
                    word = word.toLowerCase();
                       if(word.equals(""))
                               continue;
                          int x=d2.get(word);
                           d2.replace(word, x, x+1);
                      
                        
                      

                }
            }
        } catch (IOException e) {
            System.out.println("File " + files[1] + " not found. Skip it");
        }

        try (BufferedReader file = new BufferedReader(new FileReader(files[2]))) {
            String ln;
            while ((ln = file.readLine()) != null) {
                String[] words = ln.split("\\W+");

                for (String word : words) {
                    word = word.toLowerCase();
                       if(word.equals(""))
                               continue;
                          int x=d3.get(word);
                           d3.replace(word, x, x+1);
                      

                }
            }
        } catch (IOException e) {
            System.out.println("File " + files[2] + " not found. Skip it");
        }
        try (BufferedReader file = new BufferedReader(new FileReader(files[3]))) {
            String ln;
            while ((ln = file.readLine()) != null) {
                String[] words = ln.split("\\W+");

                for (String word : words) {
                    word = word.toLowerCase();
                            if(word.equals(""))
                               continue;
                          int x=d4.get(word);
                          int y=x+1;
                           d4.replace(word, x,y);
                      
                }
            }
        } catch (IOException e) {
            System.out.println("File " + files[3] + " not found. Skip it");
        }

    }

    public double cosine(HashMap<String, Integer> doc1, HashMap<String, Integer> doc2) {
        int product = 0;
        double dd1 = 0, dd2 = 0;
        Iterator<Entry<String, Integer>> iter1 = doc1.entrySet().iterator();
        double eq = 0;
        Iterator<Entry<String, Integer>> iter2 = doc2.entrySet().iterator();
        while (iter1.hasNext() || iter2.hasNext()) {
            Entry<String, Integer> e1 = iter1.next();
            Entry<String, Integer> e2 = iter2.next();

            product += (e1.getValue() * e2.getValue());
            //System.out.println(product);
            dd1 += Math.pow(e1.getValue(),2) ;
            dd2 +=  Math.pow(e2.getValue(),2);
            eq = product / (sqrt(dd1) * sqrt(dd2));

        }
        return eq;

    }
        Map<String, Double> sortByValue(Map<String, Double> hm) {
        List<Map.Entry<String, Double> > list =
                new LinkedList<Map.Entry<String, Double> >(hm.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Double> >() {
            public int compare(Map.Entry<String, Double> o1,
                               Map.Entry<String, Double> o2){
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });
        Map<String, Double> temp = new LinkedHashMap<String, Double>();
        for (Map.Entry<String, Double> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
    public void run(String[] files) {
        Map<String, Double> hm = new LinkedHashMap<String, Double>();
                Map<String, Double> hm2 = new LinkedHashMap<String, Double>();

        readfiles(files);
        addData(files);
         
 
        hm.put( "Doc 1 and Doc 2",cosine(d1, d2));
       
        hm.put( "Doc 1 and Doc 3",cosine(d1, d3));
        hm.put("Doc 1 and Doc 4",cosine(d1, d4));
        hm.put("Doc 2 and Doc 3",cosine(d2, d3) );
        hm.put("Doc 2 and Doc 4",cosine(d2, d4) );
        hm.put("Doc 3 and Doc 4",cosine(d3, d4));
       hm2= sortByValue(hm);
        Iterator<Entry<String, Double>> iter2 = hm2.entrySet().iterator();
        while (iter2.hasNext()) {
            Entry<String, Double> e1 = iter2.next();
            System.out.println(e1.getKey()+ " " + e1.getValue());
        }

    }
}
