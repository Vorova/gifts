package com.vorova.gifts.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "image")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "gift_id")
    private Gift gift;

    @Column(name = "link")
    private String link;

    @Column(name = "is_main")
    private Boolean isMain;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(id, image.id) && Objects.equals(gift, image.gift) && Objects.equals(link, image.link) && Objects.equals(isMain, image.isMain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gift, link, isMain);
    }
}