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
