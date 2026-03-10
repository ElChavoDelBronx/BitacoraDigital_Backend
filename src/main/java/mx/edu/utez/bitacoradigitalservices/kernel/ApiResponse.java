package mx.edu.utez.bitacoradigitalservices.kernel;

import org.springframework.http.HttpStatus;

public class ApiResponse {
    private String message;
    private Object data;
    private boolean error;
    private HttpStatus status;

    public ApiResponse(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    //Mensaje de éxito con cuerpo
    public ApiResponse(String message, Object data, HttpStatus status) {
        this.message = message;
        this.data = data;
        this.status = status;
    }

    //Mnesaje de error sin payload
    public ApiResponse(String message, boolean error, HttpStatus status) {
        this.message = message;
        this.error = error;
        this.status = status;
    }

    //Mensaje de error con payload
    public ApiResponse(String message, Object data, boolean error, HttpStatus status) {
        this.message = message;
        this.data = data;
        this.error = error;
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public boolean isError() {
        return error;
    }

    public Object getData() {
        return data;
    }
}
