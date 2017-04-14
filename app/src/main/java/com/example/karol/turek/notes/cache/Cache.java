package com.example.karol.turek.notes.cache;

import android.app.Application;

import com.example.karol.turek.notes.dto.NotesDto;

import java.util.LinkedList;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Cache extends Application
        implements ICache {
    private LinkedList<NotesDto> linkedList;
    private String valueString1, valueString2, valueString3, valueString4;
    private long valueLong1;

    @Override
    public void onCreate() {
        super.onCreate();
        linkedList = new LinkedList<>();
        Realm.init(getApplicationContext());
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("Notes")
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);
    }

    @Override
    public LinkedList<NotesDto> getLinkedList() {
        return linkedList;
    }

    @Override
    public void setLinkedList(LinkedList<NotesDto> linkedList) {
        this.linkedList = linkedList;
    }

    @Override
    public String getValueString1() {
        return valueString1;
    }

    @Override
    public void setValueString1(String valueString1) {
        this.valueString1 = valueString1;
    }

    @Override
    public String getValueString2() {
        return valueString2;
    }

    @Override
    public void setValueString2(String valueString2) {
        this.valueString2 = valueString2;
    }

    @Override
    public String getValueString3() {
        return valueString3;
    }

    @Override
    public void setValueString3(String valueString3) {
        this.valueString3 = valueString3;
    }

    @Override
    public String getValueString4() {
        return valueString4;
    }

    @Override
    public void setValueString4(String valueString4) {
        this.valueString4 = valueString4;
    }

    @Override
    public long getValueLong1() {
        return valueLong1;
    }

    @Override
    public void setValueLong1(long valueLong1) {
        this.valueLong1 = valueLong1;
    }

    @Override
    public void clearCache() {
        linkedList.clear();
        valueLong1 = 0;
        valueString1 = null;
        valueString2 = null;
        valueString3 = null;
        valueString4 = null;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        linkedList.clear();
    }
}
