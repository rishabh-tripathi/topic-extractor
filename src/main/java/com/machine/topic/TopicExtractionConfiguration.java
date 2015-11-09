package com.machine.topic;
import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by rishabh.tripathi on 9/22/15.
 */
public class TopicExtractionConfiguration extends Configuration {
    @NotEmpty
    private String serviceName;

    @Valid
    @NotNull
    @JsonProperty("database")
    private DataSourceFactory database = new DataSourceFactory();

    @JsonProperty
    public String getServiceName() {
        return serviceName;
    }

    @JsonProperty
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @JsonProperty
    public DataSourceFactory getDatabase() {
        return database;
    }

    @JsonProperty
    public void setDatabase(DataSourceFactory database) {
        this.database = database;
    }
}
