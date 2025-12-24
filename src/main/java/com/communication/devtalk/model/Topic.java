package com.communication.devtalk.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "topics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    // Inverse side of Many-to-Many
    @ManyToMany(mappedBy = "topics")
    @ToString.Exclude
    @Builder.Default
    private Set<Post> posts = new HashSet<>();
}
