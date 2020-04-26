package com.alecrim.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TerminalController {

	@GetMapping("/terminal/saque")
	public ResponseEntity<String> saque(@RequestParam(required = true) Integer valor) {
		if (valor < 8) {
			return new ResponseEntity<String>("Valor deve ser maior que 8.", HttpStatus.BAD_REQUEST);
		}

		List<Integer> notas = Arrays.asList(5, 3);
		Integer resto = valor;
		List<Integer> qtNotas = new ArrayList<Integer>();

		for (Integer nota : notas) {
			Integer n = 0;
			while (resto >= nota) {
				if ((resto - nota) < 3 && (resto - nota) > 0) {
					break;
				}
				resto -= nota;
				n++;
			}

			if (n != 0) {
				qtNotas.add(n);
			}
		}

		String message;

		if (qtNotas.size() > 1) {
			message = String.format("Saque %d: %d nota de R$3 e %d nota de R$5", valor, qtNotas.get(1), qtNotas.get(0));
		} else {
			message = String.format("Saque %d: %d nota de R$5", valor, qtNotas.get(0));
		}

		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

}
