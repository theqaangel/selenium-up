package controls.web.interfaces;

/**
 * Checkbox representation
 * <p>
 * All actions the end user used browser can execute to a checkbox.
 * <p>
 * 
 * @author Angel Tsvetkov <angel.tsvetkov@mail.com>
 * 
 */
public interface CheckBox extends Control {

  /**
   * @return the checkbox label text
   */
  String getText();

  /**
   * @return true if the checkbox state is checked and false if it is not
   */
  boolean isChecked();

  /**
   * Set the checkbox to checked state. If the checkbox is already checked - nothing is changed
   */
  void check();

  /**
   * Set the checkbox to unchecked state. If the checbox is not checked - nothing is changed
   */
  void unCheck();
}
