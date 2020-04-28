package com.company.clinic.web.screens.veterinarian;

import com.haulmont.cuba.gui.screen.*;
import com.company.clinic.entity.Veterinarian;

import java.math.BigDecimal;

@UiController("clinic_Veterinarian.edit")
@UiDescriptor("veterinarian-edit.xml")
@EditedEntityContainer("veterinarianDc")
@LoadDataBeforeShow
public class VeterinarianEdit extends StandardEditor<Veterinarian> {

    @Subscribe
    public void onInitEntity(InitEntityEvent<Veterinarian> event) {
        event.getEntity().setHourlyRate(BigDecimal.TEN);
    }

}