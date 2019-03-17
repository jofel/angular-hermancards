package hu.szeged.u.ohsh.service.dto;

import java.io.Serializable;
import java.util.Objects;
import hu.szeged.u.ohsh.domain.enumeration.Rank;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the Student entity. This class is used in StudentResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /students?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class StudentCriteria implements Serializable {
    /**
     * Class for filtering Rank
     */
    public static class RankFilter extends Filter<Rank> {
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private StringFilter study;

    private RankFilter rank;

    private LongFilter roomId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter getStudy() {
        return study;
    }

    public void setStudy(StringFilter study) {
        this.study = study;
    }

    public RankFilter getRank() {
        return rank;
    }

    public void setRank(RankFilter rank) {
        this.rank = rank;
    }

    public LongFilter getRoomId() {
        return roomId;
    }

    public void setRoomId(LongFilter roomId) {
        this.roomId = roomId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final StudentCriteria that = (StudentCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(study, that.study) &&
            Objects.equals(rank, that.rank) &&
            Objects.equals(roomId, that.roomId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        name,
        study,
        rank,
        roomId
        );
    }

    @Override
    public String toString() {
        return "StudentCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (study != null ? "study=" + study + ", " : "") +
                (rank != null ? "rank=" + rank + ", " : "") +
                (roomId != null ? "roomId=" + roomId + ", " : "") +
            "}";
    }

}
