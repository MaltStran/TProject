package parser;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.tinkoff.edu.java.linkParser.ParserURL;
import ru.tinkoff.edu.java.linkParser.parsers.GitHubParser;
import ru.tinkoff.edu.java.linkParser.parsers.StackOverflowParser;
import ru.tinkoff.edu.java.linkParser.records.Result;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class parserTest {

    private StackOverflowParser stackOverflowParser = new StackOverflowParser();

    private GitHubParser gitHubParser = new GitHubParser();

    private ParserURL parserURL = new ParserURL();

    @Test
    public void githubParserTest(){
        Result resNull1 = gitHubParser.parse(null);
        assertAll(() -> assertNull(resNull1));
        Result resNull2 = gitHubParser.parse("123");
        assertAll(() -> assertNull(resNull2));
        Result resNull3 = gitHubParser.parse("https://stackoverflow.com/questions/1642028/what-is-the-operator-in-c");
        assertAll(() -> assertNull(resNull3));
        Result resNull4 = gitHubParser.parse("https://github.com/sanyarnd");
        assertAll(() -> assertNull(resNull4));
        Result res = gitHubParser.parse("https://github.com/sanyarnd/tinkoff-java-course-2022/");
        assertAll(() -> assertEquals(res.toString(), "GitHubRecord[userName=sanyarnd, repName=tinkoff-java-course-2022]"));
    }

    @Test
    public void stackOverlowParserTest(){
        Result resNull1 = stackOverflowParser.parse(null);
        assertAll(() -> assertNull(resNull1));
        Result resNull2 = stackOverflowParser.parse("123");
        assertAll(() -> assertNull(resNull2));
        Result resNull3 = stackOverflowParser.parse("https://github.com/sanyarnd/tinkoff-java-course-2022/");
        assertAll(() -> assertNull(resNull3));
        Result resNull4 = stackOverflowParser.parse("https://stackoverflow.com/questions/1642028");
        assertAll(() -> assertNull(resNull4));
        Result res = stackOverflowParser.parse("https://stackoverflow.com/questions/1642028/what-is-the-operator-in-c");
        assertAll(() -> assertEquals(res.toString(), "StackOverFlowRecord[id=1642028]"));
    }

    @Test
    public void fullParser(){
        Result resNull1 = parserURL.parse(null);
        assertAll(() -> assertNull(resNull1));
        Result resNull2 = parserURL.parse("https://github.com/sanyarnd");
        assertAll(() -> assertNull(resNull2));
        Result res1= parserURL.parse("https://stackoverflow.com/questions/1642028/what-is-the-operator-in-c");
        assertAll(() -> assertEquals(res1.toString(), "StackOverFlowRecord[id=1642028]"));
        Result res2 = parserURL.parse("https://github.com/sanyarnd/tinkoff-java-course-2022/");
        assertAll(() -> assertEquals(res2.toString(), "GitHubRecord[userName=sanyarnd, repName=tinkoff-java-course-2022]"));
    }
}
