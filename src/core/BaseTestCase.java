package core;

import java.lang.reflect.Method;

import junit.framework.TestCase;

import org.openqa.selenium.firefox.FirefoxDriver;

import reporting.Report;
import annotations.Browser;
import annotations.EntryPoint;
import enums.Browsers;

/**
 * This class extends jUnit TestCase class and should be inherit from the test project's base test
 * class
 * 
 * @author Angel Tsvetkov
 * 
 */
public class BaseTestCase extends TestCase {

  /* (non-Javadoc)
   * @see junit.framework.TestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    
    /* Load the properties file */
    PropertiesManager.initialise();
    executePrerequisites();
  }

  /* (non-Javadoc)
   * @see junit.framework.TestCase#tearDown()
   */
  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
    
    /* Close the browser and destroy the web driver */
    TestContext.getWebDriver().quit();
  }

  /**
   * 
   */
  private void executePrerequisites() {
    
    String url = null;
    Browsers browser = null;

    /* Check whether test class has Entry Point annotation */
    EntryPoint classEntryPoint = this.getClass().getAnnotation(EntryPoint.class);
    if (classEntryPoint != null) {
      url = classEntryPoint.value();
    }

    /* Check whether test class has Browser annotation */
    Browser classBrowser = this.getClass().getAnnotation(Browser.class);
    if (classBrowser != null) {
      browser = classBrowser.value();
    }

    Method[] methods = this.getClass().getMethods();
    if (methods != null) {

      /*
       * Check whether the test method has browser and entry point assigned. Take into account that
       * the test methods annotations override test class annotations
       */
      for (Method method : methods) {

        /* Check whether current method has Browser annotation */
        Browser methodBrowser = method.getAnnotation(Browser.class);
        if (methodBrowser != null) {
          browser = methodBrowser.value();
        }

        /* Check whether current method has EntryPoint annotation */
        EntryPoint methodEntryPoint = method.getAnnotation(EntryPoint.class);
        if (methodEntryPoint != null) {
          url = methodEntryPoint.value();
        }
      }
    }

    /*
     * Check whether browser and entry point are set in test methods or in test class
     */
    if (url != null && browser != null) {
      
      /* Open passed URL in selected browser */
      openBrowser(url, browser);
      
    } else {
      Report.error("Browser or Entry Point are not set!");
    }
  }

  /**
   * @param url which will be used when the browser is started 
   * @param browser type. Currently only FireFox is supported
   */
  private void openBrowser(String url, Browsers browser) {

    switch (browser) {
      case Firefox: {
        TestContext.setWebDriver(new FirefoxDriver());
      }
        break;
      case InternetExplorer: {

      }
        break;

      case Chrome: {

      }
        break;

      default: {

      }
        break;
    }

    TestContext.getWebDriver().get(url);
  }
}
