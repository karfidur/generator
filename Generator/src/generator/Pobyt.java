/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author MSI
 */
public class Pobyt {
    
    private Integer id;
    private long pacjentId;
    private LocalDate dataPrzyjecia;
    private LocalDate dataWypisania;
    
    private Integer wpisany = 0;
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setPacjentId(long id) {
        this.pacjentId = id;
    }
    
    public void setWpisany() {
        this.wpisany = 1;
    }
     public void setDataPrzyjecia(int begin, int end) {
        long minDay = LocalDate.of(begin, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(end, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        this.dataPrzyjecia = LocalDate.ofEpochDay(randomDay);
    }

    public void setDataWypisania(int end) {
        long minDay = LocalDate.of(this.dataPrzyjecia.getYear(), this.dataPrzyjecia.getMonth(), this.dataPrzyjecia.getDayOfMonth()).toEpochDay();
        long maxDay = LocalDate.of(end, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        this.dataWypisania = LocalDate.ofEpochDay(randomDay);
    }
    
     public void wypisz(PrintWriter zapis){
         zapis.println("insert into Pobyt (\"ID\", \"ID_pacjenta\", \"Data_przyjecia\", \"Data_wypisania\") values ('" + this.id + "', '" + this.pacjentId + "', '" + this.dataPrzyjecia + "', '" + this.dataWypisania+ "');" );
     }
     
     public int getWpisany() {
        return this.wpisany;
    }
     
     public long getPacjentId() {
        return this.pacjentId;
    }
}
