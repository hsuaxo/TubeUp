package com.hsuaxo.tubeup;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface FavoriteDao {

    @Query("SELECT * FROM favorites ORDER BY name")
    public List<FavoriteContent> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertFavorites(FavoriteContent... users);

    @Delete
    public void deleteFavorites(FavoriteContent... users);
}