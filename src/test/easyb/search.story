import ohtu.*
import ohtu.authentication.*
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'A search based on author is succesful if what is being searched is in the system'

scenario "element that has been inserted to the database can be found with the search function based on the author", {
    given 'element inserted to the database', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8088");
        element = driver.findElement(By.linkText("Luo uusi viite"));
        element.click();
        element = driver.findElement(By.name("author"));
        element.sendKeys("test");
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

    when 'searching the added element from the database', {
        element = driver.findElement(By.name("keywords"));
        element.sendKeys("test");

        element.submit();
    }
 
    then 'the added element is found', {
        driver.getPageSource().contains("test").shouldBe true
    }
}
description 'A search based on title is succesful  if what is being searched is in the system'

scenario "element that has been inserted to the database can be found with the search function based on the title", {
    given 'element inserted to the database', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8088");
        element = driver.findElement(By.linkText("Luo uusi viite"));
        element.click();
        element = driver.findElement(By.name("author"));
        element.sendKeys("test");
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

    when 'searching the added element from the database', {
        element = driver.findElement(By.name("keywords"));
        element.sendKeys("Ohtu2012");

        element.submit();
    }
 
    then 'the added element is found', {
        driver.getPageSource().contains("Ohtu2012").shouldBe true
    }
}

description 'A search based on booktitle is succesful  if what is being searched is in the system'

scenario "element that has been inserted to the database can be found with the search function based on the booktitle", {
    given 'element inserted to the database', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8088");
        element = driver.findElement(By.linkText("Luo uusi viite"));
        element.click();
        element = driver.findElement(By.name("author"));
        element.sendKeys("test");
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

    when 'searching the added element from the database', {
        element = driver.findElement(By.name("keywords"));
        element.sendKeys("OhtuLabra");

        element.submit();
    }
 
    then 'the added element is found', {
        driver.getPageSource().contains("OhtuLabra").shouldBe true
    }
}

description 'A search based on year is succesful if what is being searched is in the system'

scenario "element that has been inserted to the database can be found with the search function based on the year", {
    given 'element inserted to the database', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8088");
        element = driver.findElement(By.linkText("Luo uusi viite"));
        element.click();
        element = driver.findElement(By.name("author"));
        element.sendKeys("test");
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

    when 'searching the added element from the database', {
        element = driver.findElement(By.name("keywords"));
        element.sendKeys("2012");

        element.submit();
    }
 
    then 'the added element is found', {
        driver.getPageSource().contains("2012").shouldBe true
    }
}






description 'A search is unsuccesful if  what is being searched is not in the system'

scenario "element that doesn't exist in the database cannot be found with the search function", {
    given 'element inserted to the database', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8088");
        element = driver.findElement(By.linkText("Luo uusi viite"));
        element.click();
        element = driver.findElement(By.name("author"));
        element.sendKeys("test");
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

    when 'searching the non-existant element from the database', {
        element = driver.findElement(By.name("keywords"));
        element.sendKeys("ViitettaEiOleOlemassa");

        element.submit();
    }
 
    then 'the non-existant element is not found', {
        driver.getPageSource().contains("ViitettaEiOleOlemassa").shouldBe false
    }
}