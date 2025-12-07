package com.ifsc.selenium_junit;

import java.util.List;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Dimension;
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
    
    @Test
    @DisplayName("teste de creditar com sacola vazia/sem nome")
    public void testaFalhaCreditar() throws InterruptedException{
        //Sacola vazia
        page.toggleSacola();
        PopUpSacola sacola = new PopUpSacola(driver);
        sacola.creditar();
        Alert alert = driver.switchTo().alert();
        Thread.sleep(1000);
        Assertions.assertEquals(alert.getText(), "Seu carrinho está vazio!");
        alert.accept();
        
        //Faltando nome
        page.toggleSacola();
        Thread.sleep(500);
        CardProduto card1 = page.fazBusca("Coca");
        Thread.sleep(1000);
        card1.addSacola();
        
        page.toggleSacola();
        sacola.creditar();
        Thread.sleep(1000);
        Assertions.assertEquals(alert.getText(), "É necessário preencher o campo de texto com seu nome!");
        alert.accept();
    }
    
    @Test
    @DisplayName("teste de creditar corretamente")
    public void testaSucessoCreditar() throws InterruptedException{
        //Preencher sacola
        CardProduto card1 = page.fazBusca("Coca");
        Thread.sleep(1000);
        card1.addSacola();
        
        //Preencher nome
        page.toggleSacola();
        PopUpSacola sacola = new PopUpSacola(driver);
        String nome = "murilo";
        sacola.preencheNome(nome);
        sacola.creditar();
        Alert alert = driver.switchTo().alert();
        
        Assertions.assertEquals(alert.getText().trim(),("  Obrigado, "+nome+". Seu pedido foi realizado!\n" +
"      Compareça à Cantin's Coffee para receber e pagar pelo seu pedido.\n" +
"      Valor Total: R$"+String.format("%.2f",card1.getPreco())+".").trim());
    }
    
    @Test
    @DisplayName("teste das opcoes do cabecalho")
    public void testaMenu() throws InterruptedException{
        CabecalhoMenu menu = page.getMenu();
        menu.clickMenu();
        Thread.sleep(1000);
        Assertions.assertTrue(driver.getCurrentUrl().contains("#menu"), "A URL não contém '#menu'.");
        
        menu.clickSobre();
        Thread.sleep(1000);
        Assertions.assertTrue(driver.getCurrentUrl().contains("#sobre"), "A URL não contém '#sobre'.");
        
        menu.clickContato();
        Thread.sleep(1000);
        Assertions.assertTrue(driver.getCurrentUrl().contains("#faleconosco"), "A URL não contém '#faleconosco'.");
        
        Assertions.assertTrue(menu.clickAjuda().getAttribute("class").contains("open"), "O pop up ajuda não esta aberto.");
    }
    
    @Test
    @DisplayName("testa redimensionamento da tela")
    public void testaMobile() throws InterruptedException{
        driver.manage().window().setSize(new Dimension(759, 1000));
        //Menu deve virar hambúrguer.
        CabecalhoMenu menu = page.getMenu();
        Assertions.assertTrue(menu.checkDisplayMobileMenu(), "mobile-menu-icon nao esta visivel.");
        //cardápio deve ficar vertical
        Assertions.assertTrue(page.fazBusca("Brownie").slider().getAttribute("class").contains("swiper-vertical"));
    }
}