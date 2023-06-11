package telran.java47.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import telran.java47.post.dto.DatePeriodDto;
import telran.java47.post.dto.PostDto;
import telran.java47.post.model.Post;

public interface PostRepository extends MongoRepository<Post, String> {
	List<PostDto> findPostsByAuthor(String author);
	List<PostDto> findPostsByTags(List<String> tags);
	@Query("dateCreated:{$gte:ISODate(?0),$lt:ISODate()?1 }")
	List<PostDto>findPostsByPeriod(LocalDate dateFrom,LocalDate dateTo);
//	List<PostDto> findPostsByPeriod(DatePeriodDto datePeriodDto);
}
