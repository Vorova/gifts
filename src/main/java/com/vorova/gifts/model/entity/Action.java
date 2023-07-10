package com.vorova.gifts.model.entity;

import com.vorova.gifts.model.enums.ActionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "action")
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user = null;

    @Column(name = "date")
    private LocalDateTime date = LocalDateTime.now();

    @Column(name = "type")
    private ActionType type;

    @Column(name = "description")
    private String description;

}
