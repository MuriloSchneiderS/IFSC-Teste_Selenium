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
    @DisplayName(value="teste de busca de produto")
    public void testaBusca() throws InterruptedException {
        //Garantir que está na pagina inicial
        PaginaInicial page = new PaginaInicial(this.driver);
        page.navegaHome();
        Thread.sleep(2000);
        
        // Testando a busca por produto qualquer
        String busca1 = "Banoff";
        CardProduto card1 = page.fazBusca(busca1);
        Thread.sleep(1000);
        String tituloCard1 = card1.getTituloResposta();
        Thread.sleep(1000);
        
        // Testando a busca pelo último produto de um swiper
        String busca2 = "Snickers";
        page.limpaBusca();
        CardProduto card2 = page.fazBusca(busca2);
        Thread.sleep(1000);
        String tituloCard2 = card2.getTituloResposta();
        Thread.sleep(1000);
        
        Assertions.assertAll(
            () -> Assertions.assertTrue(tituloCard1.contains(busca1), 
                "O título do card não contém o produto buscado: " + busca1),
            () -> Assertions.assertTrue(tituloCard2.contains(busca2), 
                "O título do card não contém o produto buscado: " + busca2)
        );
    }
}