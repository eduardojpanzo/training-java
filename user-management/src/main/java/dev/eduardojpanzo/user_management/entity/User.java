package dev.eduardojpanzo.user_management.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    protected User() {}

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.createdAt = OffsetDateTime.now();
    }

    public Long getId() {
        return id; 
    }
    public String getName() {
        return name; 
    }
    public String getEmail() {
        return email; 
    }
    public OffsetDateTime getCreatedAt() {
        return createdAt; 
    }
}
