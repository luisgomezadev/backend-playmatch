package com.lgsoftworks.reservation.application.dto.response;

import java.time.LocalTime;

public record TimeSlot(LocalTime start, LocalTime end) {
}
