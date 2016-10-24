/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


/**
 *
 * @author MSI
 */
public class Lekarz {
    private Integer id;
    private String specjalizacja;
    private String imie;
    private String nazwisko;
    private LocalDate dataZatrudnienia;
    private LocalDate dataZwolnienia;
    
    public void setId(int id) {
    this.id = id;
}
    public void setImie(List<String> imiona) {
     Random rand = new Random();
     int los = rand.nextInt(imiona.size());
     this.imie = imiona.get(los);  
    }
    
     public void setNazwisko(List<String> nazwiska, int sex) {
     Random rand = new Random();
     int los = rand.nextInt(nazwiska.size());
     String randNazwisko = nazwiska.get(los);
     String sub = randNazwisko.substring(randNazwisko.length()-3);
     if (sex == 0 || !sub.equals("ski") && !sub.equals("cki") && !sub.endsWith("zki"))
     this.nazwisko = nazwiska.get(los);  
     else
     this.nazwisko = randNazwisko.substring(0,randNazwisko.length()- 1) + "a";
    }
     
     public void setSpecjalizacja (List<String> specjalizacje){
     Random rand = new Random();
     int los = rand.nextInt(specjalizacje.size());
     this.specjalizacja = specjalizacje.get(los); 
     }
     
     public void setDataZatrudnienia (int begin){
    long minDay = LocalDate.of(begin, 1, 1).toEpochDay();
    long maxDay = LocalDate.of(2015, 12, 31).toEpochDay();
    long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
     this.dataZatrudnienia = LocalDate.ofEpochDay(randomDay); 
     }
    
      public void setDataZwolnienia (){
    long minDay = LocalDate.of(this.dataZatrudnienia.getYear(),this.dataZatrudnienia.getMonth(),this.dataZatrudnienia.getDayOfMonth()).toEpochDay();
    long maxDay = LocalDate.of(2015, 12, 31).toEpochDay();
    long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
     this.dataZwolnienia = LocalDate.ofEpochDay(randomDay); 
     }
    
    public void wypisz(PrintWriter zapis){
    zapis.println(this.id + " " + this.imie + " " + this.nazwisko + " " + this.specjalizacja + " " + this.dataZatrudnienia + " " + this.dataZwolnienia); 
    }
    
    
    
}
    
