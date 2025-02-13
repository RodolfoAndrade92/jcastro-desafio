package com.test.dev.contact.manager.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Schema(description = "Response containing details of a contact")
public class ContactResponse {
    
    @Schema(description = "Contact ID", example = "1")
    private Long id;

    @Schema(description = "Contact name", example = "John Doe")
    private String name;

    @Schema(description = "Contact email", example = "john.doe@email.com")
    private String email;

    @Schema(description = "Contact cellphone", example = "11912345678")
    private String cellPhone;

    @Schema(description = "Contact telephone", example = "1112345678")
    private String telephone;

    @Schema(description = "If contact is favorite", example = "false")
    private Boolean favorite;

    @Schema(description = "If contact is active", example = "true")
    private Boolean active;

    @Schema(description = "Contact creation date", example = "2023-10-01T10:00:00")
    private LocalDateTime createDate;
}
