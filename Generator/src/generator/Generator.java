/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 *
 * @author MSI
 */
public class Generator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        List<String> listaImionM = new ArrayList<String>();
        List<String> listaImionF = new ArrayList<String>();
        List<String> listaNazwisk = new ArrayList<String>();
        List<String> spec = new ArrayList<String>();
        List<Lekarz> lekarze = new ArrayList<Lekarz>();
        
        String textLine;
        int sex;
        Random rand = new Random();
      PrintWriter zapis = new PrintWriter("out.sql");
  
        BufferedReader br = new BufferedReader (new FileReader("imionam.txt"));
        while ((textLine = br.readLine()) != null) {
            listaImionM.add(textLine);
        }
        BufferedReader br2 = new BufferedReader (new FileReader("nazwiska.txt"));
        while ((textLine = br2.readLine()) != null) {
            listaNazwisk.add(textLine);
        }
         BufferedReader br3 = new BufferedReader (new FileReader("imionaf.txt"));
        while ((textLine = br3.readLine()) != null) {
            listaImionF.add(textLine);
        }
        BufferedReader br4 = new BufferedReader (new FileReader("spec.txt"));
        while ((textLine = br4.readLine()) != null) {
            spec.add(textLine);
        }
        
        for (int i = 0; i<100;i++){
        lekarze.add(new Lekarz());
        sex = rand.nextInt(2);
        if (sex == 0)
        lekarze.get(i).setImie(listaImionM); 
        else
        lekarze.get(i).setImie(listaImionF); 
        
        lekarze.get(i).setNazwisko(listaNazwisk, sex);
        lekarze.get(i).setSpecjalizacja(spec);
        lekarze.get(i).setId(i+1);
        lekarze.get(i).setDataZatrudnienia();
        }
        
  
        for (Lekarz l : lekarze) l.wypisz(zapis);
        zapis.close();
        
        // TODO code application logic here
    }
    
}
