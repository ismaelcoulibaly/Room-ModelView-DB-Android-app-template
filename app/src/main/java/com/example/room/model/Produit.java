package com.example.room.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "produitTable")
public class Produit {

    @PrimaryKey(autoGenerate = true)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ColumnInfo(name = "produitName")
    private String nom;

    @ColumnInfo(name = "produitDescription")
    private String description;

    @ColumnInfo(name = "produitPrix")
    private int prix;

    public Produit(String nom, String description, int prix) {

        this.nom = nom;
        this.description = description;
        this.prix = prix;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }
}
