package com.lgsoftworks.application.reservation.dto.response;

import java.time.LocalTime;

public record TimeSlot(LocalTime start, LocalTime end) {
}
