/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author MSI
 */
public class Choroba {
    
    private Integer id;
    private String nazwa;
    
     public void setId(int id) {
        this.id = id;
    }
     
     public void setNazwa(List<String> nazwy) {
        this.nazwa = nazwy.get(this.id);
    }
     
     public void wypisz(PrintWriter zapis){
         zapis.println("insert into Choroba (\"ID\", \"Nazwa\") values ('" + this.id + "', '" + this.nazwa + "');" );
     }
    
}
