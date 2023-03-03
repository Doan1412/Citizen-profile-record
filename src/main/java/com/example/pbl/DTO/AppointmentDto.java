package com.example.pbl.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto {
    private Long citizen_id;
    private Long politician_id;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="Indochina")
    private Date appointmentDate;
    private String startTime;
    private String endTime;
    private String status;
    private String description;
}
