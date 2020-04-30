package com.company.clinic.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.BaseUuidEntity;

import java.math.BigDecimal;
import java.util.Date;

@MetaClass(name = "clinic_PriceHistory")
public class PriceHistory extends BaseUuidEntity {
    private static final long serialVersionUID = -3297451212135740431L;

    @MetaProperty
    protected Date ts;

    @MetaProperty
    protected BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }
}