package com.machine.topic;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.machine.topic.resource.TopicResource;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

/**
 * Created by rishabh.tripathi on 9/22/15.
 */
public class TopicExtractionServer extends Application<TopicExtractionConfiguration> {
    private Injector injector;

    public static void main(String[] args) throws Exception {
        new TopicExtractionServer().run(args);

    }

    @Override
    public String getName() {
        return getConfigurationClass().getName();
    }

    @Override
    public void initialize(Bootstrap<TopicExtractionConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(TopicExtractionConfiguration configuration,
                    Environment environment) {
        final DBI database = new DBIFactory().build(environment, configuration.getDatabase(), "database");
        final TopicExtractionModule topicExtractionModule = new TopicExtractionModule(configuration, environment, database);
        injector = Guice.createInjector(topicExtractionModule);

        // Register the resources
        environment.jersey().register(injector.getInstance(TopicResource.class));
    }
}
