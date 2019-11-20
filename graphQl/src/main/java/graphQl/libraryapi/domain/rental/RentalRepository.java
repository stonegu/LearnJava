package graphQl.libraryapi.domain.rental;

import java.time.LocalDate;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import graphQl.libraryapi.domain.book.Book;
import graphQl.libraryapi.domain.cardholder.CardHolder;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

	Collection<Rental> findRentalsByBook(Book book);

	Collection<Rental> findRentalsByBookId(Long bookId);

	Collection<Rental> findRentalsByCardHolder(CardHolder cardHolder);

	Collection<Rental> findRentalsByDueDateAfter(LocalDate date);

	Collection<Rental> findRentalsByDueDateBefore(LocalDate date);

}
