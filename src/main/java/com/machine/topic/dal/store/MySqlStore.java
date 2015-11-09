package com.machine.topic.dal.store;

import com.google.inject.Inject;
import com.machine.topic.TopicExtractionConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by rishabh.tripathi on 9/25/15.
 */
public class MySqlStore implements DataStore {
    private final static Logger LOGGER = LoggerFactory.getLogger(MySqlStore.class);
    private TopicExtractionConfiguration configuration;

    @Inject
    public void setConfiguration(TopicExtractionConfiguration configuration) {
        this.configuration = configuration;
    }


}
