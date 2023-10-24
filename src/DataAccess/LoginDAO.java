package DataAccess;

import common.Library;
import java.util.Locale;


public class LoginDAO {  
    private static LoginDAO instance = null;
    Library l;

    public LoginDAO() {
        l = new Library();
    }

    public static LoginDAO Instance() {
        if (instance == null) {
            synchronized (LoginDAO.class) {
                if (instance == null) {
                    instance = new LoginDAO();
                }
            }
        }
        return instance;
    }

    public void login(Locale language) {
        l.getWordLanguage(language, "enterAccountNumber");
        int accountNumber = l.checkInputAccount(language);
        l.getWordLanguage(language, "enterPassword");
        String passString = l.checkInputPassword(language);
        String captchaGenerated = l.generateCaptchaText();
        while (true) {
            if (l.checkInputCaptcha(captchaGenerated, language)) {
                l.getWordLanguage(language, "loginSuccess");
                System.out.println();
                return;
            } else {
                l.getWordLanguage(language, "errCaptchaIncorrect");
                System.out.println();
            }
        }
    }
}
