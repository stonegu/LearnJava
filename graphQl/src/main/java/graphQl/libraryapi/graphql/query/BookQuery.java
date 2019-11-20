package graphQl.libraryapi.graphql.query;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import graphQl.libraryapi.domain.book.Book;
import graphQl.libraryapi.domain.book.BookService;
import graphQl.libraryapi.rest.book.BookDto;

@Component
public class BookQuery implements GraphQLQueryResolver {

	@Autowired
	private BookService bookService;

	@Autowired
	private ModelMapper modelMapper;

	public List<BookDto> getBooks() {
		return bookService.getAllBooks().stream()
				.map(book -> modelMapper.map(book, BookDto.class))
				.collect(Collectors.toList());		
	}
	
	public BookDto getBook(Long id) {
		Optional<Book> book = bookService.getBookById(id);
		if (!book.isPresent()) {
			return null;
		}
		return modelMapper.map(book.get(), BookDto.class);		
	}
}
