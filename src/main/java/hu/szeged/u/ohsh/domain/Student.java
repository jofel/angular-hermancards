package hu.szeged.u.ohsh.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

import hu.szeged.u.ohsh.domain.enumeration.Rank;

/**
 * A Student.
 */
@Entity
@Table(name = "student")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "study")
    private String study;

    @Enumerated(EnumType.STRING)
    @Column(name = "jhi_rank")
    private Rank rank;

    @ManyToOne
    @JsonIgnoreProperties("students")
    private Room room;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Student name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudy() {
        return study;
    }

    public Student study(String study) {
        this.study = study;
        return this;
    }

    public void setStudy(String study) {
        this.study = study;
    }

    public Rank getRank() {
        return rank;
    }

    public Student rank(Rank rank) {
        this.rank = rank;
        return this;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Room getRoom() {
        return room;
    }

    public Student room(Room room) {
        this.room = room;
        return this;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        if (student.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), student.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Student{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", study='" + getStudy() + "'" +
            ", rank='" + getRank() + "'" +
            "}";
    }
}
