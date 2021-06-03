package ru.itis.javalab.models;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class Method {
    private Long id;
    private String name;
    private Integer count;
}
