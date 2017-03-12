package sdudoc;

import com.sdudoc.bean.Book;
import com.sdudoc.bean.User;
import com.sdudoc.service.BookService;
import com.sdudoc.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhangjk on 2017/3/11.
 */
public class TestBookService {

    @Test
    public void testAddBook() throws InterruptedException {
        ApplicationContext ac = new ClassPathXmlApplicationContext(
                "classpath:applicationContext.xml");
        BookService bookService = (BookService) ac.getBean("bookService");

        String bookTitles[] = "史记，汉书，后汉书 ，三国志，晋书，宋书，南齐书，梁书，陈书，魏书，北齐书，周书 隋书，南史，北史，旧唐书，新唐书，旧五代史，新五代史，宋史，辽史 ，金史，元史，明史".split("，");
        String bookPatterns[] = "著，编，辑，译，合著，合编，合辑，合译，注释".split("，");
        String bookClasses[] = "马列毛，哲学，社科，经济，军事，法律，教、科、文、体，艺术，自然科学，语言，文学，历史，地理，医药卫生，工程技术，农业科学，综合参考".split("，");
        String bookPositions[] = "高，中，低".split("，");
        String bookStyles[] = "纪传体，国别体，编年体，纪实本末体".split("，");
        String bookVersions[] = "孤本，珍本，善本，禁书本，进呈本，底本，巾箱本，通行本，足本，节本，选本，配本，百衲本，从书本，单行本，邋遢本，赝本，秘本，禁毁本，绣像本，石印本，手抄本，补本，保留本".split("，");

        String dynasties[] = "夏朝， 商朝， 西周，东周， 春秋， 战国，秦朝，唐，宋，元，明，清".split("，");
        int bookNum = 100;

        for (int i = 0; i < bookNum; i++) {
            Book book = new Book();
            Random random = new Random();
            book.setAuthors("作者" + random.nextInt(50));
            book.setBookTitle(bookTitles[random.nextInt(bookTitles.length)] + i );
            book.setBookPatterns(bookPatterns[random.nextInt(bookPatterns.length)] );
            book.setBookClass(bookClasses[random.nextInt(bookClasses.length)]);
            book.setBookPosition(bookPositions[random.nextInt(bookPositions.length)]);
            book.setBookStyle(bookStyles[random.nextInt(bookStyles.length)]);
            book.setBookVersion(bookVersions[random.nextInt(bookVersions.length)]);
            book.setYear(new Date());
            book.setDynasty(dynasties[random.nextInt(dynasties.length)]);
            book.setTotalVolume(100 + random.nextInt(1000));
            book.setTotalChapter(10 + random.nextInt(100));
            book.setSummary(book.getBookTitle() + book.getBookClass() + book.getBookPatterns() + book.getBookStyle() + book.getBookVersion() + book.getDynasty());
            bookService.addBook(book);

            //System.out.println(random.nextInt(bookPatterns.length));
        }
        System.out.println(bookPatterns.length);
    }
}
