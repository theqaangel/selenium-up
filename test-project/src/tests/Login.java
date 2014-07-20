package tests;

import org.junit.Test;

import annotations.Browser;
import annotations.EntryPoint;
import enums.Browsers;

@EntryPoint("http://gmail.com")
@Browser(Browsers.Firefox)
public class Login extends BaseTest {

  @Test
  public void testLogin() {

      loginPageView.login("seleniumup@gmail.com", "testuser");
      mainView.isPresented(true);
  }

}
