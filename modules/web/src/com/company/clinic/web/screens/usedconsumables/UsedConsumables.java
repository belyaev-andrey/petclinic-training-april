package com.company.clinic.web.screens.usedconsumables;

import com.company.clinic.entity.Consumable;
import com.company.clinic.entity.Visit;
import com.company.clinic.service.ConsumablesService;
import com.haulmont.cuba.core.entity.KeyValueEntity;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.ValueLoadContext;
import com.haulmont.cuba.core.global.View;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.HBoxLayout;
import com.haulmont.cuba.gui.components.RowsCount;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.KeyValueCollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.web.gui.components.JavaScriptComponent;
import com.vaadin.shared.ui.JavaScriptComponentState;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Slider;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@UiController("clinic_UsedConsumables")
@UiDescriptor("used-consumables.xml")
@LoadDataBeforeShow
public class UsedConsumables extends Screen {

    @Inject
    private HBoxLayout topBox;
    @Inject
    private DataManager dataManager;
    @Inject
    private JavaScriptComponent moneySelector;
    @Inject
    private KeyValueCollectionLoader consumablesDl;

    private Slider slider;

    private Double valueFilter = 0.0;

    @Subscribe
    public void onInit(InitEvent event) {
        initVaadinComponent();
        MoneyMaskOptions options = new MoneyMaskOptions();
        options.prefix = "$";
        moneySelector.setState(options);
        moneySelector.addFunction("onValueChange", callbackEvent ->
        {
            //Not fancy component, does not store its value in is options
            //So we have to store it in the screen
            valueFilter = callbackEvent.getArguments().getNumber(0);
            consumablesDl.load();
        });
    }

    private void initVaadinComponent() {
        slider = new Slider();
        slider.setMin(0);
        slider.setMax(10);
        slider.setValue((double) 0);
        slider.addValueChangeListener(changedEvent -> {
            consumablesDl.load();
        });
        topBox.unwrap(AbstractOrderedLayout.class).addComponent(slider, 1);
    }

    @Install(to = "consumablesDl", target = Target.DATA_LOADER)
    private List<KeyValueEntity> consumablesDlLoadDelegate(ValueLoadContext valueLoadContext) {

        return dataManager.loadValues(
                valueLoadContext
                        .setQuery(
                                ValueLoadContext.createQuery(
                                        "select distinct " +
                                                "c.title as title, " +
                                                "c.description as description, " +
                                                "c.price as price, " +
                                                "count(v.id) as usages " +
                                                "from clinic_Visit v join v.consumables c " +
                                                "where @between(v.createTs, now-7, now+1, day) " +
                                                "and price >= :consPrice " +
                                                "group by title, description, price " +
                                                "having count(v.id) >= :usageCount")
                                        .setParameter("usageCount", slider.getValue().intValue())
                                        .setParameter("consPrice", valueFilter)));
    }

    /**
     * Class that partially represents the structure for passing state to JS component.
     * See JS file:
     *             prefix: "",
     *             suffix: "",
     *             affixesStay: true,
     *             thousands: ",",
     *             decimal: ".",
     *             precision: 2,
     *             allowZero: false,
     *             allowNegative: false,
     *             doubleClickSelection: true,
     *             allowEmpty: false,
     *             bringCaretAtEndOnFocus: true,
     */
    static class MoneyMaskOptions extends JavaScriptComponentState {
        public String suffix = "";
        public String prefix = "";
    }

}
