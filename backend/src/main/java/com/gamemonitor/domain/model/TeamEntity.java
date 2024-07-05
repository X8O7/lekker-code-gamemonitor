package com.gamemonitor.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity(name = "Team")
@Table(name = "Team")
public class TeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int maxMembers;

    @OneToMany(mappedBy = "team")
    private Set<UserEntity> users;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity owner;
}
