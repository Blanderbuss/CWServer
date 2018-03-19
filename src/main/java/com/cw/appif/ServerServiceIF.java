package com.cw.appif;

import com.cw.exceptions.FighterException;
import com.cw.exceptions.UserException;
import com.cw.models.db.entities.Artefact;
import com.cw.models.db.entities.User;

import java.util.List;


public interface ServerServiceIF {

    boolean register(User user) throws UserException;

    boolean auth(User user) throws UserException;

    boolean addSet(String name, int lvl, List<Artefact> artefacts) throws FighterException;

    boolean regSet(int id) throws FighterException;
}
