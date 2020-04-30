package com.company.clinic.web.screens.bproc.treatment;

import com.haulmont.addon.bproc.service.BprocRuntimeService;
import com.haulmont.addon.bproc.web.processform.*;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.LookupField;
import com.haulmont.cuba.gui.components.TextArea;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;

@UiController("clinic_DoTreatmentScreen")
@UiDescriptor("do-treatment-screen.xml")
@ProcessForm(
        outcomes = {
                @Outcome(id = "complete"),
                @Outcome(id = "requestDetails", outputVariables = {
                        @OutputVariable(name = "comment", type = String.class)
                }
                )
        }
)
public class DoTreatmentScreen extends Screen {

    @Inject
    @ProcessVariable(name = "pet")
    private LookupField petField;

    @Inject
    @ProcessVariable(name = "description")
    private TextArea<String> descriptionField;

    @Inject
    private ProcessFormContext processFormContext;

    @Inject
    @ProcessVariable(name = "comment")
    private TextArea<String> commentField;

    @Inject
    private BprocRuntimeService bprocRuntimeService;

    @Subscribe("completeProcess")
    public void onCompleteProcessClick(Button.ClickEvent event) {
        processFormContext.taskCompletion()
                .withOutcome("complete")
                .complete();
        closeWithDefaultAction();
    }

    @Subscribe("needInfo")
    public void onNeedInfoClick(Button.ClickEvent event) {
        processFormContext.taskCompletion()
                .withOutcome("requestDetails")
                .addProcessVariable("comment", commentField.getValue())
                .complete();
        closeWithDefaultAction();
    }


}