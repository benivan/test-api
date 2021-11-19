package com.example.springsecurity.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Like {

    @EmbeddedId
    private LikedPKId likedPKId;

    private LocalDateTime likedTIme;
}
