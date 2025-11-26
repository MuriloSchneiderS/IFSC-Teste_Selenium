package com.ifsc.selenium_junit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PaginaInicial {
    private WebDriver driver;
    private By campoBusca = By.className("SearchInput_input__NOK7W");
    
    public PaginaInicial(WebDriver driver){
        this.driver = driver;
    }
    
    public void navegaHome(){
        this.driver.get("https://www.pexels.com/pt-br/");
    }
    public PaginaResposta fazBusca(String valor){
        WebElement campoSearch = driver.findElement(campoBusca);
        campoSearch.sendKeys(valor);
        campoSearch.submit();
        return new PaginaResposta(this.driver);
    }

}