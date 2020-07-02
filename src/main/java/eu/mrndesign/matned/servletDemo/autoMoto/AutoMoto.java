package eu.mrndesign.matned.servletDemo.autoMoto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;


@Data
@Builder
@Entity
@Table(name = "auto_moto")
public class AutoMoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String ownername;
    private String cartype;
    private String brand;
    private String model;
    private String mileage;
    private String color;
    private String horsepower;

    public boolean isNumeric(String str){
        try{
            int temp = Integer.parseInt(str);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

}
