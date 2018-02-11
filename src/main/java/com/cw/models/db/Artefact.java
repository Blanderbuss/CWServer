package com.cw.models.db;

import javax.validation.constraints.Min;

public class Artefact {
	
	@Min(0)
    private long id;

    public Artefact(long id){
        this.id = id;
    }

    public boolean update(String id){
        return true;
    }

    // Getters must be here.
    public Artefact getArtifact(){
        return this;
    }

    public boolean delete(){
        return true;
    }

}
