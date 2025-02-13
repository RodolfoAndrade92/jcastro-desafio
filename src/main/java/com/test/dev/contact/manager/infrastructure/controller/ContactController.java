package com.test.dev.contact.manager.infrastructure.controller;

import com.test.dev.contact.manager.application.dto.ContactResponse;
import com.test.dev.contact.manager.application.dto.CreateContactRequest;
import com.test.dev.contact.manager.application.dto.UpdateContactRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Contact Management", description = "API for contact management")
@RequestMapping("/api/contacts")
public interface ContactController {

    @Operation(summary = "Create a contact")
    @ApiResponse(responseCode = "201", description = "Contact successfully created")
    @PostMapping("/create")
    ResponseEntity<ContactResponse> create(@RequestBody CreateContactRequest createContactRequest);

    @Operation(summary = "List all contacts")
    @ApiResponse(responseCode = "200", description = "Contacts successfully fetched")
    @GetMapping("/get")
    ResponseEntity<List<ContactResponse>> getAll();

    @Operation(summary = "List active contacts")
    @ApiResponse(responseCode = "200", description = "Contacts successfully fetched")
    @GetMapping("/get/active")
    ResponseEntity<List<ContactResponse>> getActive();

    @Operation(summary = "Update a contact")
    @ApiResponse(responseCode = "200", description = "Contact successfully updated")
    @PatchMapping("/update")
    ResponseEntity<ContactResponse> update(@RequestBody UpdateContactRequest updateContactRequest);
}
