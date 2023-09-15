package com.example.demo.infrastructure.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

/**
 * User Entity
 */
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nonnull
    private String nickName;

    @Nonnull
    @Column(unique = true)
    private String email;
}
