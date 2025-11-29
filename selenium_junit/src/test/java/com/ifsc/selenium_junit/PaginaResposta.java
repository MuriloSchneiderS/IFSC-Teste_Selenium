package com.ifsc.selenium_junit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PaginaResposta {
    private WebDriver driver;
    private By labelResp= By.className("swiper-slide-active");//Card Ativo
    
    public PaginaResposta(WebDriver driver) {
        this.driver= driver;
    }
    public String getTituloResposta(){
        WebElement cardAtivo = driver.findElement(labelResp);
        WebElement nomeElement = cardAtivo.findElement(By.tagName("h2"));
        return nomeElement.getText();
    }
}