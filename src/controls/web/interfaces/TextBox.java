package controls.web.interfaces;

/**
 * TextBox representation
 * <p>
 * All actions the end user used browser can execute to a text box.
 * <p>
 * 
 * @author Angel Tsvetkov <angel.tsvetkov@mail.com>
 * 
 */
public interface TextBox extends Control {

  /**
   * @param text - the text which will be entered into textbox
   */
  void enter(String text);

  /**
   * @return the textbox content
   */
  String getText();
}
