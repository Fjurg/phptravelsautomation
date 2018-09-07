package phptravels.page;

import org.openqa.selenium.By;
import phptravels.driver.Driver;

public class LoginPage {

    public static void goTo() {
        Driver.instance.navigate().to("https://www.phptravels.net/admin");
    }

    public static LoginCommand loginAs(String userName) {
        return new LoginCommand(userName);
    }

    public static class LoginCommand {
        private String userName;
        private String password;

        public LoginCommand(String userName) {
            this.userName = userName;
        }

        public LoginCommand withPassword(String password) {
            this.password = password;
            return this;
        }

        public void login() {
            Driver.instance.findElement(By.name("email")).sendKeys(userName);
            Driver.instance.findElement(By.name("password")).sendKeys(password);
            Driver.instance.findElement(By.className("ladda-label")).click();
        }

    }
}
