package com.bslota.refactoring.library;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.subethamail.wiser.Wiser;

@Configuration
public class SmtpConfig {

    @Bean
    public Wiser wiser() {
        final Wiser wiser = new Wiser();
        wiser.setHostname("localhost");
        wiser.setPort(10025);
        wiser.start();
        return wiser;
    }
}
