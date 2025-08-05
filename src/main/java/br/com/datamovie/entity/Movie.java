package br.com.datamovie.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "movie")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    private String description;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    private double rating;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToMany
    @JoinTable(name = "movie_category",
            joinColumns = @JoinColumn (name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    @ManyToMany
    @JoinTable(name = "movie_streaming",
            joinColumns = @JoinColumn (name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "streaming_id")
    )
    private List<Streaming> streaming;

}
