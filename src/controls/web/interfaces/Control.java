package controls.web.interfaces;

/**
 * Control common representation
 * <p>
 * All available actions for a user to execute over one web control
 * <p>
 * 
 * @author Angel Tsvetkov
 * 
 */
public interface Control {

  /**
   * Click the test element
   */
  void click();

  /**
   * @return true if the test element state is enabled and false if it is not
   */
  boolean isEnabled();

  /**
   * @return true if the test element state is enabled and false if it is not
   */
  boolean isVisible();

  /**
   * @param timeout the time to wait for particular test element to disappear
   * 
   * Still not implemented
   */
  void waitVanish(int timeout);
}
