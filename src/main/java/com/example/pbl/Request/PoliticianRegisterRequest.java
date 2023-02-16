package com.example.pbl.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PoliticianRegisterRequest {
    Long citizen_id;
    String position;//chuc danh
    String areaManage;
    String levelManager;
}
