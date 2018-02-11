package com.cw.models.db.services;

import java.util.ArrayList;

/**
 * Created by denysmelnychenko on 2/11/18.
 */
public interface SetService {
    public String getName();

    public boolean update();

    public boolean delete();

    public boolean checkCode(String code);

    public int getLvl(String name);

    ArrayList<ArtifactService> getAllArts(String name);
}
