package com.jirandata.modal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class ModalController {
    private final ModalService modalService;
}
