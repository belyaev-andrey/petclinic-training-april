package com.company.clinic.service;

import com.company.clinic.entity.Visit;

import java.math.BigDecimal;

public interface VisitService {
    String NAME = "clinic_VisitService";

    BigDecimal calculateAmount(Visit visit);
}