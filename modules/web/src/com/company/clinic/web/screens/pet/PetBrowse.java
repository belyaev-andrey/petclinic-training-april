package com.company.clinic.web.screens.pet;

import com.haulmont.cuba.gui.screen.*;
import com.company.clinic.entity.Pet;

@UiController("clinic_Pet.browse")
@UiDescriptor("pet-browse.xml")
@LookupComponent("petsTable")
@LoadDataBeforeShow
public class PetBrowse extends StandardLookup<Pet> {
}