package dev.walteribanez.crud.product;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;


@Entity
@Table
public class Product {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;



    @Column(unique = true)
    private String name;

    private float price;

    private LocalDate fecha;


    @Transient
    private int  antiguedad;

    public Product(String name, float price, LocalDate fecha, int antiguedad) {
        this.name = name;
        this.price = price;
        this.fecha = fecha;
        this.antiguedad = antiguedad;
    }

    public Product() {
    }

    public Product(Long id, String name,float  price, LocalDate fecha, int antiguedad) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.fecha = fecha;
        this.antiguedad = antiguedad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getAntiguedad() {
        return Period.between( this.fecha, LocalDate.now()  ).getYears();
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }



}
