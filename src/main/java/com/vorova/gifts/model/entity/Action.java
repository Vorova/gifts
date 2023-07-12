package com.vorova.gifts.model.entity;

import com.vorova.gifts.model.enums.ActionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.context.SecurityContextHolder;

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
    private User user;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ActionType type;

    @Column(name = "subject")
    private Long subject;

    public Action(ActionType type, Long subject) {
        date = LocalDateTime.now();
        User user = new User();
        user.setId((Long) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getCredentials());
        this.user = user;
        this.type = type;
        this.subject = subject;
    }

}
