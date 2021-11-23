package ru.sber.entity;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * todo:
 * - configure JPA entity
 * - table name: "photo_comment"
 * - configure auto generated identifier
 * - configure not nullable column: text
 *
 * - map relation between Photo and PhotoComment using foreign_key column: "photo_id"
 * - configure relation as mandatory (not optional)
 */
@Getter
@Setter
public class PhotoComment {
    private Long id;
    private String text;
    private LocalDateTime createdOn;
    private Photo photo;
}
