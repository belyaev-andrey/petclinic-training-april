package com.company.clinic.web.screens.main;

import com.company.clinic.entity.Pet;
import com.company.clinic.entity.Visit;
import com.company.clinic.service.VisitService;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.Calendar;
import com.haulmont.cuba.gui.components.DateField;
import com.haulmont.cuba.gui.components.LookupField;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.security.global.UserSession;
import com.haulmont.cuba.web.app.main.MainScreen;

import javax.inject.Inject;
import java.sql.Date;
import java.time.LocalDateTime;


@UiController("extMainScreen")
@UiDescriptor("ext-main-screen.xml")
public class ExtMainScreen extends MainScreen {

    @Inject
    private CollectionLoader<Pet> petsDl;
    @Inject
    private CollectionLoader<Visit> visitsDl;
    @Inject
    private ScreenBuilders screenBuilders;
    @Inject
    private VisitService visitService;
    @Inject
    private LookupField<Pet> petSelector;
    @Inject
    private DateField<LocalDateTime> dateSelector;
    @Inject
    private UserSession userSession;
    @Inject
    private CollectionContainer<Visit> visitsDc;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        visitsDl.load();
        petsDl.load();
    }

    @Subscribe("refreshAction")
    public void onRefreshAction(Action.ActionPerformedEvent event) {
        visitsDl.load();
        petsDl.load();
    }

    @Subscribe("scheduleAction")
    public void onScheduleAction(Action.ActionPerformedEvent event) {
        Visit visit = visitService.scheduleVisit(petSelector.getValue(), dateSelector.getValue(), userSession.getUser());
        visitsDc.getMutableItems().add(visit);

        petSelector.setValue(null);
        dateSelector.setValue(null);
    }


    @Subscribe("visitsCalendar")
    public void onVisitsCalendarCalendarEventClick(Calendar.CalendarEventClickEvent<LocalDateTime> event) {
        Visit visit = (Visit) event.getEntity();

        if (visit == null) {
            return;
        }

        Screen screen = screenBuilders.editor(Visit.class, this)
                .editEntity(visit)
                .withOpenMode(OpenMode.DIALOG)
                .build();

        screen.addAfterCloseListener(afterCloseEvent -> {
           visitsDl.load();
        });

        screen.show();
    }



}