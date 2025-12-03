package com.ifsc.selenium_junit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CardSacola {
    private WebDriver driver;
    private String nome;
    private double preco;
    private int quantidade;
    
    public CardSacola(WebDriver driver, String nome, float preco, int quantidade) {
        this.driver = driver;
        this.nome=nome;
        this.preco=preco;
        this.quantidade=quantidade;
    }

    public String getNome() {
        return nome;
    }
    public double getPreco() {
        return preco;
    }
    public int getQuantidade() {
        return quantidade;
    }
}
