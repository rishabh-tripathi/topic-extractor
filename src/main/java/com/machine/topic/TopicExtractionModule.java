package com.machine.topic;

import com.google.inject.AbstractModule;
import com.machine.topic.Util.TEUtil;
import com.machine.topic.dal.store.DataStore;
import com.machine.topic.dal.store.MySqlStore;
import com.machine.topic.resource.TopicResource;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

/**
 * Created by rishabh.tripathi on 9/22/15.
 */
public class TopicExtractionModule extends AbstractModule {
    final TopicExtractionConfiguration configuration;
    final Environment environment;
    final DBI database;
    public TopicExtractionModule(final TopicExtractionConfiguration configuration,
                                 final Environment environment,
                                 final DBI database) {

        this.configuration = configuration;
        this.environment = environment;
        this.database = database;
    }

    @Override
    protected void configure() {
        bind(TopicExtractionConfiguration.class).toInstance(configuration);

        // Bind Resources
        bind(TopicResource.class).asEagerSingleton();

        // Binding Util
        bind(TEUtil.class).asEagerSingleton();

        // Bind Database and DAOs
        bind(DBI.class).toInstance(database);

        // Bind DataStore
        bind(DataStore.class).to(MySqlStore.class);

    }
}

