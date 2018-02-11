package com.cw.models.db;

public class Artifact {

    private long id;

    public Artifact(long id){
        this.id = id;
    }

    public boolean update(String id){
        return true;
    }

    // Getters must be here.
    public Artifact getArtifact(){
        return this;
    }

    public boolean delete(){
        return true;
    }

}
