package com.example.pbl.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequirementRequest {
    Long author_id;
    List <Long> recipient_id;
    String description;
    Date date;
}
