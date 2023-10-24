
package Repository;

import DataAccess.LoginDAO;
import java.util.Locale;

public class LoginRepository implements ILoginRepository{

    @Override
    public void login(Locale language) {
        LoginDAO.Instance().login(language);
    }
}
