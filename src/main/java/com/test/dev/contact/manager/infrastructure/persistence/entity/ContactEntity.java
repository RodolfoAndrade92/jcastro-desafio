package com.test.dev.contact.manager.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "CONTATO", schema = "DESAFIO")
@Getter
@Setter
@Builder
public class ContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contato_id")
    private Long id;

    @Column(name = "contato_nome", length = 100)
    private String name;

    @Column(name = "contato_email")
    private String email;

    @Column(name = "contato_celular", length = 11)
    private String cellPhone;

    @Column(name = "contato_telefone", length = 10)
    private String telephone;

    @Column(name = "contato_sn_favorito")
    private Boolean favorite;

    @Column(name = "contato_sn_ativo")
    private Boolean active;

    @Column(name = "contato_dh_cad")
    private LocalDateTime createDate;
}
