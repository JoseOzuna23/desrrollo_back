package com.coop8.demojwt.Controllers;

import com.coop8.demojwt.Request.PersonasRequest;
import com.coop8.demojwt.Response.SecuredResponse;
import com.coop8.demojwt.Service.PersonasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/personas")
@Slf4j
public class PersonasController {

    @Autowired
    PersonasService personasService;

    @GetMapping("/list")
    public ResponseEntity<?> list(@Valid  PersonasRequest personasRequest) throws Exception {
        log.info("__end_point: /personas/list");
        SecuredResponse response = personasService.list(personasRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/edit")
    public ResponseEntity<?> edit(@Valid @RequestBody PersonasRequest personasRequest) throws Exception {
        log.info("__end_point: /personas/edit");
        SecuredResponse response = personasService.getById(personasRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/getById")
    public ResponseEntity<?> getById(@Valid @RequestBody PersonasRequest personasRequest) throws Exception {
        log.info("__end_point: /personas/getById");
        SecuredResponse response = personasService.getById(personasRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/newAction")
    public ResponseEntity<?> newAction(@Valid @RequestBody PersonasRequest personasRequest) throws Exception {
        log.info("__end_point: /personas/newAction");
        SecuredResponse response = personasService.save(personasRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody PersonasRequest personasRequest) throws Exception {
        log.info("__end_point: /personas/save");
        SecuredResponse response = personasService.save(personasRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/deleteById")
    public ResponseEntity<?> deleteById(@Valid @RequestBody PersonasRequest personasRequest) throws Exception {
        log.info("__end_point: /personas/deleteById");
        SecuredResponse response = personasService.deleteById(personasRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody PersonasRequest personasRequest) throws Exception {
        log.info("__end_point: /personas/create");
        String usuariosys = SecurityContextHolder.getContext().getAuthentication().getName();
        personasService.savePerson(personasRequest, usuariosys);
        return new ResponseEntity<>("Persona creada exitosamente", HttpStatus.CREATED);
    }
}
