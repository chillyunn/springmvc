package com.jirandata.modal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModalRepository extends JpaRepository<Modal,Long> {
}
