package com.ifsc.selenium_junit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PaginaInicial {
    private WebDriver driver;
    private By campoBusca = By.className("barra-pesquisa");
    
    public PaginaInicial(WebDriver driver){
        this.driver = driver;
    }
    
    public void navegaHome(){
        this.driver.get("http://muriloschneiders.github.io/SENAC-WEB-cantin-sCoffee/cantinscoffee/src/main/resources/templates/index.html");
    }
    public CardProduto fazBusca(String valor){//Busca apenas move o campo ativo para o item com nome contendo termo pesquisado, sem precisar de enter
        WebElement campoSearch = driver.findElement(campoBusca);
        campoSearch.sendKeys(valor);
        return new CardProduto(this.driver);
    }
    public void limpaBusca(){
        WebElement campoSearch = driver.findElement(campoBusca);
        campoSearch.clear();
    }

}