/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author MSI
 */
public class Pacjent {
    
    private String pesel;
    private String imie;
    private String nazwisko;
    private LocalDate dataUrodzenia;
    
    private Integer sex;
    private Integer wpisany = 0;
    
     public void setWpisany() {
        this.wpisany = 1;
    }
     
     public int getWpisany() {
        return this.wpisany;
    }
    
    public void setImie(List<String> imiona, int sex) {
        Random rand = new Random();
        int los = rand.nextInt(imiona.size());
        this.imie = imiona.get(los);
        this.sex = sex;
    }
    
     public void setNazwisko(List<String> nazwiska) {
        Random rand = new Random();
        int los = rand.nextInt(nazwiska.size());
        String randNazwisko = nazwiska.get(los);
        String sub = randNazwisko.substring(randNazwisko.length() - 3);
        if (this.sex == 0 || !sub.equals("ski") && !sub.equals("cki") && !sub.endsWith("zki")) {
            this.nazwisko = nazwiska.get(los);
        } else {
            this.nazwisko = randNazwisko.substring(0, randNazwisko.length() - 1) + "a";
        }
    }
     
      public void setDataUrodzenia(int begin, int end) {
        long minDay = LocalDate.of(begin, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(end, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        this.dataUrodzenia = LocalDate.ofEpochDay(randomDay);
    }
      
      public int getDataUrodzenia(){
          return this.dataUrodzenia.getYear();
      }
      
      public String getPesel(){
          return this.pesel;
      }
       public void setPesel() {
       String rok = String.valueOf(this.dataUrodzenia.getYear());
       String sub = rok.substring(2);
       String mm;
       String dd;
       Random rand = new Random();
       int last = rand.nextInt(89999) + 10000;
       if (this.dataUrodzenia.getMonthValue() < 10)
       {
           mm = "0" + this.dataUrodzenia.getMonthValue();
       }
       else
       {
           mm = String.valueOf(this.dataUrodzenia.getMonthValue());
       }
       if (this.dataUrodzenia.getDayOfMonth() < 10)
       {
           dd = "0" + this.dataUrodzenia.getDayOfMonth();
       }
       else
       {
           dd = String.valueOf(this.dataUrodzenia.getDayOfMonth());
       }
       
       this.pesel = sub + mm + dd + last;
    }
     
 public void wypisz(PrintWriter zapis){
         zapis.println("insert into Pacjent (\"Pesel\", \"Imie\", \"Nazwisko\", \"Data_urodzenia\") values ('" + this.pesel + "', '" + this.imie + "', '" + this.nazwisko + "', '" + this.dataUrodzenia+ "');" );
     }
    
}

