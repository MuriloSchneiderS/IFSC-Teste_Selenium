package com.ifsc.selenium_junit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CardProduto{
    private WebDriver driver;
    private By labelCardAtivo= By.className("swiper-slide-active");//Card Ativo dentro do swiper slide
    private By labelCardBuscado= By.className("buscado");//Card buscado pela barra de pesquisa
    private final WebElement cardBuscado;
    
    private final String titulo;
    private final Double preco;
    private final WebElement botaoAdd;
    
    public CardProduto(WebDriver driver) {
        this.driver= driver;
        cardBuscado = this.driver.findElement(labelCardBuscado);
        
        this.titulo = cardBuscado.findElement(By.tagName("h2")).getText();
        this.preco = Double.valueOf(cardBuscado.findElement(By.className("menu-card-preco")).getText().replace(",", "."));
        this.botaoAdd = cardBuscado.findElement(By.className("add-button"));
    }
    
    public String getTitulo(){
        return titulo;
    }
    public double getPreco(){
        return preco;
    }
    public void addSacola(){
        botaoAdd.click();
    }
    
    public WebElement slider(){//slider/carrossel onde o item esta
        return cardBuscado.findElement(By.xpath("ancestor::div[contains(@class, 'slide-content')]"));
    }
}