package com.cw.models.db.services;

/**
 * Created by denysmelnychenko on 2/11/18.
 */
public interface UserService {
    public String getLogin();

    public void setLogin(String login);

    public String getPass();

    public void setPass(String pass);

    public String getEmail();

    public void setEmail(String email);

    @Override
    public String toString();
}
