package com.n26.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class which is thrown when transaction is not found.
 *
 * @author Biniam Asnake.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Transaction not found")
public class TransactionNotFoundException extends RuntimeException {

}
