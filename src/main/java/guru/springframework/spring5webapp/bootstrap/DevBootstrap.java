package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private PublisherRepository publisherRepository;
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {

        Publisher harperCollins = new Publisher("Harper Collins", "HarperCollins, New York");
        Publisher worx = new Publisher("Worx", "Worx, New York, USA");

        Author eric = new Author("Aric", "Evans");
        Book ddd = new Book("Domain Drive Design", "ISBN: 1234", harperCollins);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        publisherRepository.save(harperCollins);
        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "Johnson");
        Book noEjb = new Book("J2EE Development without EJB", "ISBN: 2345", worx);
        rod.getBooks().add(noEjb);
        noEjb.getAuthors().add(rod);

        publisherRepository.save(worx);
        authorRepository.save(rod);
        bookRepository.save(noEjb);

    }
}
