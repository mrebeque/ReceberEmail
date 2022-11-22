package br.gov.rj.fazenda.email.corp.controller.impl;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/status/v1")
public class Ping {
	
		@PreAuthorize("hasAuthority('USER')")
		@GetMapping(value = "/ping",produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> ping() {
			return ResponseEntity.ok("Pong!");
		}	
}
