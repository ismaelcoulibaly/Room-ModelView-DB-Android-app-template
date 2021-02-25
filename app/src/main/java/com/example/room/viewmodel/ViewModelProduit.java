package com.example.room.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.room.model.Produit;
import com.example.room.repository.repoProduit;

import java.util.List;

public class ViewModelProduit extends AndroidViewModel {
    private repoProduit repo;
    private LiveData<List<Produit>> allProduits;

    public ViewModelProduit(@NonNull Application application) {
        super(application);
        repo = new repoProduit(application);
        allProduits = repo.getAllProduits();
    }

    public LiveData<List<Produit>> getAllProduits() {
        return allProduits;
    }


    public void insertProduit(Produit unProduit){

        repo.insertProduit(unProduit);// on utilise les methodes des classes dans les couches inferieures
    }


}
