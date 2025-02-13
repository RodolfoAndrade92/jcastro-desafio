package com.test.dev.contact.manager.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Schema(description = "Request to create a new contact")
public class CreateContactRequest {

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
}
