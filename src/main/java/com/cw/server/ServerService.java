package com.cw.server;

import com.cw.appif.ServerServiceIF;
import com.cw.exceptions.AuthException;
import com.cw.models.User;

import java.util.ArrayList;

public class ServerService implements ServerServiceIF{

    static ArrayList<User> clients = new ArrayList<>();

    @Override
    public boolean auth(User user) throws AuthException {
        clients.add(user);
        System.out.println("hi");
        return true;
    }
}
