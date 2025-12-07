package com.ifsc.selenium_junit;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PopUpSacola {
    private WebDriver driver;
    private final By labelLista= By.tagName("ul");//lista dos itens(li) na sacola
    private final By labelFinalizar = By.className("finalizar");
    private final By labelNome = By.className("input-cliente");
    
    public PopUpSacola(WebDriver driver) {
        this.driver= driver;
    }
    
    //Preenche campo nome
    public void preencheNome(String nome){
        WebElement inputNome = driver.findElement(labelNome);
        inputNome.sendKeys(nome);
    }
    
    //Retornar lista dos itens(CardSacola)
    public List<CardSacola> getItens() {
        List<CardSacola> itens = new ArrayList<>();
        //Armazena o <ul>
        WebElement list = driver.findElement(labelLista);
        //Armazena todos os <li>
        List<WebElement> itensSacola = list.findElements(By.tagName("li"));
        
        for (WebElement item : itensSacola){//Busca as informações de cada item na sacola
            String itemNome = item.findElement(By.className("cart-title")).getText();
            double itemPreco = Double.parseDouble(item.findElement(By.className("item-preco")).getText().replace(",", "."));
            int itemQuantidade = Integer.parseInt(item.findElement(By.className("quantidade")).getText());
            
            //Cria um objeto CardSacola para cada item
            CardSacola card = new CardSacola(driver, itemNome, itemPreco, itemQuantidade);
            itens.add(card);
        }
        
        return itens;
    }
    
    //Finaliza compra
    public void creditar(){
        WebElement btnFinalizar = driver.findElement(labelFinalizar);
        btnFinalizar.click();
    }
}