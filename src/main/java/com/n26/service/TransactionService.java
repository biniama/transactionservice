package com.n26.service;

import com.n26.entity.Transaction;
import com.n26.exception.TransactionNotFoundException;
import com.n26.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class that contains methods called from the Controller and
 * performs database operations using transaction repository
 *
 * @author Biniam Asnake
 */
@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    /**
     * Returns transaction by transaction id
     *
     * @param transactionId: input parameter of transaction id
     * @return transaction
     */
    public Transaction getTransaction(Long transactionId) {
        return transactionRepository.findOneById(transactionId)
                .orElseThrow(TransactionNotFoundException::new);
    }

    /**
     * Accepts and persists transaction
     *
     * @param transactionId: input parameter of transaction id which is assigned to the transaction and saved
     * @param transaction: input parameter of transaction containing type, amount and parentId (can also be null)
     * @return transaction: saved transaction object
     */
    public Transaction createTransaction(Long transactionId, Transaction transaction) {

        transaction.setId(transactionId);
        return transactionRepository.save(transaction);
    }

    /**
     * Get all transactionIds by type
     *
     * @param type: type of transaction
     * @return List< Long >: list of transaction Ids that has the specified type
     */
    public List<Long> getAllTransactionIdsByType(String type) {

        // get a collection of all the ids.
        List<Transaction> transactions = transactionRepository.findAllByType(type)
                .orElseThrow(TransactionNotFoundException::new);

        return transactions.stream().map(Transaction::getId).collect(Collectors.toList());
    }

    /**
     * Get sum of transaction amount by id
     *
     * @param transactionId: input parameter of transaction id
     * @return double value of the sum of transaction amounts
     */
    public double getSumOfTransactionAmountById(Long transactionId) {

        List<Transaction> transactions = transactionRepository.findAllByIdOrParentId(transactionId, transactionId)
                .orElseThrow(TransactionNotFoundException::new);

        return transactions.stream().mapToDouble(Transaction::getAmount).sum();
    }
}
