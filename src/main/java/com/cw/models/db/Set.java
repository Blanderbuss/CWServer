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
    @NotNull
    private int hp;
    @NotNull
    private int mana;
    @NotNull
    private int stamina;
    @NotNull
    @Max(value=500)
    @Min(value=(-500))
    private int hpRegen;
    @NotNull
    @Max(value=500)
    @Min(value=(-500))
    private int manaRegen;
    @NotNull
    private double evasion;
    @NotNull
    private int armor;

    private void setName(String name) {
        this.name = name;
    }

    private void setId(long id) {
        this.id = id;
    }

    private void setCode(String code) {
        this.code = code;
    }

    private void setHp(int hp) {
        this.hp = hp;
    }

    private void setMana(int mana) {
        this.mana = mana;
    }

    private void setStamina(int stamina) {
        this.stamina = stamina;
    }

    private void setHpRegen(int hpRegen) {
        this.hpRegen = hpRegen;
    }

    private void setManaRegen(int manaRegen) {
        this.manaRegen = manaRegen;
    }

    private void setEvasion(double evasion) {
        this.evasion = evasion;
    }

    private void setArmor(int armor) {
        this.armor = armor;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public int getHp() {
        return hp;
    }

    public int getMana() {
        return mana;
    }

    public int getStamina() {
        return stamina;
    }

    public int getHpRegen() {
        return hpRegen;
    }

    public int getManaRegen() {
        return manaRegen;
    }

    public double getEvasion() {
        return evasion;
    }

    public int getArmor() {
        return armor;
    }

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
