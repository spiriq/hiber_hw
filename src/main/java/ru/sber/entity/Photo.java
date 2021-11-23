package ru.sber.entity;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * todo:
 *
 * - configure JPA entity
 * - table name: "photo"
 * - configure auto generated identifier
 * - configure not nullable and unique column: url
 *
 * - initialize field comments
 * - map relation between Photo and PhotoComment on the child side
 * - implement helper methods {@link Photo#addComment(ru.sber.entity.PhotoComment)} and {@link Photo#removeComment(ru.sber.entity.PhotoComment)}
 * - enable cascade type {@link javax.persistence.CascadeType#ALL} for field {@link Photo#comments}
 * - enable orphan removal
 */
@Getter
@Setter
public class Photo {
    private Long id;
    private String url;
    private String description;
    private List<PhotoComment> comments;

    public void addComment(PhotoComment comment) {
        throw new UnsupportedOperationException("TODO!");
    }

    public void removeComment(PhotoComment comment) {
        throw new UnsupportedOperationException("TODO");
    }

}
