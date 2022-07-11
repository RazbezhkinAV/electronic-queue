package ru.razbezhkin.electronicqueue.UI;


import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import javax.annotation.PostConstruct;

@Route("/")
public class MainUI extends VerticalLayout {

    public MainUI() {
    }

    @PostConstruct
    public void init(){
        add(new Label("Hello world"));
    }
}
