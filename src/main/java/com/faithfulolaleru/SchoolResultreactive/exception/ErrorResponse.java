package com.faithfulolaleru.SchoolResultreactive.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    public static final String ERROR_MOVIE_ALREADY_EXIST = "MOVIE_ALREADY_EXISTS";

    public static final String ERROR_INVOICE_ALREADY_EXIST = "INVOICE_ALREADY_EXISTS";

    public static final String ERROR_MOVIE_NOT_EXIST = "MOVIE_DOES_NOT_EXIST";

    public static final String ERROR_INVOICE_NOT_EXIST = "INVOICE_DOES_NOT_EXIST";

    public static final String ERROR_STUDENT_ALREADY_EXIST = "STUDENT_ALREADY_EXISTS";
    public static final String ERROR_STUDENT_NOT_EXIST = "STUDENT_DOES_NOT_EXIST";




    private String error;
    private String message;
    private HttpStatus httpStatus;


    public ErrorResponse(final GeneralException ex) {
        this.httpStatus = ex.getHttpStatus();
        this.error = ex.getError();
        this.message = ex.getMessage();   //  ex.getLocalizedMessage()
    }
}
