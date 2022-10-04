package ru.razbezhkin.electronicqueue.UI;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.RequiredArgsConstructor;
import ru.razbezhkin.electronicqueue.UI.validator.PhoneNumberValidator;
import ru.razbezhkin.electronicqueue.domain.VisitorDto;
import ru.razbezhkin.electronicqueue.service.VisitorService;

import javax.annotation.PostConstruct;

@UIScope
@SpringComponent
@RequiredArgsConstructor
public class RegistrationLayout extends VerticalLayout {

    private final VisitorService visitorService;

    @PostConstruct
    public void init() {
        setSizeFull();
        setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);

        FormLayout regForm = new FormLayout();
        regForm.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 2));
        TextField login = new TextField("Login");
        PasswordField password = new PasswordField("Password");
        TextField firstName = new TextField("First Name");
        TextField lastName = new TextField("Last Name");
        TextField phoneNumber = new TextField("Phone Number");

        Binder<VisitorDto> binder = new Binder<>();
        String fieldMustBeSet = "Field must be set";
        binder.forField(login)
                .asRequired(fieldMustBeSet)
                .bind(VisitorDto::getLogin, VisitorDto::setLogin);
        binder.forField(password)
                .asRequired(fieldMustBeSet)
                .bind(VisitorDto::getPassword, VisitorDto::setPassword);
        binder.forField(firstName)
                .asRequired(fieldMustBeSet)
                .bind(VisitorDto::getFirstName, VisitorDto::setFirstName);
        binder.forField(lastName)
                .asRequired(fieldMustBeSet)
                .bind(VisitorDto::getLastName, VisitorDto::setLastName);
        binder.forField(phoneNumber)
                .withValidator(new PhoneNumberValidator())
                .asRequired(fieldMustBeSet)
                .bind(VisitorDto::getPhoneNumber, VisitorDto::setPhoneNumber);

        Button regButton = new Button("Register");
        regButton.setEnabled(false);

        regForm.add(login, password, firstName, lastName, phoneNumber);

        add(new H3("New user"),
                regForm,
                regButton);

        binder.addValueChangeListener(event -> regButton.setEnabled(binder.isValid()));

        regButton.addClickListener(event -> {
            VisitorDto newUser = new VisitorDto();
            binder.writeBeanIfValid(newUser);
            VisitorDto createdUser = visitorService.saveUser(newUser);
            Notification.show(createdUser != null ? "OK" : "ERROR", 5000, Notification.Position.BOTTOM_CENTER);
        });
    }
}
