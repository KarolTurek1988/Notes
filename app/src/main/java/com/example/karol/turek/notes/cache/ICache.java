package com.example.karol.turek.notes.cache;

import com.example.karol.turek.notes.dto.NotesDto;

import java.util.LinkedList;

public interface ICache {
    LinkedList<NotesDto> getLinkedList();
    void setLinkedList(LinkedList<NotesDto> linkedList);
    public String getValueString1();
    public void setValueString1(String valueString1);
    public String getValueString2();
    public void setValueString2(String valueString2);
    public String getValueString3();
    public void setValueString3(String valueString3);
    public String getValueString4();
    public void setValueString4(String valueString4);
    public long getValueLong1();
    public void setValueLong1(long valueLong1);
    void clearCache();
}
