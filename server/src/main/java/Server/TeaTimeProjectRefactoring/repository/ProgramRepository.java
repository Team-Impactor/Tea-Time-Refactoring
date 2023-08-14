package Server.TeaTimeProjectRefactoring.repository;

import Server.TeaTimeProjectRefactoring.entity.Program;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Program, Long> {

}
