/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


/**
 *
 * @author MSI
 */
public class Lekarz {
    private Integer id;
    private String specjalizacja;
    private String imie;
    private String nazwisko;
    private Date dataZatrudnienia;
    private Date dataZwolnienia;
    
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
    
    
    public void wypisz(){
    System.out.println(this.id + " " + this.imie + " " + this.nazwisko + " Spec " + this.specjalizacja); 
    }
    
    
    
}
    
