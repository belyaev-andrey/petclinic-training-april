package com.company.clinic.web.screens.owner;

import com.haulmont.cuba.gui.screen.*;
import com.company.clinic.entity.Owner;

@UiController("clinic_Owner.edit")
@UiDescriptor("owner-edit.xml")
@EditedEntityContainer("ownerDc")
@LoadDataBeforeShow
public class OwnerEdit extends StandardEditor<Owner> {
}