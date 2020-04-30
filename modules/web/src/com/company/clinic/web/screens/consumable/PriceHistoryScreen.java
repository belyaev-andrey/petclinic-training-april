package com.company.clinic.web.screens.consumable;

import com.company.clinic.entity.PriceHistory;
import com.haulmont.charts.gui.components.charts.SerialChart;
import com.haulmont.charts.gui.data.ContainerDataProvider;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.core.global.TimeSource;
import com.haulmont.cuba.gui.components.Timer;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.screen.*;
import org.apache.commons.lang3.RandomUtils;

import javax.inject.Inject;
import java.math.BigDecimal;

@UiController("clinic_PriceHistoryScreen")
@UiDescriptor("price-history-screen.xml")
public class PriceHistoryScreen extends Screen {

    @Inject
    private CollectionContainer<PriceHistory> priceHistoriesDc;
    @Inject
    private SerialChart priceHistoryChart;
    @Inject
    private Metadata metadata;
    @Inject
    private TimeSource timeSource;

    @Subscribe
    public void onInit(InitEvent event) {
        priceHistoryChart.setDataProvider(new ContainerDataProvider(priceHistoriesDc));
        onUpdateChartTimerTimerAction(null);
    }

    @Subscribe(id = "updateChartTimer")
    public void onUpdateChartTimerTimerAction(Timer.TimerActionEvent event) {
        PriceHistory priceHistory = metadata.create(PriceHistory.class);
        priceHistory.setPrice(new BigDecimal(RandomUtils.nextInt(100, 500)));
        priceHistory.setTs(timeSource.currentTimestamp());

        priceHistoriesDc.getMutableItems().add(priceHistory);
    }


}