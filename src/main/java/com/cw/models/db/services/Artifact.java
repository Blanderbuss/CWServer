package com.cw.models.db.services;

/**
 * Created by denysmelnychenko on 2/11/18.
 */
public interface Artifact {

    public boolean update(String id);

    // Getters must be here.
    public Artifact getArtifact();

    public boolean delete();

}
