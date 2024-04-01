package com.gustavo.spring.domain.relationship.pks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gustavo.spring.domain.Order;
import com.gustavo.spring.domain.Product;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class OrderItemPK {

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
