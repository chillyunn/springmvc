package com.jirandata;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {
    @CreatedDate @Column(name = "created_datetime")
    private LocalDateTime createdDateTime;

    @LastModifiedDate @Column(name = "last_modified_datetime")
    private LocalDateTime lastModifiedDateTime;
}
