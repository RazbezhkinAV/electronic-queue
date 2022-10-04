package ru.razbezhkin.electronicqueue.UI;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.RequiredArgsConstructor;
import ru.razbezhkin.electronicqueue.security.SecurityService;

import javax.annotation.PostConstruct;

@Route("login")
@PageTitle("Authorization")
@UIScope
@RequiredArgsConstructor
public class LoginUI extends VerticalLayout {

    private final RegistrationLayout registrationLayout;
    private final SecurityService securityService;

    @PostConstruct
    public void init() {
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        VerticalLayout loginLayout = new VerticalLayout();
        TextField login = new TextField("Login");
        PasswordField password = new PasswordField("Password");

        Button regButton = new Button("Registration");

        Dialog regDialog = new Dialog();
        regDialog.add(registrationLayout);

        regButton.addClickListener(event -> regDialog.open());

        Button loginButton = new Button("Login");

        loginButton.addClickListener(event -> {
            securityService.authenticate(login.getValue(),password.getValue());
        });

        HorizontalLayout buttonLayout = new HorizontalLayout(regButton, loginButton);

        add(login, password, buttonLayout);
    }

}
