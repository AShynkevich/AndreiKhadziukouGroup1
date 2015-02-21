package com.epam.pattern.core.configuration;

import java.io.IOException;
import java.util.Properties;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;

/**
 * Created by Aliaksandr_Shynkevich on 2/21/15
 */
public class CinemaConfigurator {
    private static final Logger LOGGER = Logger.getLogger(CinemaConfigurator.class);
    private static final String LOCAL_HOST = "localhost";

    private ChannelConfiguration configuration = new ChannelConfiguration();

    public CinemaConfigurator() {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream("/channel.properties"));
        } catch (IOException e) {
            LOGGER.error("Lading property error: ", e);
        }
        init(properties);
    }

    private void init(Properties properties) {
        configuration.setBrokerReceiverHost(properties.getProperty("broker.receiver.address", LOCAL_HOST));
        configuration.setBrokerReceiverPort(NumberUtils
                .createInteger(properties.getProperty("broker.receiver.port", "9898")));
        configuration.setBrokerSenderHost(properties.getProperty("broker.sender.address", LOCAL_HOST));
        configuration.setBrokerSenderPort(NumberUtils
                .createInteger(properties.getProperty("broker.sender.port", "8989")));
    }

    public ChannelConfiguration getConfiguration() {
        return configuration;
    }
}
