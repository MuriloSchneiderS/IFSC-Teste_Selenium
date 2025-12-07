package com.ifsc.selenium_junit;

import java.util.List;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class SeleniumTest {
    private WebDriver driver;
    PaginaInicial page;
    
    @BeforeEach
    public void carregaNavegador() throws InterruptedException{
        FirefoxOptions options = new FirefoxOptions();

        this.driver = new FirefoxDriver(options);
        this.driver.manage().window().maximize();
        //Garantir que está na pagina inicial
        page = new PaginaInicial(this.driver);
        page.navegaHome();
        Thread.sleep(2000);
    }
    @AfterEach
    public void fechaNavegador(){
        //if(driver!=null) this.driver.quit();
    }
    
    @Test
    @DisplayName(value="teste de busca de produto")
    public void testaBusca() throws InterruptedException {
        // Testando a busca por produto qualquer
        String busca1 = "Banoff";
        CardProduto card1 = page.fazBusca(busca1);
        Thread.sleep(1000);
        String tituloCard1 = card1.getTitulo();
        Thread.sleep(1000);
        
        // Testando a busca pelo último produto de um swiper
        String busca2 = "Snickers";
        page.limpaBusca();
        CardProduto card2 = page.fazBusca(busca2);
        Thread.sleep(1000);
        String tituloCard2 = card2.getTitulo();
        Thread.sleep(1000);
        
        Assertions.assertAll(
            () -> Assertions.assertTrue(tituloCard1.contains(busca1), 
                "O título do card não contém o produto buscado: " + busca1),
            () -> Assertions.assertTrue(tituloCard2.contains(busca2), 
                "O título do card não contém o produto buscado: " + busca2)
        );
    }
    
    @Test
    @DisplayName("teste de adicionar à sacola")
    public void testaSacola() throws InterruptedException{
        //Adicionar produto à sacola
        String nomeProduto1 = "RedBull";
        CardProduto card1 = page.fazBusca(nomeProduto1);
        Thread.sleep(2000);
        card1.addSacola();//1X
        
        page.limpaBusca();
        
        String nomeProduto2 = "Baly";
        CardProduto card2 = page.fazBusca(nomeProduto2);
        Thread.sleep(2000);
        card2.addSacola();
        card2.addSacola();//2X
        
        //Verificar se o produto está na sacola
        page.toggleSacola();
        PopUpSacola sacola = new PopUpSacola(driver);
        List<CardSacola> itens = sacola.getItens();//Armazena os itens uma vez

        //Verificações
        for (CardSacola item : itens){//Para cada item na sacola
            if(item.getNome().equals(nomeProduto1)){
                Assertions.assertTrue(item.getQuantidade()==1);
                Assertions.assertTrue(item.getPreco()==card1.getPreco());
            }
            if(item.getNome().equals(nomeProduto2)){
                Assertions.assertTrue(item.getQuantidade()==2);//2 balys pois foi clicado + 2 vezes
                Assertions.assertTrue(item.getPreco()==card2.getPreco());
            }
            Assertions.assertTrue(
                    item.getNome().equals(nomeProduto1) || item.getNome().equals(nomeProduto2), 
                       "Produto " + item.getNome() + " não está na sacola."
            );
        }
    }
}