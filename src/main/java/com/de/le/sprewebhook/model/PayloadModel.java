package com.de.le.sprewebhook.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PayloadModel {
    private String event;
    private String orderId;
    private Double amount;
    private String status;
}
