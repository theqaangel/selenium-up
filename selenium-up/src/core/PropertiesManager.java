package core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import reporting.Report;

/**
 * Read properties files
 * @author Angel Tsvetkov
 *
 */
public class PropertiesManager {

  private static Properties prop = null;

  /**
   * Load config.properties file
   */
  public static void initialise() {
    prop = new Properties();

    try {
      /* Load a properties file */
      prop.load(new FileInputStream("config.properties"));

    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  /**
   * @param key from properties file
   * @return the value representation as a String
   */
  public static String getString(String key) {
    return prop.getProperty(key);
  }

  /**
   * @param key from properties file
   * @return the value representation as a intiger
   */
  public static int getInt(String key) {
    int value = 0;
    try {
      value = Integer.parseInt(prop.getProperty(key));
    } catch (NumberFormatException ex) {
      Report.error("The property with key = " + key + " CAN NOT be parsed to int.");
    }
    return value;
  }
}
