package com.cw.models.db.services;

/**
 * Created by denysmelnychenko on 2/11/18.
 */
public interface BattleTypeService {

    // Getters must be here.
    public BattleTypeService getBT();

    public boolean update(long id);

    public boolean delete(long id);

    public long getQuantity(long id);

}
