package com.cw.models.db;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9_-]{3,15}")
    private String login;
    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9_.%+-]{6,30}")
    private String pass;
    @NotNull
    @Pattern(regexp = "[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}")
    private String email;
    @NotNull
    private int experience;
    @NotNull
    @Max(value = 80)
    private int lvl;

    public User() {
    }

    public User(String login, String pass, String email, int experience, int lvl) {
        this.login = login;
        this.pass = pass;
        this.email = email;
        this.experience = experience;
        this.lvl = lvl;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return getLogin();
    }
}
