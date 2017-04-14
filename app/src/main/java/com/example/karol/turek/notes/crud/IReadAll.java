package com.example.karol.turek.notes.crud;

import com.example.karol.turek.notes.dto.NotesDto;

import java.util.LinkedList;

public interface IReadAll {
    LinkedList<NotesDto> read();
}
