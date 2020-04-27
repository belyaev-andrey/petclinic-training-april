package com.company.clinic.web.screens.consumable;

import com.haulmont.cuba.gui.screen.*;
import com.company.clinic.entity.Consumable;

@UiController("clinic_Consumable.browse")
@UiDescriptor("consumable-browse.xml")
@LookupComponent("consumablesTable")
@LoadDataBeforeShow
public class ConsumableBrowse extends StandardLookup<Consumable> {
}