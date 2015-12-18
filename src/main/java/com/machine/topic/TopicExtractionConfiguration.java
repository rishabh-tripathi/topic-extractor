package com.machine.topic;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
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

    @NotEmpty
    private String calisUrl;

    @NotEmpty
    private String calisAuth;

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

    @JsonProperty
    public String getCalisUrl() {
        return calisUrl;
    }

    @JsonProperty
    public void setCalisUrl(String calisUrl) {
        this.calisUrl = calisUrl;
    }

    @JsonProperty
    public String getCalisAuth() {
        return calisAuth;
    }

    @JsonProperty
    public void setCalisAuth(String calisAuth) {
        this.calisAuth = calisAuth;
    }
}
