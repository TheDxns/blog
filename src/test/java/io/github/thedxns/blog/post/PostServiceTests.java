package io.github.thedxns.blog.post;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import java.time.LocalDateTime;
import java.util.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostServiceTests {

    @Mock
	private PostService postService;

    @Mock
    private PostRepository postRepository;

    public LocalDateTime getTestDate() {
        return LocalDateTime.parse("2019-05-08T11:33:08.863");
    }

    public void setUpTestPost() {
        Post post1 = new Post();
        post1.setTitle("A new test post");
        post1.setContent("<h1>This post was created for unit testing purpose.</h1>");
        post1.setCreatorUsername("user");
        post1.setCategory("Test");
        post1.setCreatedOn(getTestDate());
        doReturn(post1).when(postRepository).getById(1);
    }

    public static String generateRandomString(int length) {
		String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk"
          +"lmnopqrstuvwxyz!@#$%&";
		Random rnd = new Random();
		StringBuilder stringBuilder = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
            stringBuilder.append(chars.charAt(rnd.nextInt(chars.length())));
        }
		return stringBuilder.toString();
	}

    @Test
    @DisplayName("The context should not be null")
	public void contextLoads() throws Exception {
		Assertions.assertNotNull(postService);
    }

    @Test
    @DisplayName("Successful creation of a post entity")
    public void createPostEntity() {
        setUpTestPost();
        Post newPost = new Post(postRepository.getById(1));
        Assertions.assertEquals("A new test post", newPost.getTitle());
        Assertions.assertEquals("<h1>This post was created for unit testing purpose.</h1>", newPost.getContent());
        Assertions.assertEquals("user", newPost.getCreatorUsername());
        Assertions.assertEquals("Test", newPost.getCategory());
        Assertions.assertEquals(getTestDate(), newPost.getCreatedOn());
    }

    @Test
    @DisplayName("After saving a post the sneak peak should not be null or too long")
    public void checkSneakPeakAfterSave() {
        setUpTestPost();
        PostRepository mockPostRepository = mock(PostRepository.class);
        PostService testPostService = new PostService(mockPostRepository);
        Post newPost = new Post(postRepository.getById(1));
        newPost.setContent(generateRandomString(1000));
        testPostService.savePost(newPost);
        Assertions.assertNotNull(newPost.getSneakPeak());
        Assertions.assertEquals(true, newPost.getSneakPeak().length() == 993);
    }

    @Test
    @DisplayName("After updating the post the sneak peak should not be null or too long")
    public void checkSneakPeakAfterUpdate() {
        setUpTestPost();
        PostRepository mockPostRepository = mock(PostRepository.class);
        PostService testPostService = new PostService(mockPostRepository);
        Post newPost = new Post(postRepository.getById(1));
        newPost.setContent(generateRandomString(1000));
        testPostService.updatePost(1, newPost);
        Assertions.assertNotNull(newPost.getSneakPeak());
        Assertions.assertEquals(true, newPost.getSneakPeak().length() == 993);
    }



}