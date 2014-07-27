package controls.web.implementation;

import modules.reporter.Report;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.By.ByLinkText;
import org.openqa.selenium.By.ByName;
import org.openqa.selenium.By.ByPartialLinkText;
import org.openqa.selenium.By.ByTagName;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import support.properties.PropertiesManager;
import core.enums.SearchBy;
import core.test.TestContext;

/**
 * Control common representation
 * <p>
 * All available actions for a user to execute over one web control
 * <p>
 * 
 * @author Angel Tsvetkov
 * 
 */
public class Control implements controls.web.interfaces.Control {

  /**
   * The name of the control. This value will be used in the generated report.
   */
  private String descriptiveName;
  /**
   * Set of By expressions which uniquely identify the control.
   */
  private By[] criterion;

  /**
   * The element located on the screen
   */
  private WebElement webElement;

  /**
   * @param descriptiveName - name of the control which will appear in the generated report
   * @param criterion - set of search expressions presented as By expressions which uniquely
   *        identify 1 element in the web page
   */
  public Control(String descriptiveName, By... criterion) {

    this.descriptiveName = descriptiveName;
    this.criterion = criterion;

  }

  /**
   * 
   */
  protected void initWebElement() {
    WebDriver webDriver = TestContext.getWebDriver();

    /* Check whether web driver is initialized */
    if (webDriver != null) {
      try {
        /*
         * Try to initialize the web element for the time defined in find.timeout.sec. If the
         * find.timeout.sec is not defined, by default the find timeout is 30 seconds
         */
        long timeout = 30;

        try {
          /*
           * In some cases if the config.properties file is not found or the property
           * find.timeout.sec does not exist, the exception is thrown but the test should continue
           * with the default 30 seconds search timeout
           */
          timeout = PropertiesManager.getInt("find.timeout.sec");
        } catch (Exception ex) {

        }

        /* Search the test element with defined timeout */
        WebDriverWait wait = new WebDriverWait(webDriver, timeout);
        webElement =
            wait.until(ExpectedConditions
                .presenceOfElementLocated(prepareSerachStatement(this.criterion)));

      } catch (Exception ex) {
        /* I something get wrong while looking for test element */
        Report.error(this.getDescriptiveName() + " is NOT initialised." + ex.getMessage());
      }
    } else {
      /* If the web driver is not initialized */
      Report
          .error("WebDriver is NOT initialised. Please verify that your test class extends BaseTestCase.");
    }
  }

  /**
   * @param criterion set of By expressions which uniquely identify test element
   * @return XPath By expression which represents all of the provided criterion
   * @throws Exception
   */
  private By prepareSerachStatement(By... criterion) throws Exception {

    /* If no criteria is provided return null */
    if (criterion == null) {
      return null;
    }

    /* If there is only one provided criteria */
    if (criterion.length == 1) {

      /* If this criteria is by class attribute */
      if (criterion[0] instanceof ByClassName) {

        /*
         * By default WebDriver search for particular class name, not for the entire class attribute
         * value. Here we change this behavior and now the entire class value is analyzed.
         */
        return convertToXPath(criterion);

      } else {

        /* If the provided 1 criteria is not by class name, then just return it */
        return criterion[0];
      }

    } else {

      /* If there are more than 1 criteria then convert it to XPath and return it back */
      return convertToXPath(criterion);

    }
  }

  /**
   * @param criterion set of By expressions which uniquely identify test element
   * @return XPath By expression which represents all of the provided criterion. This method can
   *         combine multiple search criterion into one XPath search criteria if the set of provided
   *         criterion contains only search expressions by id, name and class. Otherwise the
   *         exception will be thrown.
   * @throws Exception
   */
  private By convertToXPath(By... criterion) throws Exception {
    By xpath = null;
    String xpathExpression = "";

    for (By criteria : criterion) {
      SearchBy type = getCriterionType(criteria);

      switch (type) {
        case id: {
          String value = getCriterionValue(criteria);
          xpathExpression += "//*[@id='" + value + "'] | ";
        }
          break;

        case name: {
          String value = getCriterionValue(criteria);
          xpathExpression += "//*[@name='" + value + "'] | ";
        }
          break;

        case className: {
          String value = getCriterionValue(criteria);
          xpathExpression += "//*[@class='" + value + "'] | ";
        }
          break;

        case xpath: {
          xpathExpression += getCriterionValue(criteria) + " | ";
        }
          break;

        default: {
          throw new Exception("Criterion can not be converted to XPath");
        }
      }
    }

    if (xpathExpression.length() == 0) {
      throw new Exception("Criterion can not be converted to XPath");
    } else {
      xpath = By.xpath(xpathExpression.substring(0, xpathExpression.lastIndexOf('|')));
    }

    return xpath;
  }

