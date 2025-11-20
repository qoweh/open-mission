package com.openmission.domain;

import java.time.LocalDateTime;
import java.util.List;

public record SaveLog(String sender, List<String> receivers, String title, LocalDateTime localDateTime) {}
