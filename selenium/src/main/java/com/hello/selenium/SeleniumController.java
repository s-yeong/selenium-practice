package com.hello.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SeleniumController {
    private static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
    private static final String WEB_DRIVER_PATH = "C:\\Users\\parks\\chromedriver_win32\\chromedriver.exe";

    @ResponseBody
    @GetMapping("/selenium")
    public List<String> getBusinessHours(@RequestParam String url) throws InterruptedException {

        //드라이버 설정
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

        //크롬 설정을 담은 객체 생성
        ChromeOptions options = new ChromeOptions();
        //headless 설정
        options.addArguments("headless");

        //드라이버 객체 생성
        WebDriver driver = new ChromeDriver(options);

        //브라우저에서 url로 이동
        driver.get(url);

        //브라우저 로딩될 때까지 기다리기
        Thread.sleep(500);

        List<String> list = new ArrayList<>();

        // 데이터 가져오기
        WebElement element = driver.findElement(By.xpath("//*[@id=\"mArticle\"]/div[1]/div[2]/div[2]/div/div/ul"));
        List<WebElement> txt_operation = element.findElements(By.className("txt_operation"));
        for (WebElement webElement : txt_operation) {
            list.add(webElement.getText());
        }

        return list;
    }
}
