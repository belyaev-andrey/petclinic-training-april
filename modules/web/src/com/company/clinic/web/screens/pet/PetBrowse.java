package com.company.clinic.web.screens.pet;

import com.haulmont.charts.gui.components.charts.Chart;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.screen.*;
import com.company.clinic.entity.Pet;

import javax.inject.Inject;

@UiController("clinic_Pet.browse")
@UiDescriptor("pet-browse.xml")
@LookupComponent("petsTable")
@PrimaryLookupScreen(Pet.class)
@LoadDataBeforeShow
public class PetBrowse extends StandardLookup<Pet> {

    @Inject
    private Notifications notifications;

    @Subscribe("petsPieChart")
    public void onPetsPieChartSliceClick(Chart.SliceClickEvent event) {
        notifications.create()
        .withCaption(event.getDataItem().getValue("petName").toString())
        .show();
    }
}