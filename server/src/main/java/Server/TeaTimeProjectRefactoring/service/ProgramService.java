package Server.TeaTimeProjectRefactoring.service;

import Server.TeaTimeProjectRefactoring.dto.ProgramDto;
import Server.TeaTimeProjectRefactoring.entity.Member;
import Server.TeaTimeProjectRefactoring.entity.Program;
import Server.TeaTimeProjectRefactoring.global.error.BusinessException;
import Server.TeaTimeProjectRefactoring.global.error.ErrorCode;
import Server.TeaTimeProjectRefactoring.repository.ProgramRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProgramService {

    private final ProgramRepository programRepository;
    private final MemberService memberService;

    public Program createProgramLogic(ProgramDto.Post data) {
        Member member = memberService.findVerifyMemberByMemberId(data.getMemberId());

        Program program = Program.createOf(
            data.getTitle(),
            data.getContent(),
            data.getUserMax(),
            data.getCost(),
            data.getImage(),
            data.getAnnounce(),
            data.getZoomLink(),
            data.getDateStart(),
            data.getDateEnd(),
            member.getMemberId()
        );

        return programRepository.save(program);
    }

    public Program updateProgramLogic(ProgramDto.Patch data, Long programId) {
        Program program = programRepository.getReferenceById(programId);

        program.update(
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

    public Program findVerifyProgramByProgramId(Long programId) {
        Optional<Program> optionalProgram = programRepository.findById(programId);

        Program findProgram = optionalProgram.orElseThrow(
            () -> new BusinessException(ErrorCode.TEST)
        );

        return findProgram;
    }

    public void deleteProgramLogic(Long programId) {
        Program program = programRepository.getReferenceById(programId);
        programRepository.delete(program);
    }

    public Page<Program> getAllProgramLogic(int page, int size) {
        return programRepository.findAll(
            PageRequest.of(page, size, Sort.by("programId").descending()));
    }
}
