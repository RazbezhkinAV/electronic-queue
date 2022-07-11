package ru.razbezhkin.electronicqueue;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.spring.annotation.EnableVaadin;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ElectronicQueueApplication implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(ElectronicQueueApplication.class, args);
    }

}
