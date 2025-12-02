package com.ifsc.selenium_junit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CardProduto {
    private WebDriver driver;
    private By labelResp= By.className("swiper-slide-active");//Card Ativo dentro do swiper slide
    
    public CardProduto(WebDriver driver) {
        this.driver= driver;
    }
    public String getTituloResposta(){
        WebElement cardAtivo = driver.findElement(labelResp);
        WebElement tituloProduto = cardAtivo.findElement(By.tagName("h2"));
        return tituloProduto.getText();
    }
}