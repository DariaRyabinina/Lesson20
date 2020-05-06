package org.DariaRyabinina;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ReviewPage {

    private WebDriver webDriver;

    public ReviewPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public WebElement webColumnMoney() {
        return webDriver.findElement(By.xpath("//span[@class='amount-holder']/span[@class='amount']"));

    }

    public WebElement webColumnMyMoney() {
        return webDriver.findElement(By.xpath("//small[@class='my-assets']"));

    }

    public WebElement financialFreedom(int i) {
        return webDriver.findElement(By.xpath("//@id[.='can-spend'][1]/following::span[" + i + "]"));
    }

    public WebElement nameReview(int i) {
        return webDriver.findElement(By.xpath("//@id[.='header-container']/following::h1[" + i + "]"));
    }

}
