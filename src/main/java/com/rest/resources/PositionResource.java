package com.rest.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rest.entity.Position;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by Ihar_Rubanovich on 3/24/2017.
 */
public class PositionResource extends ResourceSupport {

    private Position position;

    @JsonCreator
    public PositionResource(@JsonProperty("position") Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
