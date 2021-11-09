package io.github.thedxns.blog;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import io.github.thedxns.blog.controller.PostController;
import io.github.thedxns.blog.logic.PostService;
import io.github.thedxns.blog.model.Post;

@SpringBootTest
public class PostControllerTests {
    
    @Mock
	private PostController postController;

	@Test
    @DisplayName("The context should not be null")
	public void contextLoads() throws Exception {
		Assertions.assertNotNull(postController);
    }
    
    @Test
    @DisplayName("Delete post endpoint should return 404 Not Found response if there is no post of given ID")
    public void deletePostWhenNoPostFoundReturns404Response() {
        PostService mockPostService = mock(PostService.class);
        when(mockPostService.existsById(1)).thenReturn(false);
        PostController testPostController = new PostController(mockPostService);
        Assertions.assertEquals(ResponseEntity.notFound().build(), testPostController.deletePost(1));
    }
    
    @Test
    @DisplayName("Update post endpoint should return 404 Not Found response if there is no post of given ID")
    public void updatePostWhenNoPostFoundReturns404Response() {
        PostService mockPostService = mock(PostService.class);
        when(mockPostService.existsById(1)).thenReturn(false);
        PostController testPostController = new PostController(mockPostService);
        Assertions.assertEquals(ResponseEntity.notFound().build(), testPostController.updatePost(1, new Post()));
    }

    @Test
    @DisplayName("Delete post endpoint should return 500 Internal Error response if the post service could not delete the post")
    public void deletePostWhenPostCouldNotBeDeletedReturns500Response() {
        PostService mockPostService = mock(PostService.class);
        when(mockPostService.existsById(1)).thenReturn(true);
        when(mockPostService.deletePost(1)).thenReturn(false);
        PostController testPostController = new PostController(mockPostService);
        Assertions.assertEquals(ResponseEntity.internalServerError().build(), testPostController.deletePost(1));
    }

    @Test
    @DisplayName("Update post endpoint should return 500 Internal Error response if thepost service could not update the post")
    public void updatePostWhenPostCouldNotBeUpdatedReturns500Response() {
        PostService mockPostService = mock(PostService.class);
        when(mockPostService.existsById(1)).thenReturn(true);
        when(mockPostService.updatePost(1, new Post())).thenReturn(false);
        PostController testPostController = new PostController(mockPostService);
        Assertions.assertEquals(ResponseEntity.internalServerError().build(), testPostController.updatePost(1, new Post()));
    }

    @Test
    @DisplayName("Delete post endpoint should return 200 OK response if the post service deleted the post")
    public void deletePostWhenPostCouldBeDeletedReturns200Response() {
        PostService mockPostService = mock(PostService.class);
        when(mockPostService.existsById(1)).thenReturn(true);
        when(mockPostService.deletePost(1)).thenReturn(true);
        PostController testPostController = new PostController(mockPostService);
        Assertions.assertEquals(ResponseEntity.ok().build(), testPostController.deletePost(1));
    }

    @Test
    @DisplayName("Update post endpoint should return 200 OK response if the post service updated the post")
    public void updatePostWhenPostCouldBeUpdatedReturns200Response() {
        PostService mockPostService = mock(PostService.class);
        when(mockPostService.existsById(1)).thenReturn(true);
        when(mockPostService.updatePost(1, new Post())).thenReturn(true);
        PostController testPostController = new PostController(mockPostService);
        Assertions.assertEquals(ResponseEntity.internalServerError().build(), testPostController.updatePost(1, new Post()));
    }
}