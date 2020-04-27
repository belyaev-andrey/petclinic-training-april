package com.company.clinic.web.screens.veterinarian;

import com.haulmont.cuba.gui.screen.*;
import com.company.clinic.entity.Veterinarian;

@UiController("clinic_Veterinarian.browse")
@UiDescriptor("veterinarian-browse.xml")
@LookupComponent("veterinariansTable")
@LoadDataBeforeShow
public class VeterinarianBrowse extends StandardLookup<Veterinarian> {
}