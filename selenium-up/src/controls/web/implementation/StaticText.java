package controls.web.implementation;

import org.openqa.selenium.By;

/**
 * Static Text representation
 * <p>
 * All actions the end user used browser can execute to a static text.
 * <p>
 * 
 * @author Angel Tsvetkov <angel.tsvetkov@mail.com>
 * 
 */
public class StaticText extends Control implements controls.web.interfaces.StaticText {

  /**
   * @param descriptiveName - name of the static text which will appear in the generated report
   * @param criterion - set of search expressions presented as By expressions which uniquely
   *        identify 1 element in the web page
   */
  public StaticText(String descriptiveName, By... criterion) {
    super(descriptiveName, criterion);
  }

  /*
   * (non-Javadoc)
   * 
   * @see controls.web.interfaces.StaticText#getText()
   */
  @Override
  public String getText() {
    return getWebElement().getText();
  }

}
