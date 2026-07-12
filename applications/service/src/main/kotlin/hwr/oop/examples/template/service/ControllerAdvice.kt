package hwr.oop.examples.template.service

import hwr.oop.examples.template.service.model.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest

@RestControllerAdvice
class ControllerAdvice {
	
	@ExceptionHandler(Exception::class)
	fun handleGenericException(
		ex: Exception,
		request: WebRequest,
	): ResponseEntity<ErrorResponse> {
		val errorResponse = ErrorResponse(
			/*status =*/ HttpStatus.INTERNAL_SERVER_ERROR.value(),
			/*error =*/ HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase,
			/*message =*/ ex.message ?: "An unexpected error occurred",
		)

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
			.body(errorResponse)
	}
	// für jede Exception jeweils eine function
	// Eigene exceptions anbauen TODO
//	@ExceptionHandler(Exception::class)
//	fun handleGenericException(
//		ex: Exception,
//		request: WebRequest,
//	): ResponseEntity<ErrorResponse> {
//		val errorResponse = ErrorResponse(
//			/*status =*/ HttpStatus.INTERNAL_SERVER_ERROR.value(),
//			/*error =*/ HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase,
//			/*message =*/ ex.message ?: "An unexpected error occurred",
//		)
//
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//			.body(errorResponse)
//	}
	
}

