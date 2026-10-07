package br.com.apiserver.author.model;

import br.com.apiserver.work.model.Work;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor

@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
@Table(name = "authors")
public class Author {

    private final static Set<String> COUNTRY_CODES = Set.of(Locale.getISOCountries());

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false, length = 2)
    private String countryCode;

    @Column(unique = true, length = 11)
    private String cpf;

    @ManyToMany(mappedBy = "authors")
    private List<Work> works = new ArrayList<>();

    @PrePersist
    @PreUpdate
    public void validateAuthorRules() {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty!");
        }

        if (name.length() > 150) {
            throw new IllegalArgumentException("Name cannot be longer than 150 characters!");
        }

        if (birthDate == null) {
            throw new IllegalArgumentException("Birth date cannot be empty!");
        }

        if (birthDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Birth date cannot be in the future!");
        }

        if (countryCode == null || countryCode.isBlank()) {
            throw new IllegalArgumentException("Country code cannot be empty!");
        }

        countryCode = countryCode.toUpperCase(Locale.ROOT);

        if (!COUNTRY_CODES.contains(countryCode)) {
            throw new IllegalArgumentException("Country code is invalid!");
        }

        if ("BR".equals(countryCode)) {
            if (cpf == null || cpf.isBlank()) {
                throw new IllegalArgumentException("CPF cannot be empty for Brazilian authors!");
            }

            if (cpf.matches("\\d{11}")) {
                throw new IllegalArgumentException("CPF must have 11 digits!");
            }
        } else {
            cpf = null;
        }

    }

}