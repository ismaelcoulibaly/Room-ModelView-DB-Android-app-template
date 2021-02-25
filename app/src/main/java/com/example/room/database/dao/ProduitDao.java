package com.example.room.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.room.model.Produit;

import java.util.List;

@Dao
public interface ProduitDao {

    @Query("SELECT	*	FROM	produitTable")
    LiveData<List<Produit>> getAllProduit();

    @Query("SELECT	*	FROM	produitTable	WHERE	id	IN	(:userIds)")
    List<Produit>	loadAllProduitByIds(int[]	userIds);

    @Query("SELECT	*	FROM	produitTable	WHERE	produitName	LIKE	:nomProduit")
    Produit	findProduitByName(String	nomProduit);

    @Insert
    void insertAllProduit(Produit...	produit);
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertProduit(Produit produit);

    @Delete
    void deleteProduit(Produit	produit);
    @Query("DELETE FROM produitTable")
    void deleteAllProduit();
}
