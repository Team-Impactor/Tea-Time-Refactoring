package Server.TeaTimeProjectRefactoring.repository;

import Server.TeaTimeProjectRefactoring.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
