package Server.TeaTimeProjectRefactoring.service;

import Server.TeaTimeProjectRefactoring.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProgramService {

    private final ProgramRepository programRepository;
}
