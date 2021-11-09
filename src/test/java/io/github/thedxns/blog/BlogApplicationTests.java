package io.github.thedxns.blog;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {

    @Mock
    BlogApplication blogApplication;

    @Test
    @DisplayName("The context should not be null")
    void contextLoads() throws Exception {
		Assertions.assertNotNull(blogApplication);
    }
}
