package com.company.clinic.service;

import com.company.clinic.entity.Consumable;

import java.util.List;

public interface ConsumablesService {
    String NAME = "clinic_ConsumablesService";

    List<Consumable> getUsedConsumables();
}