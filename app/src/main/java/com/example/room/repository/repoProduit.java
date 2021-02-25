package com.example.room.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;

import com.example.room.database.dao.AppDatabase;
import com.example.room.database.dao.ProduitDao;
import com.example.room.model.Produit;

import java.util.List;

public class repoProduit {

    private ProduitDao produitDao;
    private LiveData<List<Produit>> allProduits;

    public repoProduit(Application application){
        AppDatabase db = AppDatabase.getInstance(application);
        produitDao = db.produitDao();
        allProduits = produitDao.getAllProduit();

    }

    public LiveData<List<Produit>> getAllProduits() {
        return allProduits;
    }

    public void  insertProduit(Produit produit){

        AppDatabase.databaseWriteExecutor.execute(() -> {
            produitDao.insertProduit(produit);
        });
    }

    public void  deleteProduit(Produit produit){

        AppDatabase.databaseWriteExecutor.execute(() -> {
            produitDao.deleteProduit(produit);
        });
    }






}
