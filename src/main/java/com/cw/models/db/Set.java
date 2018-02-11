package com.cw.models.db;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;

public class Set {
    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9_-]{3,15}")
    private String name;
    private long id;

    private String code;
    private int hp;
    private int mana;
    private int stamina;
    private int hpRegen;
    private int manaRegen;
    private double evasion;
    private int armor;

    public Set(long id, String name, String code, int hp, int mana, int stamina,
               int hpRegen, int manaRegen, double evasion, int armor){
        this.id = id;
        this.name = name;

        this.code = code;
        this.hp = hp;
        this.mana = mana;
        this.stamina = stamina;
        this.hpRegen = hpRegen;
        this.manaRegen = manaRegen;
        this.evasion = evasion;
        this.armor = armor;
    }
}
