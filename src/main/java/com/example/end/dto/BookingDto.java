package com.example.end.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Data
public class BookingDto {

    private LocalDateTime dateTime;

}