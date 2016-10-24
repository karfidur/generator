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
import java.util.Scanner;

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
        Scanner in = new Scanner(System.in);
        Random rand = new Random();
        PrintWriter zapist1 = new PrintWriter("t1.sql");
        PrintWriter zapist2 = new PrintWriter("t2.sql");

        BufferedReader br = new BufferedReader(new FileReader("imionam.txt"));
        while ((textLine = br.readLine()) != null) {
            listaImionM.add(textLine);
        }
        BufferedReader br2 = new BufferedReader(new FileReader("nazwiska.txt"));
        while ((textLine = br2.readLine()) != null) {
            listaNazwisk.add(textLine);
        }
        BufferedReader br3 = new BufferedReader(new FileReader("imionaf.txt"));
        while ((textLine = br3.readLine()) != null) {
            listaImionF.add(textLine);
        }
        BufferedReader br4 = new BufferedReader(new FileReader("spec.txt"));
        while ((textLine = br4.readLine()) != null) {
            spec.add(textLine);
        }

        System.out.println("Podaj rok rozpoczecia działalności");
        int beginDate = in.nextInt();
        System.out.println("Podaj rok t1");
        int t1 = in.nextInt();
        System.out.println("Podaj rok t2");
        int t2 = in.nextInt();
        System.out.println("Podaj liczbe lekarzy");
        int lekarzeCount = in.nextInt();
        int t0t1 = (int) (((float) (t1 - beginDate) / (float) (t2 - beginDate)) * lekarzeCount);

        for (int i = 0; i < lekarzeCount; i++) {
            lekarze.add(new Lekarz());
            sex = rand.nextInt(2);
            if (sex == 0) {
                lekarze.get(i).setImie(listaImionM, sex);
            } else {
                lekarze.get(i).setImie(listaImionF, sex);
            }
            lekarze.get(i).setNazwisko(listaNazwisk);
            lekarze.get(i).setSpecjalizacja(spec);
            lekarze.get(i).setId(i + 1);
            int zwolniony;
            zwolniony = rand.nextInt(10);
            if (i <= t0t1) {
                lekarze.get(i).setDataZatrudnienia(beginDate, t1);
                if (zwolniony == 1) {
                    lekarze.get(i).setDataZwolnienia(t1);
                }
            } else {
                lekarze.get(i).setDataZatrudnienia(t1, t2);
                if (zwolniony == 1) {
                    lekarze.get(i).setDataZwolnienia(t2);
                }
            }

            if (i == t0t1) {
                for (Lekarz l : lekarze) {
                    l.wypisz(zapist1);
                }
                zapist1.close();
            }
            int losowanie;
            losowanie = rand.nextInt(5);
            if (losowanie == 1) {
                losowanie = rand.nextInt(lekarze.size());
                lekarze.get(losowanie).setNazwisko(listaNazwisk);
            }

        }
        for (Lekarz l : lekarze) {
            l.wypisz(zapist2);
        }
        zapist2.close();

        // TODO code application logic here
    }

}
