package com.vorova.gifts.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Gift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String category;

    @Column
    private double price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gift gift = (Gift) o;
        return id == gift.id && Double.compare(gift.price, price) == 0 && Objects.equals(name, gift.name) && Objects.equals(description, gift.description) && Objects.equals(category, gift.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category, price);
    }
}
