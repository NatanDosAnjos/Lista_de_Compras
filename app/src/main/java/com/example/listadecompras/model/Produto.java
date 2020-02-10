package com.example.listadecompras.model;

public class Produto {

    private String name;
    private double qtd;
    private double price;
    private double total;

    //Construtor da Classe com parametro Nome
    public Produto (String name) {
        this.name = name;
        this.qtd = 1;
    }


    //Getters e Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQtd() {
        return qtd;
    }

    public void setQtd(double qtd) {
        this.qtd = qtd;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }



    //Operations with products

    public double addOneProduct() {
        double newTotal;

        newTotal = this.total + this.price;

        return newTotal;
    }

    public double removeOneProduct() {
        double newTotal;

        newTotal = (this.total <= 0) ? 0 : (this.total - this.price);

        return newTotal;
    }

}
