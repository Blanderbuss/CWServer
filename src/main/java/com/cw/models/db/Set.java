package com.cw.models.db;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Set {
    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9_-]{3,15}")
    private String name;

}
