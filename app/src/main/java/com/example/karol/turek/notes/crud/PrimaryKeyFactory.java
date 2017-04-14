package com.example.karol.turek.notes.crud;

import io.realm.Realm;

public class PrimaryKeyFactory {
    private long id;
    private Realm realm = Realm.getDefaultInstance();
    private static PrimaryKeyFactory istance;

    public static PrimaryKeyFactory getIstance(){
        if (istance == null){
            synchronized (PrimaryKeyFactory.class){
                if (istance == null){
                    istance = new PrimaryKeyFactory();
                }
            }
        }
        return istance;
    }

    public long nextKey(){
        long leftLimit = 1L;
        long rightLimit = 99999L;
        long generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
        return generatedLong;
    }
}
