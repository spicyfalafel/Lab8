package com.itmo.app.controllers;

import com.itmo.client.Client;
import com.itmo.client.User;
import com.itmo.collection.Dragon;
import com.itmo.utils.MyListener;
import com.itmo.utils.Painter;
import javafx.collections.ObservableList;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class State {
    public Client client;
    private ObservableList<Dragon> dragonsInTable;
    private String currentLang;
    private MyListener listener;
    private Painter painter;
}