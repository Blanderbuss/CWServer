package com.cw.server;

import com.cw.models.User;
import models.Fighter;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class CWServer{



    /*@Override
    public void register(RemoteClientIF client) throws RemoteException {
        clients.add(client);
        System.out.println("Added client:" + client);
        clients.get(0).sendFighter(new Fighter());
        System.out.println("Fighter sent");
    }

    public CWServer(){
    }

    public void init() throws RemoteException {
        CWServer stub = (CWServer) UnicastRemoteObject.exportObject(this, 4040);
        Registry reg = LocateRegistry.createRegistry(4040);
        reg.rebind("/server", stub);
        System.out.println("Server is ready");
    }*/
}
