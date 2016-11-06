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
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        List<String> listaImionM = new ArrayList<>();
        List<String> listaImionF = new ArrayList<>();
        List<String> listaNazwisk = new ArrayList<>();
        List<String> spec = new ArrayList<>();
        List<String> listaleki = new ArrayList<>();
        List<String> listachoroby = new ArrayList<>();
        List<Lekarz> lekarze = new ArrayList<>();
        List<Pacjent> pacjenci = new ArrayList<>();
        List<Pielegniarka> pielegniarki = new ArrayList<>();
        List<Lek> leki = new ArrayList<>();
        List<Choroba> choroby = new ArrayList<>();
        List<Pobyt> pobyty = new ArrayList<>();
       // List<Pracownik> pracownicy = new ArrayList<>();
        String textLine;
        int sex;
        int t1counter = 0;
        int t2counter = 0;
        int t1lekarz = 0;
        int t1piele = 0;
        int t1lekarzcounter = 0;
        Scanner in = new Scanner(System.in);
        Random rand = new Random();
        PrintWriter zapist1 = new PrintWriter("t1.sql");
        PrintWriter zapist2 = new PrintWriter("t2.sql");
        PrintWriter zapis3 = new PrintWriter("chorobypobytt1.bulk");
        PrintWriter zapis4 = new PrintWriter("chorobypobytt2.bulk");
        PrintWriter zapis5 = new PrintWriter("zleceniepodaniat1.bulk");
        PrintWriter zapis6 = new PrintWriter("zleceniepodaniat2.bulk");
        PrintWriter zapis7 = new PrintWriter("dawkapodaniat1.bulk");
        PrintWriter zapis8 = new PrintWriter("dawkapodaniat2.bulk");
        PrintWriter zapis9 = new PrintWriter("podaniet1.bulk");
        PrintWriter zapis10 = new PrintWriter("podaniet2.bulk");
        
        
        

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

        BufferedReader br5 = new BufferedReader(new FileReader("leki.txt"));
        while ((textLine = br5.readLine()) != null) {
            listaleki.add(textLine);
        }
        
         BufferedReader br6 = new BufferedReader(new FileReader("choroby.txt"));
        while ((textLine = br6.readLine()) != null) {
            listachoroby.add(textLine);
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
        
          for (int i = 0; i < listachoroby.size() - 1; i++) {
            choroby.add(new Choroba());
            {
                choroby.get(i).setId(choroby.size());
                choroby.get(i).setNazwa(listachoroby);
            }
         }

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
                    t1lekarz++;
                    l.wypisz(zapist1);
                    l.setWpisany();
                   // pracownicy.add(new Pracownik(pracownicy.size() + 1,l.getImie(),l.getNaziwsko(),l.getDataZa(),l.getDataZw(),l.getEdit()));

                }
                /*for (Pracownik p : pracownicy)
                 {
                 p.wypisz(zapist1);
                 }*/

            }
            if (i > t0t1) {
                int losowanie;
                losowanie = rand.nextInt(lekarzeCount/10);
                if (losowanie == 1) {
                    losowanie = rand.nextInt(t0t1);
                    lekarze.get(losowanie).setEdit();
                    lekarze.get(losowanie).setNazwisko(listaNazwisk);
                }

            }
            

        }
        
        System.out.println("Podaj liczbe pielegniarek");
        int pielegniarkiCount = in.nextInt();
        t0t1 = (int) (((float) (t1 - beginDate) / (float) (t2 - beginDate)) * pielegniarkiCount);
        for (int i = 0; i < pielegniarkiCount; i++) {
            pielegniarki.add(new Pielegniarka());
            pielegniarki.get(i).setImie(listaImionF, 1);
            pielegniarki.get(i).setNazwisko(listaNazwisk);
            pielegniarki.get(i).setId(i + 1);
            int zwolniony;
            zwolniony = rand.nextInt(10);
            if (i <= t0t1) {
                pielegniarki.get(i).setDataZatrudnienia(beginDate, t1);
                if (zwolniony == 1) {
                    pielegniarki.get(i).setDataZwolnienia(t1);
                }
            } else {
                pielegniarki.get(i).setDataZatrudnienia(t1, t2);
                if (zwolniony == 1) {
                    pielegniarki.get(i).setDataZwolnienia(t2);
                }
            }

            if (i == t0t1) {
                for (Pielegniarka p : pielegniarki) {
                    t1piele++;
                    p.wypisz(zapist1);
                    p.setWpisany();
                }

            }
            if (i > t0t1) {
                int losowanie;
                losowanie = rand.nextInt(10);
                if (losowanie == 1) {
                    losowanie = rand.nextInt(t0t1);
                    pielegniarki.get(losowanie).setEdit();
                    pielegniarki.get(losowanie).setNazwisko(listaNazwisk);
                }

            }

        }
        
    for (int i = 0; i < listaleki.size() - 1; i++) {
            leki.add(new Lek());
            {
                leki.get(i).setId(leki.size());
                leki.get(i).setNazwa(listaleki);
            }
    }
        
        System.out.println("Podaj liczbe pacjentow");
        int pacjenciCount = in.nextInt();
        t0t1 = (int) (((float) (t1 - beginDate) / (float) (t2 - beginDate)) * pacjenciCount);
        for (int i = 0; i < pacjenciCount; i++) {
            pacjenci.add(new Pacjent());
            sex = rand.nextInt(2);
            if (sex == 0) {
                pacjenci.get(i).setImie(listaImionM, sex);
            } else {
                pacjenci.get(i).setImie(listaImionF, sex);
            }
            pacjenci.get(i).setNazwisko(listaNazwisk);
             if (i <= t0t1)
            pacjenci.get(i).setDataUrodzenia(beginDate-100, t1);
             else 
            pacjenci.get(i).setDataUrodzenia(beginDate-100, t2);
            pacjenci.get(i).setPesel();
           

            if (i == t0t1) {
                for (Pacjent p : pacjenci) {
                    p.wypisz(zapist1);
                    p.setWpisany();
                }
             for (int t = 0; t<pacjenci.size()*2; t++)
             {
                pobyty.add(new Pobyt());
                pobyty.get(t).setId(pobyty.size());
                int los = rand.nextInt(pacjenci.size());
                long parsed = Long.valueOf(pacjenci.get(los).getPesel()).longValue();
                pobyty.get(t).setPacjentId(parsed);
                pobyty.get(t).setDataPrzyjecia(beginDate,t1);
                pobyty.get(t).setDataWypisania(t1);
                
             }
             for (Pobyt p : pobyty){
                 p.wypisz(zapist1);
                 p.setWpisany();
             }
             
             
             
             
                for (int z = 0; z < pobyty.size() * 2; z++) {
                    t1counter++;
                    zapis3.println(z + 1 + "|" + (rand.nextInt(choroby.size()) + 1) + "|" + (rand.nextInt(pobyty.size()) + 1));
                }
                
                 for (int z = 0; z < pobyty.size() * 2; z++) {
                     t1lekarzcounter++;
                    zapis5.println(z + 1 + "|" + (rand.nextInt(pobyty.size()) + 1) + "|" + (rand.nextInt(t1lekarz) + 1));
                    zapis7.println(z + 1 + "|" + (rand.nextInt(pobyty.size()*2) + 1) + "|" + (rand.nextInt(leki.size()) + 1) + "|" + (rand.nextInt(10)));
                    zapis9.println(z + 1 + "|" + (rand.nextInt(pobyty.size()) + 1) + "|" + (rand.nextInt(t1piele) + 1));
                }


            }
            

        }
        
        
            
     
         
        
        for (Lek l : leki) {
            l.wypisz(zapist1);
        }
        
        for (Choroba c : choroby) {
            c.wypisz(zapist1);
        }

        //pracownicy.clear();
        for (Lekarz l : lekarze) {
            if (l.getEdit() == 1 || l.getWpisany() == 0)
            l.wypisz(zapist2);
            //pracownicy.add(new Pracownik(pracownicy.size() + 1,l.getImie(),l.getNaziwsko(),l.getDataZa(),l.getDataZw(),l.getEdit()));

        }
        
        /*for (Pracownik p : pracownicy)
         {
         p.wypisz(zapist2);
         }*/
        for (Pielegniarka p : pielegniarki) {
            if (p.getEdit() == 1 || p.getWpisany() == 0)
            p.wypisz(zapist2);
        }
       

        
        for (Pacjent p : pacjenci) {
            if (p.getWpisany() == 0)
            p.wypisz(zapist2);
        }
        
          for (int t = pobyty.size(); t<pacjenci.size()*2; t++)
             {
                t2counter++;
                pobyty.add(new Pobyt());
                pobyty.get(t).setId(pobyty.size());
                pobyty.get(t).setPacjentId(rand.nextInt(pacjenci.size()) + 1);
                pobyty.get(t).setDataPrzyjecia(t1, t2);
                pobyty.get(t).setDataWypisania(t2);
                
             }
        for (Pobyt p : pobyty) {
            if (p.getWpisany() == 0)
            p.wypisz(zapist2);
        }
        for (int z = t1lekarzcounter; z < pobyty.size() * 2; z++) {
                    zapis6.println(z + 1 + "|" + ((rand.nextInt(t2counter)) + (pobyty.size()-t2counter)) + "|" + (rand.nextInt(lekarze.size()) + 1));
                    zapis8.println(z + 1 + "|" + ((rand.nextInt(pobyty.size() * 2 - t1lekarzcounter)) + t1lekarzcounter) + "|" + (rand.nextInt(leki.size()) + 1) + "|" + (rand.nextInt(10)));
                    zapis10.println(z + 1 + "|" + ((rand.nextInt(t2counter)) + (pobyty.size()-t2counter)) + "|" + (rand.nextInt(pielegniarki.size()) + 1));
                }
        
         for (int z = t1counter; z < pobyty.size() * 2; z++) {
                    zapis4.println(z + 1 + "|" + (rand.nextInt(choroby.size()) + 1) + "|" + ((rand.nextInt(t2counter)) + (pobyty.size()-t2counter)));
                }
        zapist1.close();
        zapist2.close();
        zapis3.close();
        zapis4.close();
        zapis5.close();
        zapis6.close();
        zapis7.close();
        zapis8.close();
        zapis9.close();
        zapis10.close();

        // TODO code application logic here
    }

}
