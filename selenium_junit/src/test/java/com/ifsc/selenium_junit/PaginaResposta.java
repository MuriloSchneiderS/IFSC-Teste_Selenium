package com.ifsc.selenium_junit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PaginaResposta {
    private WebDriver driver;
    private By labelResp= By.className("Text_text__D8yqX Text_size-h49__TD6VW Text_size-h28-mobile__p1MpK Text_weight-bold__CBWtB Text_color-greyscale-shadow__RZoEL spacing_noMargin__F5u9R");
    
    public PaginaResposta(WebDriver driver) {
        this.driver= driver;
    }
    public String getLabelRespostas(){
        WebElement labelSearch = driver.findElement(labelResp);
        return labelSearch.getText();
    }
}