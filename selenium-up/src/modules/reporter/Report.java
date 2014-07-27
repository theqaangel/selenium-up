package modules.reporter;

import java.sql.Timestamp;

import org.junit.Assert;

/**
 * Set report events
 * 
 * @author Angel Tsvetkov
 *
 */
public class Report {

  /**
   * @param text error
   */
  public static void error(String text) {
    text = "ERROR:\t" + text;
    Write(getTimestamp() + "\t" + text);
    Assert.fail();
  }

  /**
   * @param text successful
   */
  public static void pass(String text) {
    text = "PASS:\t" + text;
    Write(getTimestamp() + "\t" + text);
  }

  /**
   * @param text information
   */
  public static void info(String text) {
    text = "I:\t" + text;
    Write(getTimestamp() + "\t" + text);
  }

  /**
   * @return String representation of current timestamp
   */
  static private String getTimestamp() {
    java.util.Date date = new java.util.Date();
    Timestamp timestamp = new Timestamp(date.getTime());
    return timestamp.toString();
  }

  /**
   * @param text to be set in the report
   */
  static private void Write(String text) {
    System.out.println(text);
  }
}
