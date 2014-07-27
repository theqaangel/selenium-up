package controls.web.implementation;

import modules.reporter.Report;

import org.openqa.selenium.By;

/**
 * TextBox representation
 * <p>
 * All actions the end user used browser can execute to a text box.
 * <p>
 * 
 * @author Angel Tsvetkov <angel.tsvetkov@mail.com>
 * 
 */
public class TextBox extends Control implements controls.web.interfaces.TextBox {

  /**
   * @param descriptiveName - name of the textbox which will appear in the generated report
   * @param criterion - set of search expressions presented as By expressions which uniquely
   *        identify 1 element in the web page
   */
  public TextBox(String descriptiveName, By... criterion) {
    super(descriptiveName, criterion);
  }

  /* (non-Javadoc)
   * @see controls.web.interfaces.TextBox#enter(java.lang.String)
   */
  @Override
  public void enter(String text) {
    initWebElement();
    try {
      getWebElement().sendKeys(text);
      Report.info(text + " is entered in " + this.getDescriptiveName());
    } catch (Exception ex) {
      Report.error(text + " CAN'T be entered in " + this.getDescriptiveName());
    }

  }

  /* (non-Javadoc)
   * @see controls.web.interfaces.TextBox#GetText()
   */
  @Override
  public String getText() {
    return getWebElement().getText();
  }

}
