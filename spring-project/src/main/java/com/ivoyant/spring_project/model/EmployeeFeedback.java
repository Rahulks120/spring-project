package com.ivoyant.spring_project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeFeedback {
    private int id;
    private String employeeName;
    private String topic;
    private String feedback;
}

