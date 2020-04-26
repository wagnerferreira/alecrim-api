package com.alecrim.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UtilsController {

	// listaReversa?lista={25,35,40,21}
	@GetMapping("/listaReversa")
	public ResponseEntity<List<Integer>> listaReversa(@RequestParam(required = true) int[] lista) {
		List<Integer> reverse = new ArrayList<Integer>();
		for (int i = 0, n = lista.length - 1; i < lista.length; i++, n--) {
			reverse.add(lista[n]);
		}
		return new ResponseEntity<>(reverse, HttpStatus.OK);
	}

	// imprimirImpares?lista={25,35,40,21}
	@GetMapping("/imprimirImpares")
	public ResponseEntity<List<Integer>> imprimirImpares(@RequestParam(required = true) int[] lista) {
		List<Integer> impares = new ArrayList<Integer>();
		for (Integer num : lista) {
			if (num % 2 != 0) {
				impares.add(num);
			}
		}
		return new ResponseEntity<>(impares, HttpStatus.OK);
	}

	// imprimirPares?lista={25,35,40,21}
	@GetMapping("/imprimirPares")
	public ResponseEntity<List<Integer>> imprimirPares(@RequestParam(required = true) List<Integer> lista) {
		List<Integer> pares = new ArrayList<Integer>();
		for (Integer num : lista) {
			if (num % 2 == 0) {
				pares.add(num);
			}
		}
		return new ResponseEntity<>(pares, HttpStatus.OK);
	}

	// tamanho?palavra=desafio
	@GetMapping("/tamanho")
	public ResponseEntity<String> tamanho(@RequestParam(required = true) String palavra) {
		return new ResponseEntity<>("Tamanho=" + palavra.length(), HttpStatus.OK);
	}

	// maisculas?palavra=desafio
	@GetMapping("/maiusculas")
	public ResponseEntity<String> maiusculas(@RequestParam(required = true) String palavra) {
		return new ResponseEntity<>(palavra.toUpperCase(), HttpStatus.OK);
	}

	// vogais?palavra=desafio
	@GetMapping("/vogais")
	public ResponseEntity<String> vogais(@RequestParam(required = true) String palavra) {
		try {
			Integer.valueOf(palavra);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (NumberFormatException e) {
		}
		return new ResponseEntity<>(extractLetters(palavra, true), HttpStatus.OK);
	}

	// consoantes?palavra=desafio
	@GetMapping("/consoantes")
	@ResponseBody
	public ResponseEntity<String> consoantes(@RequestParam(required = true) String palavra) {
		try {
			Integer.valueOf(palavra);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (NumberFormatException e) {
		}
		return new ResponseEntity<>(extractLetters(palavra, false), HttpStatus.OK);
	}

	// nomeBibliografico?nome=Paulo%25pereira%25junior
	@GetMapping("/nomeBibliografico")
	public ResponseEntity<String> nomeBibliografico(@RequestParam(required = true) String nome) {
		String[] p = nome.split("%");
		StringBuffer sb = new StringBuffer();
		sb.append(p[0].toUpperCase());
		sb.append(", ");

		for (int i = 1; i < p.length; i++) {
			sb.append(StringUtils.capitalize(p[i]) + ' ');
		}

		return new ResponseEntity<>(sb.toString(), HttpStatus.OK);
	}

	private String extractLetters(String palavra, Boolean isVogal) {
		final String vogais = "aeiou";
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < palavra.length(); i++) {
			if (isVogal) {
				if (vogais.contains(Character.toString(palavra.charAt(i)))) {
					sb.append(palavra.charAt(i));
				}
			} else {
				if (!vogais.contains(Character.toString(palavra.charAt(i)))) {
					sb.append(palavra.charAt(i));
				}
			}
		}
		return sb.toString();
	}

}
