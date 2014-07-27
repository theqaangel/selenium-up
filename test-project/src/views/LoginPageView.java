package views;

import modules.reporter.Report;
import controls.LoginPage;
import controls.web.implementation.BaseAutomationView;

public class LoginPageView extends BaseAutomationView {

  LoginPage loginPage = new LoginPage();

  public void login(String email, String password) {
    try {
      loginPage.email.enter(email);
      loginPage.password.enter(password);
      loginPage.signin.click();

    } catch (Exception ex) {
      Report.error("User can't be logged in");
    }
  }

}
