package ua.ifit.lms.view;

import ua.ifit.lms.dao.entity.User;

public class LoginView {

    public String getloginPage(boolean isSuccessfullLogin) {
        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();
        String indBase = indexSingletonView.getIndexHtml();
        String loginForm = indexSingletonView.getLoginForm();
        String Menu = indexSingletonView.getMenuHtml();
        String LoginFormFail = indexSingletonView.getLoginformFail();
        return indBase
                .replace("<!--### insert html here ### -->", Menu)
                .replace("<!--### insert html here ### -->", isSuccessfullLogin ? loginForm : LoginFormFail);
    }

    public String welcomeUserPage(User user) {
        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();
        String Menu = indexSingletonView.getMenuHtml();
        String indBase = indexSingletonView.getIndexHtml();
        return indBase
                .replace("<!--### insert html here ### -->", Menu)
                .replace("<!--### insert html here ### -->", "Hello" + user.getName());
    }
}
