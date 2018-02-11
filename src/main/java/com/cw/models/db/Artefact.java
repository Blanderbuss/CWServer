package com.cw.models.db;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Artefact {
	
	@Min(0)
    private long id;
    @NotNull
    private int hpBoost;
    @NotNull
    private int staminaBoost;
    @NotNull
    @Max(value=500)
    @Min(value=(-500))
    private int hpRegenBoost;
    @NotNull
    @Max(value=500)
    @Min(value=(-500))
    private int manaRegenBoost;
    @NotNull
    private int evasionBoost;
    @NotNull
    private int armorBoost;
    @NotNull
    private String skin;

    private void setSkin(String skin) {
        this.skin = skin;
    }

    private void setId(long id) {
        this.id = id;
    }

    private void setHpBoost(int hpBoost) {
        this.hpBoost = hpBoost;
    }

    private void setStaminaBoost(int staminaBoost) {
        this.staminaBoost = staminaBoost;
    }

    private void setHpRegenBoost(int hpRegenBoost) {
        this.hpRegenBoost = hpRegenBoost;
    }

    private void setManaRegenBoost(int manaRegenBoost) {
        this.manaRegenBoost = manaRegenBoost;
    }

    private void setEvasionBoost(int evasionBoost) {
        this.evasionBoost = evasionBoost;
    }

    private void setArmorBoost(int armorBoost) {
        this.armorBoost = armorBoost;
    }

    public long getId() {
        return id;
    }

    public int getHpBoost() {
        return hpBoost;
    }

    public int getStaminaBoost() {
        return staminaBoost;
    }

    public int getHpRegenBoost() {
        return hpRegenBoost;
    }

    public int getManaRegenBoost() {
        return manaRegenBoost;
    }

    public int getEvasionBoost() {
        return evasionBoost;
    }

    public int getArmorBoost() {
        return armorBoost;
    }

    public String getSkin() {
        return skin;
    }

    public Artefact(long id, int hpBoost, int staminaBoost, int hpRegenBoost, int manaRegenBoost, int evasionBoost,
                    int armorBoost, String skin){
        this.id = id;
        this.hpBoost = hpBoost;
        this.staminaBoost = staminaBoost;
        this.hpRegenBoost = hpRegenBoost;
        this.manaRegenBoost = manaRegenBoost;
        this.evasionBoost = evasionBoost;
        this.armorBoost = armorBoost;
        this.skin = skin;

    }

}
