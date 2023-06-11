package telran.java47.service;

import java.util.List;

import telran.java47.post.dto.DatePeriodDto;
import telran.java47.post.dto.NewCommentDto;
import telran.java47.post.dto.NewPostDto;
import telran.java47.post.dto.PostDto;

public interface PostService {
PostDto addPost(String author,NewPostDto newPostDto);
PostDto findPostById(String id);

PostDto addComment(String id,String author,NewCommentDto newCommentDto);
PostDto removePost(String id);
PostDto updatePost(String id,NewPostDto newPostDto);
void addLike(String id);
Iterable<PostDto> findPostsByAuthor(String author);
Iterable<PostDto>findPostsByTags(List<String>tags);
Iterable<PostDto>findPostsByPeriod(DatePeriodDto datePeriodDto);

}
