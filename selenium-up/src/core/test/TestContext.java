package core.test;

import org.openqa.selenium.WebDriver;

/**
 * Test context
 * 
 * @author Angel Tsvetkov
 *
 */
public class TestContext {

  private static WebDriver webDriver;

  /**
   * @return the web driver
   */
  public static WebDriver getWebDriver() {
    return webDriver;
  }

  /**
   * @param webDriver set the web driver
   */
  public static void setWebDriver(WebDriver webDriver) {
    TestContext.webDriver = webDriver;
  }
}
