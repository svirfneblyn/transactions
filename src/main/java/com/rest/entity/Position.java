package com.rest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "positions")
public class Position implements Serializable {

    private static final long serialVersionUID = 6075455009845284374L;
    @Id
    @Column(name = "position_id")
    private Long id;
    @Column(name = "position")
    private String position;

    public Position() {
    }

    public Position(long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", position='" + position + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position1 = (Position) o;
        if (getId() != null ? !getId().equals(position1.getId()) : position1.getId() != null) return false;
        return getPosition() != null ? getPosition().equals(position1.getPosition()) : position1.getPosition() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getPosition() != null ? getPosition().hashCode() : 0);
        return result;
    }
}
