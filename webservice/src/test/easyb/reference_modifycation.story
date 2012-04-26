import ohtu.*
import ohtu.authentication.*
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'edit a file that alread exists'

scenario 'a created reference can be opened from the list', {
    given 'a new reference is created', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8088");
        element = driver.findElement(By.linkText("Luo uusi viite"));
        element.click();
        element = driver.findElement(By.id("articleButton"));
        element.click();
        element = driver.findElement(By.name("refkey"));
        element.sendKeys("viite2199");
        element = driver.findElement(By.name("author"));
        element.sendKeys("Xuegang Chai");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Ohtu2099");
        element = driver.findElement(By.name("year"));
        element.sendKeys("2012");
        element = driver.findElement(By.name("journal"));
        element.sendKeys("National Geographic");
        element = driver.findElement(By.id("submit"));
        element.submit();
        
    }

    when 'muokkaa is clicked', {
        element = driver.findElement(By.id("viite2199modify"));
        element.click();
        
    }
 
    then 'modification page opens', {
        driver.getPageSource().contains("Muokkaa viitettä").shouldBe true;
        driver.getPageSource().contains("Ohtu2099").shouldBe true;
    }
}

scenario 'a created reference can be opened from the list (alternative)', {
    given 'a new reference is created', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8088");
        element = driver.findElement(By.linkText("Luo uusi viite"));
        element.click();
        element = driver.findElement(By.id("articleButton"));
        element.click();
        element = driver.findElement(By.name("refkey"));
        element.sendKeys("viite2299");
        element = driver.findElement(By.name("author"));
        element.sendKeys("Xuegang Chai");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Ohtu2099");
        element = driver.findElement(By.name("year"));
        element.sendKeys("2012");
        element = driver.findElement(By.name("journal"));
        element.sendKeys("National Geographic");
        element = driver.findElement(By.id("submit"));
        element.submit();
        
    }

    when 'muokkaa is clicked', {
        element = driver.findElement(By.linkText("Muokkaa viite2299"));
        element.click();
        
    }
 
    then 'modification page opens', {
        driver.getPageSource().contains("Muokkaa viitettä").shouldBe true;
        driver.getPageSource().contains("Ohtu2099").shouldBe true;
    }
}

scenario 'a created reference can be found from the list', {
    given 'a new reference is created', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8088");
        element = driver.findElement(By.linkText("Luo uusi viite"));
        element.click();
        element = driver.findElement(By.id("articleButton"));
        element.click();
        element = driver.findElement(By.name("refkey"));
        element.sendKeys("viite2099");
        element = driver.findElement(By.name("author"));
        element.sendKeys("Xuegang Chai");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Ohtu2099");
        element = driver.findElement(By.name("year"));
        element.sendKeys("2012");
        element = driver.findElement(By.name("journal"));
        element.sendKeys("National Geographic");
        
    }

    when 'submit is clicked', {
        element = driver.findElement(By.id("submit"));
        element.submit();
    }
 
    then 'the reference should be found from the list', {
        driver.getPageSource().contains("Muokkaa").shouldBe true;
        driver.getPageSource().contains("Ohtu2099").shouldBe true;
        driver.getPageSource().contains("viite2099modify").shouldBe true;
    }
}

scenario 'a created reference can be opened from the list', {
    given 'a new reference is created', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8088");
        element = driver.findElement(By.linkText("Luo uusi viite"));
        element.click();
        element = driver.findElement(By.id("articleButton"));
        element.click();
        element = driver.findElement(By.name("refkey"));
        element.sendKeys("viite2199");
        element = driver.findElement(By.name("author"));
        element.sendKeys("Xuegang Chai");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Ohtu2099");
        element = driver.findElement(By.name("year"));
        element.sendKeys("2012");
        element = driver.findElement(By.name("journal"));
        element.sendKeys("National Geographic");
        element = driver.findElement(By.id("submit"));
        element.submit();
        
    }

    when 'muokkaa is clicked', {
        element = drivere.findElement(By.id("viite2199modify"));
        element.click();
        
    }
 
    then 'modification page opens', {
        driver.getPageSource().contains("Muokkaa viitettä").shouldBe true;
        driver.getPageSource().contains("Ohtu2099").shouldBe true;
    }
}


scenario 'modification is succesfull with existing reference', {
    given 'a created reference is opened', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8088");
        element = driver.findElement(By.id("viite2099.modify"));
        element.click();
    }

    when 'a valid modification is done', {
        
        element = driver.findElement(By.name("title"));
        element.sendKeys("Ohtu2100");
        element = driver.findElement(By.id("submit"));
        element.submit();
    }
 
    then 'a NEW reference will be NOT be added in to system but old one changed', {
        driver.getPageSource().contains("<!-- This is the front page. -->").shouldBe true;
        driver.getPageSource().contains("Ohtu2100").shouldBe true;
        driver.getPageSource().contains("viite2099.modify").shouldBe true;
        driver.getPageSource().contains("Ohtu2099").shouldBe false;
    }
}