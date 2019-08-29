package com.github.hysrabbit.agecorrector.batch;

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class NoBatchJobInstance extends DefaultBatchConfigurer {
    @Override
    public void setDataSource(DataSource dataSouce) {}
}
