package com.cw.models.db.services;

import java.util.ArrayList;

/**
 * Created by denysmelnychenko on 2/11/18.
 */
public interface Set {
    public String getName();

    public boolean update();

    public boolean delete();

    public boolean checkCode(String code);

    public int getLvl(String name);

    ArrayList<Artifact> getAllArts(String name);
}
