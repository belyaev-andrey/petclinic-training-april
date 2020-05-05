package com.company.clinic.listeners;

import com.company.clinic.entity.Pet;
import com.company.clinic.entity.Visit;
import com.haulmont.addon.bproc.entity.ProcessDefinitionData;
import com.haulmont.addon.bproc.query.ProcessDefinitionDataQuery;
import com.haulmont.addon.bproc.service.BprocDmnRepositoryService;
import com.haulmont.addon.bproc.service.BprocManagementService;
import com.haulmont.addon.bproc.service.BprocRepositoryService;
import com.haulmont.addon.bproc.service.BprocRuntimeService;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.app.UniqueNumbersAPI;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.core.listener.AfterInsertEntityListener;
import com.haulmont.cuba.core.listener.BeforeInsertEntityListener;
import com.haulmont.cuba.security.entity.User;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.inject.Inject;
import java.sql.Connection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(VisitEntityListener.NAME)
public class VisitEntityListener implements BeforeInsertEntityListener<Visit>, AfterInsertEntityListener<Visit> {
    public static final String NAME = "clinic_VisitEntityListener";
    @Inject
    private UniqueNumbersAPI uniqueNumbersAPI;
    @Inject
    private BprocRuntimeService bprocRuntimeService;
    @Inject
    private UserSessionSource userSessionSource;
    @Inject
    private BprocRepositoryService bprocRepositoryService;
    @Inject
    private Logger log;
    private final String processDefinitionKey = "treatment";

    @Override
    public void onBeforeInsert(Visit entity, EntityManager entityManager) {
        entity.setNumber(uniqueNumbersAPI.getNextNumber("VISIT_NUMBER"));
    }

    @Override
    public void onAfterInsert(Visit entity, Connection connection) {
        User initiator = userSessionSource.checkCurrentUserSession()?
                userSessionSource.getUserSession().getUser():entity.getVeterinarian().getUser();
        User executor = entity.getVeterinarian().getUser();
        Pet pet = entity.getPet();
        String description = entity.getDescription();
        Map<String, Object> params = new HashMap<>();
        params.put("initiator", initiator);
        params.put("executor", executor);
        params.put("pet", pet);
        params.put("description", description);
        List<ProcessDefinitionData> definitionData = bprocRepositoryService.createProcessDefinitionDataQuery()
                .processDefinitionKey(processDefinitionKey)
                .latestVersion().list();
        if (!CollectionUtils.isEmpty(definitionData)) {
            bprocRuntimeService.startProcessInstanceByKey("treatment", params);
        } else {
            log.warn("Could not run process with key \"{}\". Definition is not in the database.", processDefinitionKey);
        }
    }
}
