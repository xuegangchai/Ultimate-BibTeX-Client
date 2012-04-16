import ohtu.*
import ohtu.authentication.*
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'A reference can be created if proper informations are given'

scenario "creation succesfull with proper informations", {
    given 'login selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080");
        element = driver.findElement(By.linkText("Luo uusi viite"));
        element.click();
    }

    when 'a valid information are given', {
        element = driver.findElement(By.name("author"));
        element.sendKeys("Xuegang Chai");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Ohtu2012");
        element = driver.findElement(By.name("booktitle"));
        element.sendKeys("OhtuLabra");
        element = driver.findElement(By.name("pages"));
        element.sendKeys("31-33");
        element = driver.findElement(By.name("year"));
        element.sendKeys("2012");
        element = driver.findElement(By.id("submit"));
        element.submit();
    }
 
    then 'the reference will be added in to system', {
        driver.getPageSource().contains("Ohtu2012").shouldBe true
    }
}