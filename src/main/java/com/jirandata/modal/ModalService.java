package com.jirandata.modal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ModalService {
    private final ModalRepository modalRepository;
}
