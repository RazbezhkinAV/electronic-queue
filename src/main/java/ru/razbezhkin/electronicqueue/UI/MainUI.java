package ru.razbezhkin.electronicqueue.UI;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.Lumo;
import lombok.RequiredArgsConstructor;
import ru.razbezhkin.electronicqueue.service.TicketService;

import javax.annotation.PostConstruct;
import java.time.LocalTime;

@Route("/api/v2/")
@RequiredArgsConstructor
public class MainUI extends VerticalLayout {

    private final TicketService ticketService;

    @PostConstruct
    public void init() {

        getElement().getThemeList().add(Lumo.DARK);

        HorizontalLayout times = new HorizontalLayout();
        times.setDefaultVerticalComponentAlignment(Alignment.CENTER);

        FormLayout formLayout = new FormLayout();
        formLayout.setResponsiveSteps(new ResponsiveStep("0", 2));

        Button toggleButton = new Button("Toggle dark theme", click -> {
            ThemeList themeList = this.getElement().getThemeList();

            if (themeList.contains(Lumo.DARK)) {
                themeList.remove(Lumo.DARK);
            } else {
                themeList.add(Lumo.DARK);
            }
        });

        formLayout.setSizeFull();

        times.add(formLayout);

        times.setHeight("50%");
        times.setWidth("50%");

        add(new H1("Free Times"),
                toggleButton,
                times);
    }

    private Button createButtonTime(LocalTime time) {

        Button button = new Button(time.toString());

        return button;
    }
}
