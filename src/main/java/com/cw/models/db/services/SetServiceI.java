package com.cw.models.db.services;

import com.cw.models.db.entities.Set;
import com.cw.models.db.entities.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by denysmelnychenko on 2/11/18.
 */
@Service
public interface SetServiceI {
    Set getSetById(int id);
    List<Set> getAllSetsByUserId(int id);

    boolean addSet(Set set, int userId);

    boolean updateSet(Set set);

    boolean deleteSetById(int id);
    Set deleteSet(Set set);

    void closeConnection();
}
