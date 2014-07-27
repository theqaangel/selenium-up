package views;

import modules.reporter.Report;
import controls.MainPage;
import controls.web.implementation.BaseAutomationView;

public class MainView extends BaseAutomationView {

  MainPage mainPage = new MainPage();

  public void isPresented(boolean isPresented) {
    try {

      boolean actualState = mainPage.compose.isVisible();

      if (actualState == isPresented) {
        Report.pass("Main page is " + (actualState ? "presented" : "not presented")
            + " as expected");
      } else {
        Report.error("Main page is " + (actualState ? "presented" : "not presented"));
      }

    } catch (Exception ex) {
      Report.error("Main page presense can't be determined");
    }
  }

}
