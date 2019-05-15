package ua.ifit.lms.view;


public class RegisterView {

    public String getRegisterForm(){
    IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();
    String indBase = indexSingletonView.getIndexHtml();
    String Register = indexSingletonView.getRegisterForm();
    String Menu = indexSingletonView.getMenuHtml();

    return indBase
            .replace("<!--### insert html here ### -->", Menu).
                    replace("<!--### insert html here ### -->", Register);

}
}