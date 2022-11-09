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
@Table(name = "roles")
public class Role {
    @Column(name = "role")
    private String role;

    @ManyToOne
    @JoinColumn(name = "actor_id")
    private Actor actor;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Role(String role, Actor actor) {
        this.role = role;
        this.actor = actor;
    }

    @Override
    public String toString() {
        return "Role{" +
                "role='" + role + '\'' +
                ", actor=" + actor +
                '}';
    }
}
