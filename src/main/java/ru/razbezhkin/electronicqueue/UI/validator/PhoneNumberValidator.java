package ru.razbezhkin.electronicqueue.UI.validator;

import com.vaadin.flow.data.validator.RegexpValidator;

public class PhoneNumberValidator extends RegexpValidator {

    private static final String PATTERN = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";

    private static final String MESSAGE = "Phone Number is invalid";

    public PhoneNumberValidator() {
        super(MESSAGE, PATTERN, true);
    }
}
