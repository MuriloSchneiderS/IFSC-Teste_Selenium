package com.ifsc.selenium_junit;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CabecalhoMenu {
    private WebDriver driver;
    private List<WebElement> opcoes_menu;
    private WebElement btnMobileMenu;
    
    public CabecalhoMenu(WebDriver driver, WebElement nav_list) {
        this.driver = driver;
        this.opcoes_menu = nav_list.findElements(By.className("nav-link"));
        this.btnMobileMenu = driver.findElement(By.className("mobile-menu-icon"));
    }
    
    public void clickMenu(){
        opcoes_menu.get(0).click();
    }
    public void clickSobre(){
        opcoes_menu.get(1).click();
    }
    public void clickContato(){
        opcoes_menu.get(2).click();
    }
    public WebElement clickAjuda(){
        opcoes_menu.get(3).click();
        return driver.findElement(By.className("pop-ajuda"));
    }
    
    public boolean checkDisplayMobileMenu(){
        return btnMobileMenu.isDisplayed();
    }
}