  /**
   * @param criterion a By expression
   * @return String representation of the provided criteria
   */
  private String getCriterionValue(By criterion) {
    String value = null;

    try {
      value = criterion.toString();
      /*
       * The criteria is passed in format "By.{criteria]: [value]". In order to get only the
       * criteria value, the provided string is splitter.
       */
      if (value.contains(":")) {
        value = value.substring(value.indexOf(":") + 1);
      }
      value = value.trim();
    } catch (IndexOutOfBoundsException ex) {

    }

    return value;
  }

  /**
   * @param criterion a By expression
   * @return SearchBy object which represents the criterion type.
   */
  private SearchBy getCriterionType(By criterion) {
    SearchBy type = null;
    if (criterion instanceof ById) {
      type = SearchBy.id;
    } else if (criterion instanceof ByClassName) {
      type = SearchBy.className;
    } else if (criterion instanceof ByLinkText) {
      type = SearchBy.linkText;
    } else if (criterion instanceof ByName) {
      type = SearchBy.name;
    } else if (criterion instanceof ByPartialLinkText) {
      type = SearchBy.partialLinkText;
    } else if (criterion instanceof ByTagName) {
      type = SearchBy.tagName;
    } else if (criterion instanceof ByXPath) {
      type = SearchBy.xpath;
    } else if (criterion instanceof ByCssSelector) {
      type = SearchBy.cssSelector;
    }
    return type;
  }

  /*
   * (non-Javadoc)
   * 
   * @see controls.web.interfaces.Control#click()
   */
  @Override
  public void click() {
    initWebElement();
    if (webElement != null) {
      try {
        webElement.click();
        Report.info(this.getDescriptiveName() + " is clicked");

      } catch (Exception ex) {
        Report.error(this.getDescriptiveName() + " is NOT clicked");
      }
    } else {
      Report.error(this.getDescriptiveName() + " is NOT initialised");
    }

  }

  /*
   * (non-Javadoc)
   * 
   * @see controls.web.interfaces.Control#isEnabled()
   */
  @Override
  public boolean isEnabled() {
    initWebElement();
    boolean result = false;
    if (webElement != null) {
      try {
        result = webElement.isEnabled();
        Report.pass(this.getDescriptiveName() + " is " + (result ? "enabled" : "disabled"));

      } catch (Exception ex) {
        Report.error(this.getDescriptiveName() + " state CAN'T be determined");
      }
    } else {
      Report.error(this.getDescriptiveName() + " is NOT initialised");
    }
    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see controls.web.interfaces.Control#isVisible()
   */
  @Override
  public boolean isVisible() {
    initWebElement();
    boolean result = false;
    if (webElement != null) {
      try {
        result = webElement.isDisplayed();
        Report.pass(this.getDescriptiveName() + " is " + (result ? "visible" : "not visible"));

      } catch (Exception ex) {
        Report.error(this.getDescriptiveName() + " state CAN'T be determined");
      }
    } else {
      Report.error(this.getDescriptiveName() + " is NOT initialised");
    }
    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see controls.web.interfaces.Control#waitVanish(int)
   */
  @Override
  public void waitVanish(int timeout) throws NotImplementedException {
    initWebElement();
    if (webElement != null) {
      // TODO: Implement this
      throw new NotImplementedException();
    } else {
      Report.error(this.getDescriptiveName() + " is NOT initialised");
    }
  }

  /**
   * @return the test element descriptive name. This name will be used in the generated report.
   */
  public String getDescriptiveName() {

    /*
     * Get the subclass name. This is used as a test element type. The subclasses are Button,
     * TextBox, etc...
     */
    String controlType = this.getClass().getSimpleName();

    return descriptiveName + " " + controlType;
  }

  /**
   * @return the test element if it is initialized successfully
   */
  protected WebElement getWebElement() {

    if (webElement != null) {
      return webElement;
    } else {
      Report.error("Test Element is not defined");
    }

    return null;
  }

}
