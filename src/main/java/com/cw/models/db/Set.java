package com.cw.models.db;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;

public class Set {
    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9_-]{3,15}")
    private String name;
    @NotNull
    private long id;

    @NotNull
    private String code;

    public Set(String name, long id, String code) {
        this.name = name;
        this.id = id;
        this.code = code;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
