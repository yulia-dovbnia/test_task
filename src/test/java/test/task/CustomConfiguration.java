package test.task;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import test.task.logger.AllureSelenide;

import java.net.MalformedURLException;
import java.net.URL;

@Configuration
@ComponentScan(basePackages = {"test.task"})
@PropertySources({
        @PropertySource("classpath:${spring.profile.active}.properties")
})
public class CustomConfiguration {

    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    @Profile("selenoid")
    public WebDriver chromeDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("80.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        WebDriver webDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        webDriver.manage().window().maximize();
        return webDriver;
    }

    @Bean
    public WebDriver chromeDriverLocal() {
        WebDriverManager.chromedriver().setup();
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        WebDriverRunner.setWebDriver(webDriver);
//        SelenideLogger.addListener("AllureSelenide", new io.qameta.allure.selenide.AllureSelenide().screenshots(true).savePageSource(true));
        SelenideLogger.addListener("Custom", new AllureSelenide().screenshots(true).savePageSource(true));
        return webDriver;
    }

    @Bean
    public SoftAssertions softAssertions() {
        return new SoftAssertions();
    }
}
