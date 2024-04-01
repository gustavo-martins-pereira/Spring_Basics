package com.gustavo.spring.domain.relationship;

import com.gustavo.spring.domain.relationship.pks.OrderItemPK;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@Entity(name = "order_item")
public class OrderItemRelationship {

    @EmbeddedId
    private OrderItemPK id;

    private Integer quantity;
    private Double price;

}
