package com.cw.models.db.dao;

import com.cw.models.db.entities.Set;
import com.cw.models.db.entities.User;

import java.util.List;

/**
 * Created by Макс on 09.03.2018.
 */
public interface SetDao {
    Set getSetById(int id);

    boolean addSet(Set set, int userId);

    boolean updateSet(Set set);

    boolean deleteSetById(int id);
    Set deleteSet(Set set);
}
