package com.cw.models.db.services;

import com.cw.models.db.entities.Artefact;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by denysmelnychenko on 2/11/18.
 */
@Service
public interface ArtefactServiceI {

    Artefact getArtefactById(int id);
    List<Artefact> getAllArtefactsBySetId(int id);
    List<Artefact> getAllArtefactByUserId(int id);

    boolean addArtefact(Artefact artefact);
    boolean addArtefactToUserBackpack(int user_id, int artefact_id);
    boolean addArtefactToUserBackpack(int user_id, Artefact artefact);
    boolean addArtefactToSet(int set_id, int artefact_id);
    boolean addArtefactToSet(int set_id, Artefact artefact);
    boolean addArtefactsToSet(int set_id, List<Integer> artefactsId);

    boolean updateArtefact(Artefact artefact);

    boolean deleteArtefactById(int id);
    Artefact deleteArtefact(Artefact artefact);
    void closeConnection();
}
