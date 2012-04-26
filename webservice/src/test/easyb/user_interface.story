import ohtu.*
import ohtu.authentication.*
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'When user starts the service and clicks proper links or buttons, right pages are openened'

scenario "when user starts the service, front page opens", {
    given 'user has connected to the service',{
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8088");
    }
    when 'user has opened the service', {
        
    }
    then 'front page opens', {
        driver.getPageSource().contains("Ultimate Bibtex Client").shouldBe true;
        driver.getPageSource().contains("Luo uusi viite").shouldBe true;
        driver.getPageSource().contains("Lataa viitteet BibTeX-muodossa").shouldBe true;
    }
}


scenario "when clicked \"luo uusi viite\", an empty form for creating a new reference opens", {
    given 'service has been started', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8088");
    }
    when 'user clicks \"luo uusi viite\"', {
        element = driver.findElement(By.linkText("Luo uusi viite"));
        element.click();
    }
    then 'an empty form for creating a new reference is opened', {
        driver.getPageSource().contains("Julkaisijan osoite").shouldBe true;
        WebElement element = driver.findElement(By.id("title"));
        element.getAttribute("value").shouldBe "";
    }
}

scenario "when a new reference is already created and \"lue uusi viite\" is clicked again, the opening new form is empty", {
    given 'a new article reference is being created properly', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8088");
        element = driver.findElement(By.linkText("Luo uusi viite"));
        element.click();
        element = driver.findElement(By.id("articleButton"));
        element.click();
        element = driver.findElement(By.name("refkey"));
        element.sendKeys("viite");
        element = driver.findElement(By.name("author"));
        element.sendKeys("Xuegang Chai");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Ohtu2012");
        element = driver.findElement(By.name("year"));
        element.sendKeys("2012");
        element = driver.findElement(By.name("journal"));
        element.sendKeys("National Geographic");
        element = driver.findElement(By.id("submit"));
        element.submit();
    }
    when 'user clicks \"luo uusi viite\"', {
        element = driver.findElement(By.linkText("Luo uusi viite"));
        element.click();
    }
    then 'an empty form for creating a new reference is opened',{
        driver.getPageSource().contains("Julkaisijan osoite").shouldBe true;
        WebElement element = driver.findElement(By.id("title"));
        element.getAttribute("value").shouldBe "";
    }
}
