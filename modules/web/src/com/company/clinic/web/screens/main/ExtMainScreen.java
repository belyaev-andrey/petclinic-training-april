package com.company.clinic.web.screens.main;

import com.company.clinic.entity.Pet;
import com.company.clinic.entity.Visit;
import com.company.clinic.service.VisitService;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.Dialogs;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.model.DataContext;
import com.haulmont.cuba.gui.model.InstanceContainer;
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
    private UserSession userSession;
    @Inject
    private CollectionContainer<Visit> visitsDc;
    @Inject
    private DataContext dataContext;
    @Inject
    private InstanceContainer<Visit> visitDc;
    @Inject
    private Notifications notifications;
    @Inject
    private DataManager dataManager;
    @Inject
    private Dialogs dialogs;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        visitsDl.load();
        petsDl.load();
        visitDc.setItem(createNewVisit());
    }

    @Subscribe("refreshAction")
    public void onRefreshAction(Action.ActionPerformedEvent event) {
        visitsDl.load();
        petsDl.load();
    }

    @Subscribe("scheduleAction")
    public void onScheduleAction(Action.ActionPerformedEvent event) {
        Visit item = visitDc.getItem();
        if (item.getPet() == null || item.getDate() == null) {
            notifications.create(Notifications.NotificationType.WARNING)
                    .withCaption("Please select both Pet and Visit Date")
                    .withHideDelayMs(100)
                    .show();
            return;
        }
        dataContext.commit(); //dataManager.commit(item);
        visitsDc.replaceItem(visitDc.getItem());
        visitDc.setItem(createNewVisit());
    }

    private Visit createNewVisit() {
        Visit visit = dataContext.create(Visit.class);
        visit.setHoursSpent(1);
        visit.setVeterinarian(visitService.findVetByUser(userSession.getUser()));
        visit.setAmount(visitService.calculateAmount(visit));
        return visit;
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

    @Subscribe("visitsCalendar")
    public void onVisitsCalendarCalendarEventMove(Calendar.CalendarEventMoveEvent<LocalDateTime> event) {
        Visit visit = (Visit) event.getEntity();
        if (visit != null) {
            dialogs.createOptionDialog()
                    .withCaption("Please confirm rescheduling")
                    .withMessage("Are you sure you want to reschedule the visit?")
                    .withType(Dialogs.MessageType.CONFIRMATION)
                    .withActions(
                            new DialogAction(DialogAction.Type.OK)
                                    .withHandler(e -> {
                                                LocalDateTime newStart = event.getNewStart();
                                                visit.setDate(newStart);
                                                visitsDc.replaceItem(dataManager.commit(visit));
                                            }
                                    ),
                            new DialogAction(DialogAction.Type.CANCEL).withHandler( e -> {
                                visitsDc.replaceItem(visit);
                            })
                    ).show();
        }
    }


}