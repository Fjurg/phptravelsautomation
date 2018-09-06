package phptravels;

import org.openqa.selenium.By;

public class LoginPage {

    public static void goTo() {
        Driver.instance.navigate().to("https://www.phptravels.net/admin");
    }

    public static LoginData loginAs(String userName) {
        return new LoginData(userName);
    }

    public static class LoginData {
        private String userName;
        private String password;

        public LoginData(String userName) {
            this.userName = userName;
        }

        public LoginData withPassword(String password) {
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
