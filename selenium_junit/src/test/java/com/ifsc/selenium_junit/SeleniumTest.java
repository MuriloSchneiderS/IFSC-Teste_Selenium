package com.ifsc.selenium_junit;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class SeleniumTest {
    private WebDriver driver;
    
    @BeforeEach
    public void carregaNavegador(){
        FirefoxOptions options = new FirefoxOptions();

        this.driver = new FirefoxDriver(options);
        this.driver.manage().window().maximize();
    }
    @AfterEach
    public void fechaNavegador(){
        //if(driver!=null) this.driver.quit();
    }
    
    @Test
    @DisplayName(value="teste de busca")
    public void testaBusca() throws InterruptedException {
        String busca = "Gato";
        PaginaInicial page = new PaginaInicial(this.driver);

        page.navegaHome();
        Thread.sleep(2000);
        System.out.println(this.driver.getTitle());

        PaginaResposta pageResp = page.fazBusca(busca);
        Thread.sleep(2000);
        String labelResp = pageResp.getLabelRespostas();

        System.out.println(labelResp);

        Assertions.assertTrue(labelResp.endsWith("Imagens De "+busca));
    }
}