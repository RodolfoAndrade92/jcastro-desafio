package com.test.dev.contact.manager.domain.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
    private Long id;
    private String name;
    private String email;
    private String cellPhone;
    private String telephone;
    private Boolean favorite;
    private Boolean active;
    private LocalDateTime createDate;
}
