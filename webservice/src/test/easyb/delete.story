import ohtu.*
import ohtu.authentication.*
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'delete a reference that already exists'

scenario 'deletion succesfull', {
    given 'deletion link selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8088");
        element = driver.findElement(By.linkText("Luo uusi viite"));
        element.click();
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

    when 'reference does exists', {
        element = driver.findElement(By.linkText("Poista"));
        element.click();
    }
 
    then 'the reference will be deleted from system', {
        driver.getPageSource().contains("<!-- This is the front page. -->").shouldBe true
        driver.getPageSource().contains("Ohtu2013").shouldBe true
    }
}


scenario 'deletion not succesfull', {
    given 'deletion link selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8088");
    }

    when 'reference does NOT exists', {
        element = driver.findElement(By.linkText("Poista"));
        element.click();
    }
 
    then 'the reference will NOT be deleted from system', {
        driver.getPageSource().contains("<!-- This is the front page. -->").shouldBe false
    }
}