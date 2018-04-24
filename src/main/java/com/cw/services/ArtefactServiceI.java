package com.cw.services;

import com.cw.entities.Artefact;
import com.cw.entities.Set;
import com.cw.entities.User;

import java.util.List;

/**
 * Created by denysmelnychenko on 2/11/18.
 */
public interface ArtefactServiceI {

    Artefact getArtefactById(int id);
    List<Artefact> getAllArtefactsBySetId(int id);
    List<Artefact> getAllArtefactByUserId(int id);

    boolean addArtefact(Artefact artefact);
    boolean addArtefactToUserBackpack(Artefact artefact, User user);
    User addArtefactToUserBackpack(Artefact artefact, int user_id);
    boolean addArtefactToSet(Set set, Artefact artefact);
    Set addArtefactToSet(int set_id, Artefact artefact);
    boolean addArtefactsToSet(Set set, List<Artefact> artefactsId);

    boolean updateArtefact(Artefact artefact);

    boolean deleteArtefactById(int id);
    Artefact deleteArtefact(Artefact artefact);
    void closeConnection();
}
