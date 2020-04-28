package com.company.clinic.web.screens.owner;

import com.haulmont.cuba.gui.components.ValidationException;
import com.haulmont.cuba.gui.screen.*;
import com.company.clinic.entity.Owner;
import org.apache.commons.lang3.StringUtils;

@UiController("clinic_Owner.edit")
@UiDescriptor("owner-edit.xml")
@EditedEntityContainer("ownerDc")
@LoadDataBeforeShow
public class OwnerEdit extends StandardEditor<Owner> {

    @Install(to = "nameField", subject = "validator")
    private void nameFieldValidator(String string) throws ValidationException{
        if (!StringUtils.isAlphaSpace(string)) {
            throw new ValidationException("Name must contain only letter");
        }
    }
}