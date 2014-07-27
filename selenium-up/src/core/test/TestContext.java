package core.test;

import org.openqa.selenium.WebDriver;

import support.settings.Settings;

/**
 * Test context
 * 
 * @author Angel Tsvetkov
 *
 */
public class TestContext {

  private static WebDriver webDriver;
  private static Settings settings;
  
  static
  {
    settings = new Settings("testconfig.xml");
  }

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

  public static Settings getSettings() {
    return settings;
  }

  public static void setSettings(Settings settings) {
    TestContext.settings = settings;
  }
}
