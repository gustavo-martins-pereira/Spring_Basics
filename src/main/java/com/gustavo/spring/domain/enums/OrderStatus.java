package com.gustavo.spring.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Enumerated;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum OrderStatus {

    WAITING_PAYMENT(1),
    PAID(2),
    SHIPPED(3),
    DELIVERED(4),
    CANCELED(5);

    private final int code;

    private OrderStatus(int code) {
        this.code = code;
    }

    public static OrderStatus valueOf(int code) {
        for(OrderStatus value : OrderStatus.values()) {
            if(value.getCode() == code) {
                return value;
            }
        }

        throw new IllegalArgumentException("Invalid OrderStatus code");
    }

}
