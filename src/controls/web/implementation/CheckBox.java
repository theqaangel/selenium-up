package controls.web.implementation;

import org.openqa.selenium.By;

/**
 * Checkbox representation
 * <p>
 * All actions the end user used browser can execute to a checkbox.
 * <p>
 * 
 * @author Angel Tsvetkov <angel.tsvetkov@mail.com>
 * 
 */
public class CheckBox extends Control implements controls.web.interfaces.CheckBox {

  /**
   * @param descriptiveName - name of the check box which will appear in the generated report
   * @param criterion - set of search expressions presented as By expressions which uniquely
   *        identify 1 element in the web page
   */
  public CheckBox(String descriptiveName, By... criterion) {
    super(descriptiveName, criterion);
  }

  /*
   * (non-Javadoc)
   * 
   * @see controls.web.interfaces.CheckBox#getText()
   */
  @Override
  public String getText() {
    return getWebElement().getText();
  }

  /*
   * (non-Javadoc)
   * 
   * @see controls.web.interfaces.CheckBox#isChecked()
   */
  @Override
  public boolean isChecked() {
    return getWebElement().isSelected();
  }

  /*
   * (non-Javadoc)
   * 
   * @see controls.web.interfaces.CheckBox#check()
   */
  @Override
  public void check() {
    getWebElement().click();
  }

  /*
   * (non-Javadoc)
   * 
   * @see controls.web.interfaces.CheckBox#unCheck()
   */
  @Override
  public void unCheck() {
    if (isChecked()) {
      getWebElement().click();
    }
  }

}
