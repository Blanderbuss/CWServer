package com.cw.models.db.dao;

import com.cw.models.db.entities.Artefact;

import java.util.List;

/**
 * Created by Макс on 09.03.2018.
 */
public interface ArtefactDAO {
    Artefact getArtefactById(int id);

    boolean addArtefact(Artefact artefact);

    boolean updateArtefact(Artefact artefact);

    boolean deleteArtefactById(int id);
    Artefact deleteArtefact(Artefact artefact);
}
