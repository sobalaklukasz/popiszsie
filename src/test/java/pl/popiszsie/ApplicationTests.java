package pl.popiszsie;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import pl.popiszsie.service.BooksService;
import pl.popiszsie.util.BookSaveFailedException;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Autowired
    PlatformTransactionManager platformTransactionManager;

    @Autowired
    DataSource dataSource;

    @Autowired
    BooksService booksService;

    private final static String TEST_BOOK_TEST = "test book text";

    @Test
    public void checkForPlatformTransactionManagerBean() {
        Assert.assertNotNull(platformTransactionManager);
    }

    @Test
    public void checkForEntityManagerFactoryBean() {
        Assert.assertNotNull(entityManagerFactory);
    }

    @Test
    public void checkForDataSourceBean() {
        Assert.assertNotNull(dataSource);
    }

    @Test
    public void checkAddingBookToDatabase() throws BookSaveFailedException {
        booksService.putBook(TEST_BOOK_TEST);
    }

    @Test
    public void checkGettingBookFromDatabase() throws BookSaveFailedException {
        booksService.putBook(TEST_BOOK_TEST);

        Assert.assertEquals(booksService.getBookById((long) 1).getText(), TEST_BOOK_TEST);
    }

    @Test
    public void checkGettingAllBooksFromDatabase() throws BookSaveFailedException {
        Assert.assertNotNull(booksService.getAllBooks());
    }
}
