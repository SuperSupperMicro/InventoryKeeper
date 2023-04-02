package com.heftyb.inventorykeeper.Auth;

import com.heftyb.inventorykeeper.config.AuditorAwareImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * We declare our implementation of AuditorAware here as a bean and specify the
 * bean's name in the "auditorAwareRef" parameter of the EnableJpaAuditing annotation
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AuditingConfig {
    @Bean
    AuditorAware<Long> auditorProvider() {
        return new AuditorAwareImp();
    }
}
