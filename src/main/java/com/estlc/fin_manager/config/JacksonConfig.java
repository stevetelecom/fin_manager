package com.estlc.fin_manager.config;

import org.springframework.boot.jackson.autoconfigure.JsonMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.ext.javatime.ser.LocalDateSerializer;
import tools.jackson.databind.ext.javatime.ser.LocalDateTimeSerializer;
import tools.jackson.databind.module.SimpleModule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Format des dates dans les réponses JSON.
 *
 * En entrée (requêtes), on continue d'accepter le format ISO :
 *   - LocalDate      : yyyy-MM-dd          (ex. 2026-11-03)
 *   - LocalDateTime  : yyyy-MM-ddTHH:mm:ss (ex. 2026-11-03T10:00:00)
 *
 * En sortie (réponses), les dates sont formatées :
 *   - LocalDate      : dd-MM-yyyy          (ex. 03-11-2026)
 *   - LocalDateTime  : dd-MM-yyyy HH:mm:ss (ex. 03-11-2026 10:00:00)
 */
@Configuration
public class JacksonConfig {

    public static final String DATE_TIME_PATTERN = "dd-MM-yyyy HH:mm:ss";
    public static final String DATE_PATTERN = "dd-MM-yyyy";

    @Bean
    public JsonMapperBuilderCustomizer dateFormatCustomizer() {
        return builder -> {
            SimpleModule module = new SimpleModule("fin-manager-dates");
            module.addSerializer(LocalDateTime.class,
                    new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)));
            module.addSerializer(LocalDate.class,
                    new LocalDateSerializer(DateTimeFormatter.ofPattern(DATE_PATTERN)));
            builder.addModule(module);
        };
    }
}
