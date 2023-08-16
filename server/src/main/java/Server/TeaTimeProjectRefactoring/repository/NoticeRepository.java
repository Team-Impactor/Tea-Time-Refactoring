package Server.TeaTimeProjectRefactoring.repository;

import Server.TeaTimeProjectRefactoring.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

}
