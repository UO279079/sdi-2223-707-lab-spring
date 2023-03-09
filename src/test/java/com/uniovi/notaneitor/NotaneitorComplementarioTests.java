package com.uniovi.notaneitor;

import com.uniovi.notaneitor.pageobjects.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NotaneitorComplementarioTests {
    static String PathFirefox = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
    //static String Geckodriver = "C:\\Path\\geckodriver-v0.30.0-win64.exe";
    static String Geckodriver = "C:\\Users\\uo279079\\Desktop\\geckodriver-v0.30.0-win64.exe";
    //static String PathFirefox = "/Applications/Firefox.app/Contents/MacOS/firefox-bin";
    //static String Geckodriver = "/Users/USUARIO/selenium/geckodriver-v0.30.0-macos";
    //Común a Windows y a MACOSX
    static WebDriver driver = getDriver(PathFirefox, Geckodriver);
    static String URL = "http://localhost:8090";

    public static WebDriver getDriver(String PathFirefox, String Geckodriver) {
        System.setProperty("webdriver.firefox.bin", PathFirefox);
        System.setProperty("webdriver.gecko.driver", Geckodriver);
        driver = new FirefoxDriver();
        return driver;
    }

    @BeforeEach
    public void setUp() {
        driver.navigate().to(URL);
    }

    //Después de cada prueba se borran las cookies del navegador
    @AfterEach
    public void tearDown() {
        driver.manage().deleteAllCookies();
    }

    //Antes de la primera prueba
    @BeforeAll
    static public void begin() {
    }

    //Al finalizar la última prueba
    @AfterAll
    static public void end() {
        //Cerramos el navegador al finalizar las pruebas
        driver.quit();
    }

    @Test
    @Order(1)
    void PR01() {
        PO_LoginView.login(driver, "99999988F", "123456", "99999988F");

        PO_PrivateView.clickOption(driver, "//li/a[contains(@id, 'profesorDropdown')]", 0);
        PO_PrivateView.clickOption(driver, "//a[contains(@href, 'teacher/add')]", 0);

        String checkText = "12345678A";
        PO_PrivateView.fillFormAddTeacher(driver, checkText, "Mario", "Garcia", "Doctor");
        List<WebElement> elements = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, elements.get(0).getText());

        String loginText = PO_HomeView.getP().getString("signup.message", PO_Properties.getSPANISH());
        PO_LoginView.getLogout(driver, loginText);
    }

    @Test
    @Order(2)
    void PR02A() {
        PO_LoginView.login(driver, "99999988F", "123456", "99999988F");

        PO_PrivateView.clickOption(driver, "//li/a[contains(@id, 'profesorDropdown')]", 0);
        PO_PrivateView.clickOption(driver, "//a[contains(@href, 'teacher/add')]", 0);

        String checkText = "12345679A";
        PO_PrivateView.fillFormAddTeacher(driver, checkText, "M", "Garcia", "Doctor");

        List<WebElement> result = PO_SignUpView.checkElementByKey(driver, "Error.teacher.name.length",
                PO_Properties.getSPANISH() );
        checkText = PO_HomeView.getP().getString("Error.teacher.name.length",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());

    }

    @Test
    @Order(3)
    void PR02B() {
        PO_LoginView.login(driver, "99999988F", "123456", "99999988F");

        PO_PrivateView.clickOption(driver, "//li/a[contains(@id, 'profesorDropdown')]", 0);
        PO_PrivateView.clickOption(driver, "//a[contains(@href, 'teacher/add')]", 0);

        String checkText = "12345679A";
        PO_PrivateView.fillFormAddTeacher(driver, checkText, "Mario", "Garcia", "D");

        List<WebElement> result = PO_SignUpView.checkElementByKey(driver, "Error.teacher.category.length",
                PO_Properties.getSPANISH() );
        checkText = PO_HomeView.getP().getString("Error.teacher.category.length",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());
    }

    @Test
    @Order(4)
    void PR03A(){
        PO_LoginView.login(driver, "99999988F", "123456", "99999988F");
        driver.navigate().to(URL + "/user/add");
        String checkText = "Rol:";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    @Test
    @Order(5)
    void PR03B(){
        PO_LoginView.login(driver, "99999993D", "123456", "99999993D");
        driver.navigate().to(URL + "/user/add");
        String checkText = "There was an unexpected error (type=Forbidden, status=403).";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    @Test
    @Order(6)
    void PR03C(){
        PO_LoginView.login(driver, "99999990A", "123456", "99999990A");
        driver.navigate().to(URL + "/user/add");
        String checkText = "There was an unexpected error (type=Forbidden, status=403).";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
        //By.caseSelector();
    }

}
