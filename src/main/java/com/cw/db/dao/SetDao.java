package com.cw.db.dao;

import com.cw.entities.Set;
import com.cw.entities.User;

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
