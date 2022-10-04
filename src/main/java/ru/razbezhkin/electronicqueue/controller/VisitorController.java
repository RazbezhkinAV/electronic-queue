package ru.razbezhkin.electronicqueue.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.razbezhkin.electronicqueue.domain.VisitorDto;
import ru.razbezhkin.electronicqueue.service.VisitorService;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class VisitorController {

    private final VisitorService visitorService;

    @PutMapping
    public VisitorDto saveVisitor(@RequestBody VisitorDto visitorDto) {
        return visitorService.saveUser(visitorDto);
    }
}
