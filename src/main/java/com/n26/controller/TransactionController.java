package com.n26.controller;

import com.n26.model.Transaction;
import com.n26.response.wrapper.TransactionSum;
import com.n26.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The entrance point of all REST API calls that allows GET and PUT requests to manage transactions.
 *
 * @author Biniam Asnake
 */
@Controller
public class TransactionController {

    private static final Logger log = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private TransactionService transactionService;

    /**
     * Returns transaction by transaction id
     *
     * @param transactionId: ID of the transaction supplied by the user
     * @return Transaction
     */
    @RequestMapping(value = "transactions/{id}", method = RequestMethod.GET)
    public @ResponseBody Transaction get(@PathVariable("id") Long transactionId) {

        log.info("Getting transaction with id " + transactionId.toString());
        return transactionService.getTransaction(transactionId);
    }

    /**
     * Accepts and persists transaction data
     *
     * @param transactionId: ID of the transaction supplied by the user
     * @param transaction: transaction object containing type, amount and optional parentId
     * @return Transaction
     */
    @RequestMapping(value = "transactions/{id}", method = RequestMethod.PUT)
    public @ResponseBody Transaction create(@PathVariable("id") Long transactionId, @Validated @RequestBody Transaction transaction) {

        log.info("Creating a new (or updating) Transaction " + transaction.toString());
        return transactionService.createTransaction(transactionId, transaction);
    }

    /**
     * Get all transactionIds by type
     *
     * @param type: transaction type
     * @return List<Long>: list of transaction Ids
     */
    @RequestMapping(value = "types/{type}", method = RequestMethod.GET)
    public @ResponseBody List<Long> findAllByType(@PathVariable("type") String type) {

        log.info("Getting all transactions with type " + type);
        return transactionService.getAllTransactionIdsByType(type);
    }

    /**
     * Get sum of transaction amount by id
     *
     * @param transactionId: ID of the transaction supplied by the user
     * @return TransactionSum
     */
    @RequestMapping(value = "sum/{transactionId}", method = RequestMethod.GET)
    public @ResponseBody TransactionSum getSumOfTransactionAmount(@PathVariable("transactionId") Long transactionId) {

        log.info("Getting the sum of transactions with id " + transactionId);
        return new TransactionSum(transactionService.getSumOfTransactionAmountById(transactionId));
    }
}