package com.vorova.gifts.model.entity;

import com.vorova.gifts.model.enums.FastOrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fast_order")
public class FastOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "gift_id")
    private Gift gift;

    @OneToOne
    @JoinColumn(name = "box_id")
    private Gift box;

    @Column(name = "costs")
    private Double costs;

    @Column(name = "price")
    private Double price;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "revenue")
    private Double revenue;

    @Column(name = "address")
    private String address;

    @Column(name = "tel")
    private Long tel;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private FastOrderStatus status = FastOrderStatus.NEW;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FastOrder fastOrder = (FastOrder) o;
        return fastOrder.getId() == id.intValue();
    }

    @Override
    public int hashCode() {
        return id.intValue();
    }
}