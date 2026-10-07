package br.com.apiserver.work.model;

import br.com.apiserver.author.model.Author;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor

@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
@Table(name = "works")
public class Work {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 240)
    private String description;

    private Instant releaseDate;

    private Instant exhibitionDate;

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "work_author",
            joinColumns = @JoinColumn(name = "work_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors = new HashSet<>();

    @PreUpdate
    @PrePersist
    public void validateWorkRules() {

        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be blank");
        }

        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be blank");
        }

        if (description.length() > 240) {
            throw new IllegalArgumentException("Description cannot be longer than 240 characters");
        }

        if (releaseDate == null && exhibitionDate == null) {
            throw new IllegalArgumentException("Release date or exhibition date must be set");
        }

        if (authors == null || authors.isEmpty()) {
            throw new IllegalArgumentException("At least one author must be set");
        }

    }

}