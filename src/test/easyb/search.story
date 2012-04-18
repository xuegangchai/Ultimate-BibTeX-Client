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

description 'A search based on tag is succesful if what is being searched is in the system'

scenario "element created with one tag can be found with the search function based on the tag", {
    given 'element inserted to the database', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8088");
        element = driver.findElement(By.linkText("Luo uusi viite"));
        element.click();
        element = driver.findElement(By.name("author"));
        element.sendKeys("Tyyppitesti");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Ohtu2012");
        element = driver.findElement(By.name("booktitle"));
        element.sendKeys("OhtuLabra");
        element = driver.findElement(By.name("pages"));
        element.sendKeys("31-33");
        element = driver.findElement(By.name("year"));
        element.sendKeys("2012");
        element = driver.findElement(By.name("tags"));
        element.sendKeys("pelikaani");
        element = driver.findElement(By.id("submit"));
        element.submit();
    }

    when 'searching the added element from the database', {
        element = driver.findElement(By.name("keywords"));
        element.sendKeys("pelikaani");

        element.submit();
    }
 
    then 'the added element is found', {
        driver.getPageSource().contains("Tyyppitesti").shouldBe true
    }
}

scenario "element created with several tags can be found with the search function based on one tag", {
    given 'element inserted to the database', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8088");
        element = driver.findElement(By.linkText("Luo uusi viite"));
        element.click();
        element = driver.findElement(By.name("author"));
        element.sendKeys("Tyyppitesti2");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Ohtu2012");
        element = driver.findElement(By.name("booktitle"));
        element.sendKeys("OhtuLabra");
        element = driver.findElement(By.name("pages"));
        element.sendKeys("31-33");
        element = driver.findElement(By.name("year"));
        element.sendKeys("2012");
        element = driver.findElement(By.name("tags"));
        element.sendKeys("auto, polkupyörä, ikkuna");
        element = driver.findElement(By.id("submit"));
        element.submit();
    }

    when 'searching the added element from the database', {
        element = driver.findElement(By.name("keywords"));
        element.sendKeys("polkupyörä");

        element.submit();
    }
 
    then 'the added element is found', {
        driver.getPageSource().contains("Tyyppitesti2").shouldBe true
    }
}

scenario "when searching by tags, only the references including the tags mentioned will be found", {
    given 'two elements with tags inserted to the database', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8088");

        element = driver.findElement(By.linkText("Luo uusi viite"));
        element.click();
        element = driver.findElement(By.name("author"));
        element.sendKeys("Tyyppitesti3");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Ohtu2012");
        element = driver.findElement(By.name("booktitle"));
        element.sendKeys("OhtuLabra");
        element = driver.findElement(By.name("pages"));
        element.sendKeys("31-33");
        element = driver.findElement(By.name("year"));
        element.sendKeys("2012");
        element = driver.findElement(By.name("tags"));
        element.sendKeys("auto, polkupyörä, ikkuna");
        element = driver.findElement(By.id("submit"));
        element.submit();

        element = driver.findElement(By.linkText("Luo uusi viite"));
        element.click();
        element = driver.findElement(By.name("author"));
        element.sendKeys("Tyyppitesti4");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Ohtu2012");
        element = driver.findElement(By.name("booktitle"));
        element.sendKeys("OhtuLabra");
        element = driver.findElement(By.name("pages"));
        element.sendKeys("31-33");
        element = driver.findElement(By.name("year"));
        element.sendKeys("2012");
        element = driver.findElement(By.name("tags"));
        element.sendKeys("polkupyörä");
        element = driver.findElement(By.id("submit"));
        element.submit();

        element = driver.findElement(By.linkText("Luo uusi viite"));
        element.click();
        element = driver.findElement(By.name("author"));
        element.sendKeys("Tyyppitesti5");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Ohtu2012");
        element = driver.findElement(By.name("booktitle"));
        element.sendKeys("OhtuLabra");
        element = driver.findElement(By.name("pages"));
        element.sendKeys("31-33");
        element = driver.findElement(By.name("year"));
        element.sendKeys("2012");
        element = driver.findElement(By.name("tags"));
        element.sendKeys("ikkuna, auto");
        element = driver.findElement(By.id("submit"));
        element.submit();
    }

    when 'searching the added element from the database', {
        element = driver.findElement(By.name("keywords"));
        element.sendKeys("polkupyörä");

        element.submit();
    }
 
    then 'the added elements are found', {
        driver.getPageSource().contains("Tyyppitesti3").shouldBe true;
        driver.getPageSource().contains("Tyyppitesti4").shouldBe true;
        driver.getPageSource().contains("Tyyppitesti5").shouldBe false;
    }
}


scenario "when searching with a searchword, references can be found by booktitle and by tags", {
    given 'two elements with tags inserted to the database', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8088");

        element = driver.findElement(By.linkText("Luo uusi viite"));
        element.click();
        element = driver.findElement(By.name("author"));
        element.sendKeys("Tyyppitesti6");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Ohtu2012");
        element = driver.findElement(By.name("booktitle"));
        element.sendKeys("OhtuLabra");
        element = driver.findElement(By.name("pages"));
        element.sendKeys("31-33");
        element = driver.findElement(By.name("year"));
        element.sendKeys("2012");
        element = driver.findElement(By.name("tags"));
        element.sendKeys("auto, polkupyörä, ikkuna");
        element = driver.findElement(By.id("submit"));
        element.submit();

        element = driver.findElement(By.linkText("Luo uusi viite"));
        element.click();
        element = driver.findElement(By.name("author"));
        element.sendKeys("Tyyppitesti7");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Ohtu2012");
        element = driver.findElement(By.name("booktitle"));
        element.sendKeys("ikkuna");
        element = driver.findElement(By.name("pages"));
        element.sendKeys("31-33");
        element = driver.findElement(By.name("year"));
        element.sendKeys("2012");
        element = driver.findElement(By.name("tags"));
        element.sendKeys("polkupyörä");
        element = driver.findElement(By.id("submit"));
        element.submit();

        element = driver.findElement(By.linkText("Luo uusi viite"));
        element.click();
        element = driver.findElement(By.name("author"));
        element.sendKeys("Tyyppitesti8");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Ohtu2012");
        element = driver.findElement(By.name("booktitle"));
        element.sendKeys("OhtuLabra");
        element = driver.findElement(By.name("pages"));
        element.sendKeys("31-33");
        element = driver.findElement(By.name("year"));
        element.sendKeys("2012");
        element = driver.findElement(By.name("tags"));
        element.sendKeys("polkupyörä, auto");
        element = driver.findElement(By.id("submit"));
        element.submit();
    }

    when 'searching the added element from the database', {
        element = driver.findElement(By.name("keywords"));
        element.sendKeys("ikkuna");
        element.submit();
    }
 
    then 'only the elements with the keyword are found', {
       
        driver.getPageSource().contains("Tyyppitesti6").shouldBe true;
        driver.getPageSource().contains("Tyyppitesti7").shouldBe true;
        driver.getPageSource().contains("Tyyppitesti8").shouldBe false;
    }
}