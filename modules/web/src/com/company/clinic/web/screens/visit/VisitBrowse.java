package com.company.clinic.web.screens.visit;

import com.company.clinic.entity.Visit;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.reports.entity.Report;
import com.haulmont.reports.gui.ReportGuiManager;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@UiController("clinic_Visit.browse")
@UiDescriptor("visit-browse.xml")
@LookupComponent("visitsTable")
@LoadDataBeforeShow
public class VisitBrowse extends StandardLookup<Visit> {

    @Inject
    private ReportGuiManager reportGuiManager;
    @Inject
    private CollectionContainer<Visit> visitsDc;
    @Inject
    private DataManager dataManager;

    @Subscribe("visitsTable.printInvoice")
    public void onVisitsTablePrintInvoice(Action.ActionPerformedEvent event) {
        Map<String, Object> params = new HashMap<>();
        params.put("visit", visitsDc.getItem());
        Report report = dataManager.load(Report.class)
                .query("select r from report$Report r where r.name = :name")
                .parameter("name", "Invoice")
                .optional()
                .orElse(null);

        if (report != null) {
            reportGuiManager.printReport(report, params, this);
        }
    }

    @Install(to = "visitsTable.printInvoice", subject = "enabledRule")
    private boolean visitsTablePrintInvoiceEnabledRule() {
        return visitsDc.getItemOrNull() != null;
    }
}