package com.cw.models.db.dao;

import com.cw.models.entities.Artefact;
import com.cw.models.entities.Set;
import com.cw.models.entities.User;

import java.util.List;

/**
 * Created by Макс on 09.03.2018.
 */
public interface ArtefactDAO {
    Artefact getArtefactById(int id);
    List<Artefact> getAllArtefactsBySetId(int id);
    List<Artefact> getAllArtefactByUserId(int id);

    boolean addArtefact(Artefact artefact);
    boolean addArtefactToUserBackpack(User user, Artefact artefact);
    boolean addArtefactToSet(Set set, Artefact artefact);
    boolean addArtefactsToSet(Set set, List<Integer> artefactsId);

    boolean updateArtefact(Artefact artefact);

    boolean deleteArtefactById(int id);
}
