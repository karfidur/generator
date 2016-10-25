/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import java.io.PrintWriter;
import java.time.LocalDate;

/**
 *
 * @author MSI
 */
public class Pracownik {
    private Integer id;
    private String imie;
    private String nazwisko;
    private LocalDate dataZatrudnienia;
    private LocalDate dataZwolnienia;
    
    private Integer edited = 0;
    
public Pracownik(int id, String imie, String nazwisko, LocalDate dataZa, LocalDate dataZw, int edit){
    this.id = id;
    this.imie = imie;
    this.nazwisko = nazwisko;
    this.dataZatrudnienia = dataZa;
    this.dataZwolnienia = dataZw;
    this.edited = edit;
}
    
   
    
    public void wypisz(PrintWriter zapis){
    if (this.edited == 0)
    zapis.println("insert into Pracownik (\"ID\", \"Imie\", \"Nazwisko\", \"Data zatrudnienia\", \"Data zwolnienia\") values ('" + this.id + "', '" + this.imie + "', '" + this.nazwisko + "', '" + this.dataZatrudnienia + "', '" + this.dataZwolnienia +"');" );
    else
    zapis.println("update Pracownik set \"Nazwisko\" = '" + this.nazwisko + "', where \"ID\" = '" + this.id + "';");
    }
    
}
