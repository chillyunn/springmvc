package com.jirandata.modal;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Modal {
    @Id
    @GeneratedValue
    Long id;

    @Column(nullable = false)
    String received;
}
