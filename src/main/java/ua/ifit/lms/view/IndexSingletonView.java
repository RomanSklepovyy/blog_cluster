package ua.ifit.lms.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IndexSingletonView {

    private String path;
    private String indexHtml;
    private String loginForm;
    private String note;
    private String menu;
    private String loginformfail;
    private String register;

    private static IndexSingletonView ourInstance = new IndexSingletonView();

    public static IndexSingletonView getInstance() {
        return ourInstance;
    }

    private IndexSingletonView() {
    }

    public void setPath(String path) {
        this.path = path;
        this.indexHtml = getPartialHtml("index");
        this.note = getPartialHtml("note-form");
        this.menu = getPartialHtml("menu");
        this.loginForm = getPartialHtml("login-form");
        this.loginformfail = getPartialHtml("login-form-fail");
        this.register = getPartialHtml(register);
    }

    public String getIndexHtml() {
        return indexHtml;
    }

    public String getLoginForm() {
        return loginForm;
    }

    public String getNoteHtml() {return note;}

    public String getMenuHtml() {return menu;}

    public String getLoginformFail() {return loginformfail;}

    public String getRegisterForm() {return register;}

    private String getPartialHtml(String filename){
        StringBuilder strb = new StringBuilder();
        Path file = Paths.get(this.path + filename + ".html");
        Charset charset = Charset.forName("UTF-8");

        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                strb.append(line).append("\n");
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }

        return strb.toString();
    }
}
