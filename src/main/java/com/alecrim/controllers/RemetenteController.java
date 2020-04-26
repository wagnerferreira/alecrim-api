package com.alecrim.controllers;

import java.util.List;
import java.util.Optional;

import com.alecrim.entity.Pessoa;
import com.alecrim.repository.PessoaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class RemetenteController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @PostMapping(value = "/remetente")
    public ResponseEntity<Pessoa> save(@RequestBody Pessoa remetente) {
        remetente.setTipo("R");
        return new ResponseEntity<>(pessoaRepository.save(remetente), HttpStatus.OK);
    }   

    @PutMapping("/remetente/{id}")
    public ResponseEntity<Pessoa> edit(@PathVariable("id") Long id, @RequestBody Pessoa remetente) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        if (pessoa.isPresent()) {
            remetente.setId(pessoa.get().getId());
            pessoaRepository.save(remetente);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(remetente, HttpStatus.OK);
    }

    @GetMapping("/remetente/{id}")
    public ResponseEntity<Pessoa> findOne(@PathVariable("id") Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        if (!pessoa.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pessoa.get(), HttpStatus.OK);
    }

    @DeleteMapping("/remetente/{id}")
    public ResponseEntity<String> remove(@PathVariable("id") Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        if (pessoa.isPresent()) {
            pessoaRepository.delete(pessoa.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/remetente")
    public ResponseEntity<List<Pessoa>> findAll() {
        return new ResponseEntity<>((List<Pessoa>) pessoaRepository.findByTipo("R"), HttpStatus.OK);
    }

}