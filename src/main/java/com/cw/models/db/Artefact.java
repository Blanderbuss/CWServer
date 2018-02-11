package com.cw.models.db;

import javax.validation.constraints.Min;

public class Artefact {
	
	@Min(0)
    private long id;
    private int hpBoost;
    private int staminaBoost;
    private int hpRegenBoost;
    private int manaRegenBoost;
    private int evasionBoost;
    private int armorBoost;
    private String skin;

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
