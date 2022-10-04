package ru.razbezhkin.electronicqueue.UI;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import ru.razbezhkin.electronicqueue.security.SecurityService;

import javax.annotation.PostConstruct;

@Scope("prototype")
@SpringComponent
@RequiredArgsConstructor
public class HeaderLogout extends HorizontalLayout {

    private final SecurityService securityService;
    @Getter
    private final Button buttonRight = new Button("Logout");

    @PostConstruct
    public void init () {
        setJustifyContentMode(JustifyContentMode.END);
        setClassName("header");
        setUpContent();
        add(buttonRight);
    }

    private void setUpContent () {
        buttonRight.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        buttonRight.addClickListener(event -> securityService.logout());
        getStyle().set("margin-right", "10px")
                .set("margin-inline-start", "auto");
    }
}
