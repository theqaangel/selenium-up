package tests;

import org.testng.annotations.Test;

import support.annotations.Browser;
import support.annotations.EntryPoint;
import core.enums.Browsers;

@EntryPoint("http://gmail.com")
@Browser(Browsers.Firefox)
public class LoginTest extends BaseTest {

  @Test
  public void testLogin() {

      loginPageView.login("seleniumup@gmail.com", "testuser");
      mainView.isPresented(true);
  }

}
