package com.example.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.example.room.R;
import com.example.room.database.dao.AppDatabase;
import com.example.room.model.Produit;
import com.example.room.viewmodel.VMFactory;
import com.example.room.viewmodel.ViewModelProduit;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private ProduitAdapter adapter;
    private ViewModelProduit model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView =  findViewById(R.id.recyclerview);
        adapter = new ProduitAdapter(getApplication());

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        AppDatabase app = AppDatabase.getInstance(getApplication());

        VMFactory factory = new VMFactory(getApplication());
        model = new ViewModelProvider(this, factory).get(ViewModelProduit.class);
        model.getAllProduits().observe(this, produits -> {
            adapter.ajouterProduit(produits);
        });

        registerForContextMenu(recyclerView);
    }


    public List<Produit> getAllProduits(){
        List<Produit> listeProduit = new ArrayList<>();
        listeProduit.add(new Produit("Josu","boss 1.0", 100));
        listeProduit.add(new Produit("pcjo","lool", 20));
        listeProduit.add(new Produit("ismo","oo", 4));
        listeProduit.add(new Produit("kany","boss 1.0", 10));
        listeProduit.add(new Produit("Baki","asdf", 30));
        listeProduit.add(new Produit("mmm","boss 1.0", 300));
        listeProduit.add(new Produit("wwqe"," 1.0", 12));
        listeProduit.add(new Produit("qq","bete0", 99));
        listeProduit.add(new Produit("fd","bo", 21));
        listeProduit.add(new Produit("Jcu","bos", 45));

        return listeProduit;

    }





}