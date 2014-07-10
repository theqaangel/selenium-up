package controls.web.implementation;

public class BaseAutomationView {

  @SuppressWarnings("unused")
  private String viewName;

  public BaseAutomationView() {
    this.viewName = this.getClass().getSimpleName();
  }
}
