package telran.java47.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java47.dao.PostNotFoundExeption;
import telran.java47.dao.PostRepository;
import telran.java47.post.dto.DatePeriodDto;
import telran.java47.post.dto.NewCommentDto;
import telran.java47.post.dto.NewPostDto;
import telran.java47.post.dto.PostDto;
import telran.java47.post.model.Comment;
import telran.java47.post.model.Post;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
	final PostRepository postRepository;
	final ModelMapper modelMapper;

	@Override
	public PostDto addPost(String author, NewPostDto newPostDto) {
		if (author == null || newPostDto == null) {
			return null;
		}
		Post post = new Post(newPostDto.getTitle(), newPostDto.getContent(), author, newPostDto.getTags());
		postRepository.save(post);

		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto findPostById(String id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundExeption());
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto addComment(String id, String author, NewCommentDto newCommentDto) {
		Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundExeption());

		Comment comment = new Comment(author, newCommentDto.getMessage());
		post.addComment(comment);

		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto removePost(String id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundExeption());
		postRepository.delete(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto updatePost(String id, NewPostDto newPostDto) {
		Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundExeption());
		if (newPostDto.getContent() != null) {
			post.setContent(newPostDto.getContent());
		}
		if (newPostDto.getTitle() != null) {
			post.setTitle(newPostDto.getTitle());
		}if (newPostDto.getTags()!=null) {
			post.getTags().addAll(newPostDto.getTags());
		}postRepository.save(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override

	public void addLike(String id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundExeption());
		post.addLike();
		postRepository.save(post);
	}

	@Override
	public Iterable<PostDto> findPostsByAuthor(String author) {
		return   postRepository.findPostsByAuthor(author);
			
				
	}

	@Override
	public Iterable<PostDto> findPostsByTags(List<String> tags) {
		return postRepository.findPostsByTags(tags);
	}

	@Override
	public Iterable<PostDto> findPostsByPeriod(DatePeriodDto datePeriodDto) {
		
		return postRepository.findPostsByPeriod(datePeriodDto.getDateFrom(),datePeriodDto.getDateTo());
	}

}
