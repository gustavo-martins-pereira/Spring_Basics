package com.gustavo.spring.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@Entity(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    @Setter(AccessLevel.NONE)
    @ManyToMany(mappedBy = "categories")
    @JsonIgnoreProperties(value = "categories")
    private final Set<Product> products = new HashSet<>();

}
