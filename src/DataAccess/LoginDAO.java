package DataAccess;

import Model.User;
import common.Library;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;


public class LoginDAO {  
    private static LoginDAO instance = null;
    Library l;
    List<User> userList;

    public LoginDAO() {
        l = new Library();
        userList = new LinkedList<>();
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

//        String captchaGenerated = l.generateCaptchaText();
//        System.out.println("Captcha: " + captchaGenerated);
//        while (true) {
//            if (l.checkInputCaptcha(captchaGenerated, language)) {
//                l.getWordLanguage(language, "loginSuccess");
//                System.out.println();
//                userList.add(new User(accountNumber, passString, captchaGenerated));
//                return;
//            } else {
//                l.getWordLanguage(language, "errCaptchaIncorrect");
//                System.out.println("");
//            }
//        }

        String captchaGenerated = l.generateCaptchaText();
        System.out.println("Captcha: " + captchaGenerated);
        while (!l.checkInputCaptcha(captchaGenerated, language)) {
            l.getWordLanguage(language, "errCaptchaIncorrect");
            System.out.println("");
            captchaGenerated = l.generateCaptchaText();
            System.out.println("Captcha: " + captchaGenerated);
        }
        l.getWordLanguage(language, "loginSuccess");
        System.out.println();
        userList.add(new User(accountNumber, passString, captchaGenerated));
    }
}