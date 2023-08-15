package Server.TeaTimeProjectRefactoring.service;

import Server.TeaTimeProjectRefactoring.dto.ProgramDto;
import Server.TeaTimeProjectRefactoring.entity.Program;
import Server.TeaTimeProjectRefactoring.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProgramService {

    private final ProgramRepository programRepository;

    public Program createProgramLogic(ProgramDto.Post data) {

        Program program = Program.createOf(
            data.getTitle(),
            data.getContent(),
            data.getUserMax(),
            data.getCost(),
            data.getImage(),
            data.getAnnounce(),
            data.getZoomLink(),
            data.getDateStart(),
            data.getDateEnd()
        );

        return programRepository.save(program);
    }
}
