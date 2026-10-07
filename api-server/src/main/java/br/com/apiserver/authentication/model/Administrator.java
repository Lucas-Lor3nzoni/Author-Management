package br.com.apiserver.authentication.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor

@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
@Table(name = "administrators")
public class Administrator {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Builder.Default
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "administrator_roles",
            joinColumns = @JoinColumn(name = "administrator_id")

    )
    @Enumerated(EnumType.STRING)
    private Set<Roles> roles = new HashSet<>();

    @Builder.Default
    @Column(nullable = false)
    private boolean active = false;

    @PreUpdate
    @PrePersist
    public void validateAdministratorRoles() {

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Administrator name cannot be null or empty");
        }

        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Administrator email cannot be null or empty");
        }

        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Administrator password cannot be null or empty");
        }

        if (password.length() < 6) {
            throw new IllegalArgumentException("Administrator password must have at least 6 characters");
        }

        if (roles == null || roles.isEmpty()) {
            roles = new HashSet<>(Set.of(Roles.ADMIN));
        }

    }

}