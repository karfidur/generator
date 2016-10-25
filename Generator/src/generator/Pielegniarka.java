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
public class Pielegniarka {

    private Integer id;
    private String imie;
    private String nazwisko;
    private LocalDate dataZatrudnienia;
    private LocalDate dataZwolnienia;

    private Integer sex = 1;
    private Integer edited = 0;
    private Integer wpisany = 0;

    public void setId(int id) {
        this.id = id;
    }
    
     public void setWpisany() {
        this.wpisany = 1;
    }

    public void setImie(List<String> imiona, int sex) {
        Random rand = new Random();
        int los = rand.nextInt(imiona.size());
        this.imie = imiona.get(los);

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

    public void setDataZatrudnienia(int begin, int end) {
        long minDay = LocalDate.of(begin, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(end, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        this.dataZatrudnienia = LocalDate.ofEpochDay(randomDay);
    }

    public void setDataZwolnienia(int end) {
        long minDay = LocalDate.of(this.dataZatrudnienia.getYear(), this.dataZatrudnienia.getMonth(), this.dataZatrudnienia.getDayOfMonth()).toEpochDay();
        long maxDay = LocalDate.of(end, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        this.dataZwolnienia = LocalDate.ofEpochDay(randomDay);
    }

    public void wypisz(PrintWriter zapis) {
        if (this.edited == 0) {
            if (this.dataZwolnienia != null) {
                zapis.println("insert into Pielegniarka (\"ID\", \"Imie\", \"Nazwisko\", \"Data zatrudnienia\", \"Data zwolnienia\") values ('" + this.id + "', '" + this.imie + "', '" + this.nazwisko + "', '" + this.dataZatrudnienia + "', '" + this.dataZwolnienia + "');");
            } else {
                zapis.println("insert into Pielegniarka (\"ID\", \"Imie\", \"Nazwisko\", \"Data zatrudnienia\", \"Data zwolnienia\") values ('" + this.id + "', '" + this.imie + "', '" + this.nazwisko + "', '" + this.dataZatrudnienia + "', " + this.dataZwolnienia + ");");
            }
        } else {
            zapis.println("update Pielegniarka set \"Nazwisko\" = '" + this.nazwisko + "', where \"ID\" = '" + this.id + "';");
        }
    }

    public void setEdit() {
        this.edited = 1;
    }

    public int getID() {
        return this.id;
    }

    public String getImie() {
        return this.imie;
    }

    public String getNaziwsko() {
        return this.nazwisko;
    }

    public LocalDate getDataZa() {
        return this.dataZatrudnienia;
    }

    public LocalDate getDataZw() {
        return this.dataZwolnienia;
    }

    public int getEdit() {
        return this.edited;
    }
    
     public int getWpisany() {
        return this.wpisany;
    }

}
