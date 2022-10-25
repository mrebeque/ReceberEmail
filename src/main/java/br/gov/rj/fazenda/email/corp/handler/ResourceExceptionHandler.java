package br.gov.rj.fazenda.email.corp.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.gov.rj.fazenda.email.corp.exception.DetalhesErro;
import br.gov.rj.fazenda.email.corp.exception.SefazException;
import lombok.extern.log4j.Log4j2;

@Log4j2
@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(SefazException.class)
	public ResponseEntity<DetalhesErro> handleRecursoNaoEncontradoException(SefazException e,
			HttpServletRequest request) {
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(e.getErrorCode());
		erro.setMessage(e.getErrorMessage());
		erro.setTimestamp(System.currentTimeMillis());
		log.error("SEFAZ Exception -> status: " + erro.getStatus().toString() + " message: "
				+ erro.getMessage().toString());
		return ResponseEntity.status(e.getErrorCode()).body(erro);
	}

}