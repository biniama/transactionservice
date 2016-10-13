package com.n26.repository;

import com.n26.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;
import java.util.Optional;

/**
 * @RepositoryDefinition is chosen because it is possible to override the return type to Optional
 * which is used for returning custom exception (using Java 8's orElseThrow) if transactions are not found.
 *
 * @author Biniam Asnake
 */
@RepositoryDefinition(domainClass = Transaction.class, idClass = Long.class)
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> findOneById(Long transactionId);

    Optional<List<Transaction>> findAllByType(String type);

    Optional<List<Transaction>> findAllByIdOrParentId(Long id, Long parentId);
}