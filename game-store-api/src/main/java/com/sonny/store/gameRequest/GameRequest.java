package com.sonny.store.gameRequest;

import com.sonny.store.common.BaseEntity;
import com.sonny.store.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class GameRequest extends BaseEntity {

    private String title;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
