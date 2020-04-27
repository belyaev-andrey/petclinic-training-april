package com.company.clinic.web.screens.consumable;

import com.haulmont.cuba.gui.screen.*;
import com.company.clinic.entity.Consumable;

@UiController("clinic_Consumable.edit")
@UiDescriptor("consumable-edit.xml")
@EditedEntityContainer("consumableDc")
@LoadDataBeforeShow
public class ConsumableEdit extends StandardEditor<Consumable> {
}