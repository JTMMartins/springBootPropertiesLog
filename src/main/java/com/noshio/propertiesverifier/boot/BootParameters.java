package com.noshio.propertiesverifier.boot;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.env.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Configuration
public class BootParameters implements BeanFactoryPostProcessor, PriorityOrdered {
    private Logger logger = LoggerFactory.getLogger(BootParameters.class);

    /**
     *
     * @param configurableListableBeanFactory
     */
    @Override
    public void postProcessBeanFactory(final ConfigurableListableBeanFactory configurableListableBeanFactory)  {
        final ConfigurableEnvironment configurableEnvironment = configurableListableBeanFactory.getBean(ConfigurableEnvironment.class);
        final MutablePropertySources propSources =  configurableEnvironment.getPropertySources();
        // traversing the propertySources and collecting distinct properties.
        final Map<String,String> props = StreamSupport.stream(propSources.spliterator(), false)
                .filter(ps -> ps instanceof EnumerablePropertySource)
                .map(ps -> ((EnumerablePropertySource) ps).getPropertyNames())
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toMap(Function.identity(), configurableEnvironment::getProperty));
        //iterate over map and log each entry.
        props.entrySet()
                .stream()
                .map(entry ->
                        String.join(":", entry.getKey(),entry.getValue()))
                .forEach(logger::info);
    }

    @Override
    public int getOrder() {
        //we want to ensure this is the first BeanFactoryPostProcessor to be executed.
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
