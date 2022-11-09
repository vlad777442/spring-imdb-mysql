package ru.imdbmanager.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "movies_genres")
public class Genre {
    @Column(name = "genre")
    private String genre;


}
