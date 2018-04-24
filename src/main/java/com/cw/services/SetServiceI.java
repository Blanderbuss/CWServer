package com.cw.services;

import com.cw.entities.Set;
import com.cw.entities.User;

import java.util.List;

/**
 * Created by denysmelnychenko on 2/11/18.
 */
public interface SetServiceI {
    Set getSetById(int id);
    List<Set> getAllSetsByUserId(int id);

    User addSet(Set set, int userId);
    boolean addSet(Set set, User user);

    boolean updateSet(Set set);

    boolean updateSetArtifacts(Set set);

    boolean deleteSetById(int id);
    Set deleteSet(Set set);

    void closeConnection();
}
