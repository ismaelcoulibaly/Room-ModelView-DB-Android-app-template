package com.example.room.database.dao;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.room.model.Produit;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities	= {Produit.class},	version	= 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ProduitDao	produitDao();

    public static AppDatabase INSTANCE;
    /*
            Nous avons créé un ExecutorService avec un pool de threads fixe que vous utiliserez
            pour exécuter des opérations de base de données de manière asynchrone sur un thread d'arrière-plan.
            On fixe le nombrer des Threads pouvant éetre lancées en Arrière-plan
        */
    private static final int NUMBER_OF_THREAD = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREAD);


    public static synchronized AppDatabase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,
                    "produit_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(()->{
                ProduitDao produitDao = INSTANCE.produitDao();
                List<Produit> uneListe = new ArrayList<>();
                //personneDao.deleteAllProduit();
                uneListe.add(new Produit("Toto", "Tata", 12));
                uneListe.add(new Produit("John", "Doe", 32));
                uneListe.add(new Produit("Josue", "Lubaki", 44));


                produitDao.insertAllProduit((Produit) uneListe);

            });

        }
    };


}
