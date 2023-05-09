package  com.onlineshop.exception;

import java.util.Date;

import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class AppExceptionsHandler {
	
	@ExceptionHandler(value = {ResourceNotFoundException.class})
	public ResponseEntity<Object> handleUserServiceException(ResourceNotFoundException ex, WebRequest request)
	{
		String traceId = MDC.get("traceId");
		ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage(), traceId);
		
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = {MethodArgumentNotValidException.class})
	public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request)
	{
		String traceId = MDC.get("traceId");
		ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage(), traceId);
		
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {UsernameNotFoundException.class})
	public ResponseEntity<Object> handleUsernameNotFoundException(MethodArgumentNotValidException ex, WebRequest request)
	{
		String traceId = MDC.get("traceId");
		ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage(), traceId);
		
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleOtherExceptions(Exception ex, WebRequest request)
	{
		String traceId = MDC.get("traceId");
		ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage(), traceId);
		
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
