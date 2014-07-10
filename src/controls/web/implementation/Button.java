package controls.web.implementation;

import org.openqa.selenium.By;

/**
 * Button representation
 * <p>
 * All actions the end user used browser can execute to a button.
 * <p>
 * 
 * @author Angel Tsvetkov <angel.tsvetkov@mail>
 * 
 */
public class Button extends Control implements controls.web.interfaces.Button {

  /**
   * @param descriptiveName - name of the button which will appear in the generated report
   * @param criterion - set of search expressions presented as By expressions which uniquely
   *        identify 1 element in the web page
   */
  public Button(String descriptiveName, By... criterion) {
    super(descriptiveName, criterion);
  }

  /*
   * (non-Javadoc)
   * 
   * @see controls.web.interfaces.Button#getText()
   */
  @Override
  public String getText() {

    return getWebElement().getText();
  }

}
