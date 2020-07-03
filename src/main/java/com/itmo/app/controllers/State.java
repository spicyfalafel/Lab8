package com.itmo.app.controllers;

import com.itmo.collection.Dragon;
import com.itmo.utils.MyListener;
import com.itmo.utils.Painter;
import javafx.collections.ObservableList;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class State {
    private ObservableList<Dragon> dragonsInTable;
    private String currentLang;
    private MyListener listener;
    private Painter painter;
}