package com.n26.service;

import com.n26.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.n26.TransactionserviceApplication.transactions;

/**
 * Service class that contains methods called from the Controller and
 * performs database operations using transaction repository
 *
 * @author Biniam Asnake
 */
@Service
public class TransactionService {

    /**
     * Returns transaction by transaction id
     *
     * @param transactionId: input parameter of transaction id
     * @return transaction
     */
    public Optional<Transaction> getTransaction(Long transactionId) {
        return transactions.stream()
                .filter(t -> t.getId().equals(transactionId))
                .findFirst();
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

        //Get transaction, if it exists then update and if new add
        Optional<Transaction> existingTransaction = getTransaction(transactionId);

        if (existingTransaction != null) {
            transactions.remove(existingTransaction);
        }

        transactions.add(transaction);
        return transaction;
    }

    /**
     * Get all transactionIds by type
     *
     * @param type: type of transaction
     * @return List< Long >: list of transaction Ids that has the specified type
     */
    public List<Long> getAllTransactionIdsByType(String type) {

        // get a collection of all the ids.
        return transactions.stream()
                .filter(t -> t.getType().equals(type))
                .map(Transaction::getId)
                .collect(Collectors.toList());
                //.orElseThrow(TransactionNotFoundException::new);
    }

    /**
     * Get sum of transaction amount by id
     *
     * @param transactionId: input parameter of transaction id
     * @return double value of the sum of transaction amounts
     */
    public double getSumOfTransactionAmountById(Long transactionId) {

        return transactions.stream()
                .filter(t -> t.getParentId() != null &&
                        t.getParentId().equals(transactionId) ||
                        t.getId().equals(transactionId))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }
}
