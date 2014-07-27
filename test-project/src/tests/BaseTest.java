package tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import views.LoginPageView;
import views.MainView;
import core.test.BaseTestCase;

public class BaseTest extends BaseTestCase {

  LoginPageView loginPageView;
  MainView mainView;
  
  @BeforeTest
  public void setUp() throws Exception
  {
    super.setUp();
    
    initViews();
  }
  
  @AfterTest
  public void tearDown() throws Exception
  {
    super.tearDown();
  }

  public void initViews() {
    loginPageView = new LoginPageView();
    mainView = new MainView();
  }

}
