package com.company.clinic.web.screens.pettype;

import com.haulmont.cuba.gui.screen.*;
import com.company.clinic.entity.PetType;

@UiController("clinic_PetType.browse")
@UiDescriptor("pet-type-browse.xml")
@LookupComponent("petTypesTable")
@LoadDataBeforeShow
public class PetTypeBrowse extends StandardLookup<PetType> {
}