package com.ifsc.selenium_junit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CardProduto {
    private WebDriver driver;
    private By labelCardAtivo= By.className("swiper-slide-active");//Card Ativo dentro do swiper slide
    
    public CardProduto(WebDriver driver) {
        this.driver= driver;
    }
    public String getTitulo(){
        WebElement cardAtivo = driver.findElement(labelCardAtivo);
        WebElement tituloProduto = cardAtivo.findElement(By.tagName("h2"));
        return tituloProduto.getText();
    }
    public double getPreco(){
        WebElement cardAtivo = driver.findElement(labelCardAtivo);
        WebElement precoProduto = cardAtivo.findElement(By.className("menu-card-preco"));
        return Double.parseDouble(precoProduto.getText());
    }
    public void addSacola(){
        WebElement cardAtivo = driver.findElement(labelCardAtivo);
        WebElement botaoAdd = cardAtivo.findElement(By.className("add-button"));
        botaoAdd.click();
    }
}