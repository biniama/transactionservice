package com.n26.service;

import com.n26.exception.TransactionNotFoundException;
import com.n26.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.n26.TransactionserviceApplication.transactions;

/**
 * Service class that contains methods called from the Controller and
 * performs different operations like creating and getting transactions,
 * getting transactions by type and sum of amount.
 *
 * @author Biniam Asnake
 */
@Service
public class TransactionService {

    private static final Logger log = LoggerFactory.getLogger(TransactionService.class);

    /**
     * Returns transaction by transaction id
     *
     * @param transactionId: input parameter of transaction id
     * @return transaction
     * @throws TransactionNotFoundException
     */
    public Transaction getTransaction(Long transactionId) throws TransactionNotFoundException {
        return transactions.stream()
                .filter(t -> t.getId().equals(transactionId))
                .findFirst()
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

        try {
            //Get transaction, if it exists then update and if new add
            Transaction existingTransaction = getTransaction(transactionId);

            if (existingTransaction != null) {
                transactions.remove(existingTransaction);
            }
        } catch (Exception e) {
           log.info("Transaction not found. Hence, new transaction should be registered");
        }

        transactions.add(transaction);
        return transaction;
    }

    /**
     * Get all transactionIds by type
     *
     * @param type: type of transaction
     * @return List<Long>: list of transaction Ids that has the specified type
     */
    public List<Long> getAllTransactionIdsByType(String type) {

        // get a collection of all the ids.
        return transactions.stream()
                .filter(t -> t.getType().equals(type))
                .map(Transaction::getId)
                .collect(Collectors.toList());
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
