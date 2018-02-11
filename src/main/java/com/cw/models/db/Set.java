package com.cw.models.db;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;

public class Set {
    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9_-]{3,15}")
    private String name;
    private long id;

    public Set(long id, String name){
        this.id = id;
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public boolean update(){
        return true;
    }

    public boolean delete(){
        return true;
    }

    public boolean checkCode(String code){
        return true;
    }

    public int getLvl(String name){
        return 0;
    }

    ArrayList<Artifact> getAllArts(String name){
        return null;
    }
}
