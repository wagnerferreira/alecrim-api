package com.alecrim.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.alecrim.entity.LogImportCsv;
import com.alecrim.entity.Mensagem;
import com.alecrim.entity.Pessoa;
import com.alecrim.repository.LogImportRepository;
import com.alecrim.repository.PessoaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/api")
public class DestinatarioController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private LogImportRepository logImportRepository;
    private LogImportCsv log;

    @RequestMapping(path = "/destinatario/upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> fileUpload(@RequestParam("file") final MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Nenhum arquivo enviado", HttpStatus.BAD_REQUEST);
        }

        LogImportCsv log = logImportRepository.findByFile(file.getOriginalFilename());
        if (log != null) {
            return new ResponseEntity<>("Este arquivo já foi enviado.", HttpStatus.CONFLICT);
        }

        String line = "";
        Pessoa destinatario = null;

        try {
            final InputStream is = file.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null) {
                final String[] dests = line.split(";");
                destinatario = new Pessoa();
                destinatario.setNome(dests[0]);
                destinatario.setTelefone(dests[1]);
                destinatario.setCpf(dests[2]);
                destinatario.setEmail(dests[3]);
                destinatario.setEndereco(dests[4]);
                destinatario.setTipo("D");
                pessoaRepository.save(destinatario);
            }
            
            br.close();
            log(file.getOriginalFilename());
        } catch (final IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void log(String filename) {
        log = new LogImportCsv(filename);
        logImportRepository.save(log);
    }

    @GetMapping("/destinatario")
    public ResponseEntity<List<Pessoa>> findAll() {
        return new ResponseEntity<>((List<Pessoa>) pessoaRepository.findByTipo("D"), HttpStatus.OK);
    }

    @PostMapping(value = "/enviar-mensagem")
    public ResponseEntity<String> save(@RequestBody Mensagem mensagem) {
        System.out.println(String.format("%s enviou mensagem para %s", mensagem.getRemente().getNome(),
                mensagem.getDestinatario().getNome()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}