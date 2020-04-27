package com.company.clinic.web.screens.pet;

import com.haulmont.cuba.gui.screen.*;
import com.company.clinic.entity.Pet;

@UiController("clinic_Pet.edit")
@UiDescriptor("pet-edit.xml")
@EditedEntityContainer("petDc")
@LoadDataBeforeShow
public class PetEdit extends StandardEditor<Pet> {
}