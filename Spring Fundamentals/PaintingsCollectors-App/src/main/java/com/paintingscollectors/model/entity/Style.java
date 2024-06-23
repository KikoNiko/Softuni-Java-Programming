package com.paintingscollectors.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "styles")
public class Style extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private StyleName name;
    @Column(nullable = false)
    private String description;

    public Style() {
    }

    public Style(StyleName name) {
        this.name = name;
        switch (name) {
            case REALISM:
                description = "Also known as naturalism, this style of art is considered as 'real art' and has been the dominant style of painting since the Renaissance.";
                break;
            case ABSTRACT:
                description = "Abstract art does not attempt to represent recognizable subjects in a realistic manner.";
                break;
            case SURREALISM:
                description = "Surrealism is characterized by dreamlike, fantastical imagery that often defies logical explanation.";
                break;
            case EXPRESSIONISM:
                description = "Expressionism is a style of art that doesn't concern itself with realism.";
                break;
            case IMPRESSIONISM:
                description = "Impressionism is a painting style most commonly associated with the 19th century where small brush strokes are used to build up a larger picture.";
                break;
        }
    }

    public StyleName getName() {
        return name;
    }

    public void setName(StyleName name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
