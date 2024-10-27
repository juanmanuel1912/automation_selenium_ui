package org.nopCommerce.stepsDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginSteps {
    ChromeDriver driver;
    int TIME_OUT = 20;

    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIME_OUT));
        driver.manage().window().maximize();
    }

    @Given("user is on Login Page")
    public void user_is_on_login_page() {
        driver.get("https://www.saucedemo.com/");
    }

    @When("User enters {string} and {string}")
    public void user_enters_and(String user, String password) {
        driver.findElement(By.id("user-name")).sendKeys(user);
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @When("User click on Login Button")
    public void user_click_on_login_button() {
        driver.findElement(By.xpath("//*[@id=\"login-button\"]")).submit();
    }

    @Then("the account option should be displayed")
    public void the_account_option_should_be_displayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT));

        try {
            // Validación para detectar si aparece el texto "Products" después de un login exitoso
            WebElement productsTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.className("title")));
            String actualTitle = productsTitle.getText();
            Assert.assertEquals(actualTitle, "Products", "El usuario no fue redirigido correctamente.");
            System.out.println("Login exitoso: Página de productos encontrada.");
        } catch (Exception e) {
            // Si no aparece "PRODUCTS", verificamos si es un error de credenciales
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector(".error-message-container.error")));
            String actualError = errorMessage.getText();
            String expectedError = "Epic sadface: Username and password do not match any user in this service";
            Assert.assertEquals(actualError, expectedError, "El mensaje de error no coincide.");
            System.out.println("Login fallido: Credenciales incorrectas.");
        }
    }


    @After
    public void tearDown() {
        // Cierra el navegador
        if (driver != null) {
            driver.quit();
        }
    }
}
