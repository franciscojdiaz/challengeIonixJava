package com.example.challengeionix.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Data
public class BadRequestException extends RuntimeException {

    private String nombreDelRecurso;
    private String nombreDelCampo;
    private long valorDelCampo;

    public BadRequestException(String nombreDelRecurso, String nombreDelCampo, long valorDelCampo) {
        super(String.format(" %s No encontrado con : %s : '%s'", nombreDelRecurso,nombreDelCampo,valorDelCampo));
        this.nombreDelRecurso = nombreDelRecurso;
        this.nombreDelCampo = nombreDelCampo;
        this.valorDelCampo = valorDelCampo;
    }
}
