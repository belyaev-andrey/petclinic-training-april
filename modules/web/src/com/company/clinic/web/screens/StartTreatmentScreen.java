package com.company.clinic.web.screens;

import com.company.clinic.entity.Pet;
import com.haulmont.addon.bproc.web.processform.OutputVariable;
import com.haulmont.addon.bproc.web.processform.ProcessForm;
import com.haulmont.addon.bproc.web.processform.ProcessFormContext;
import com.haulmont.addon.bproc.web.processform.ProcessVariable;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.LookupField;
import com.haulmont.cuba.gui.components.TextArea;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.security.global.UserSession;

import javax.inject.Inject;

@UiController("clinic_StartTreatmentScreen")
@UiDescriptor("start-treatment-screen.xml")
@LoadDataBeforeShow
@ProcessForm (
        outputVariables = {
                @OutputVariable(name = "description", type = String.class),
                @OutputVariable(name = "executor", type = User.class),
                @OutputVariable(name = "pet", type = Pet.class),
                @OutputVariable(name = "initiator", type = User.class),
        }
)
public class StartTreatmentScreen extends Screen {

    @Inject
    private ProcessFormContext processFormContext;

    @Inject
    @ProcessVariable(name = "description")
    private TextArea<String> descriptionField;

    @Inject
    @ProcessVariable(name = "executor")
    private LookupField<User> executorField;

    @Inject
    @ProcessVariable(name = "pet")
    private LookupField<Pet> petField;

    @Inject
    private UserSession userSession;

    @Subscribe("startProcess")
    public void onStartProcessClick(Button.ClickEvent event) {
        processFormContext.processStarting()
                .addProcessVariable("initiator", userSession.getUser())
                .saveInjectedProcessVariables()
                .start();
        closeWithDefaultAction();
    }



}