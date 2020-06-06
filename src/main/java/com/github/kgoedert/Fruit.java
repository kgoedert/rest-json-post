package com.github.kgoedert;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "Fruit", description = "Represents a fruit")
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class Fruit {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(required = false, readOnly = true)
    private String uuid;

    @Schema(required = true, example = "The name of the fruit you want to save")
    private String name;

    @Schema(required = false, example = "Some description for the fruit")
    private String description;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(shape = Shape.STRING, pattern = "MM/dd/yyyy HH:mm:ss")
    @Schema(required = false, readOnly = true, pattern = "MM/dd/yyyy HH:mm:ss")
    private LocalDateTime created;

    public void addCreationDate() {
        this.created = LocalDateTime.now();
    }

    public void addUUID() {
        this.uuid = UUID.randomUUID().toString();
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public String getUuid() {
        return uuid;
    }
}