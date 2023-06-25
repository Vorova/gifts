package com.vorova.gifts.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "gift")
public class Gift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "short_description")
    private String shortDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category")
    private Category category;

    @Column(name = "cost_price")
    private double costPrice;

    @Column(name = "price")
    private double price;

    @Column(name = "date_added")
    private Timestamp dateAdded = Timestamp.from(Instant.now());

    @Column(name = "is_enabled")
    private Boolean isEnabled = false;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            mappedBy = "gift")
    private List<Image> images;

    @Column(name = "type")
    private String type = "gift";

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gift gift = (Gift) o;
        return id == gift.id && Double.compare(gift.costPrice, costPrice) == 0 && Double.compare(gift.price, price) == 0 && Objects.equals(title, gift.title) && Objects.equals(description, gift.description) && Objects.equals(category, gift.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, category, costPrice, price);
    }

}
