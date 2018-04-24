package com.cw.db.dao;

import com.cw.entities.Artefact;
import com.cw.entities.Set;
import com.cw.entities.User;

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
    boolean addArtefactsToSet(Set set, List<Artefact> artefacts);

    boolean updateArtefact(Artefact artefact);

    boolean deleteArtefactById(int id);
}
