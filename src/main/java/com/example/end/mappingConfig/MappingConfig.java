package com.example.end.mappingConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.mapstruct.factory.Mappers;
import com.example.end.mapping.BookingMapper;

@Configuration
public class MappingConfig {

    @Bean
    public BookingMapper bookingMapper() {
        return Mappers.getMapper(BookingMapper.class);
    }
}

