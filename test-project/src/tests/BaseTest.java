package tests;

import views.LoginPageView;
import views.MainView;
import core.BaseTestCase;

public class BaseTest extends BaseTestCase {

  LoginPageView loginPageView;
  MainView mainView;

  @Override
  protected void setUp() throws Exception {
    super.setUp();

    initViews();
  }

  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void initViews() {
    loginPageView = new LoginPageView();
    mainView = new MainView();
  }

}
