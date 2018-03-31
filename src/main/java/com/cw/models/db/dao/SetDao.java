package com.cw.models.db.dao;

import com.cw.models.entities.Set;
import com.cw.models.entities.User;

import java.util.List;

/**
 * Created by Макс on 09.03.2018.
 */
public interface SetDao {
    Set getSetById(int id);

    List<Set> getAllSetsByUserId(int id);

    boolean addSet(Set set, User user);

    boolean updateSet(Set set);

    boolean deleteSetById(int id);
}
