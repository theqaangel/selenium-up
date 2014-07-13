package controls;

import org.openqa.selenium.By;

import controls.web.implementation.Button;
import controls.web.implementation.TextBox;

public class LoginPage {

  public TextBox email = new TextBox("Username", By.id("Email"));

  public TextBox password = new TextBox("Password", By.id("Passwd"));

  public Button signin = new Button("Sign in", By.id("signIn"));

}
