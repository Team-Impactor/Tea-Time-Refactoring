package Server.TeaTimeProjectRefactoring.controller;

import Server.TeaTimeProjectRefactoring.dto.ProgramDto;
import Server.TeaTimeProjectRefactoring.service.ProgramService;
import javax.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : 이대겸
 * @desc : Program 컨트롤러
 */
@RestController
@RequestMapping("/api/programs")
@RequiredArgsConstructor
public class ProgramController {
    private final ProgramService programService;

    /**
     *
     * @param data : title, content, userMax, cost, image, announce, zoomLink, dateStart, dateEnd
     * @return : ProgramDto.Response
     * @desc : 프로그램 등록
     */
    @PostMapping("/post")
    public ResponseEntity<ProgramDto.Response> postProgram(
        @RequestBody ProgramDto.Post data
    ) {
        return new ResponseEntity<>(
            ProgramDto.Response.fromEntity(programService.createProgramLogic(data)),
            HttpStatus.CREATED
        );
    }

    /**
     *
     * @param data : title, content, userMax, cost, image, announce, zoomLink, dateStart, dateEnd
     * @param programId
     * @return : ProgramDto.Response
     * @desc : 프로그램 정보 수정
     */
    @PatchMapping("/patch/{program-id}")
    public ResponseEntity patchProgram(
        @RequestBody ProgramDto.Patch data,
        @PathVariable("program-id") @Positive Long programId
    ) {
        return new ResponseEntity(
            ProgramDto.Response.fromEntityOfPatch(programService.updateProgramLogic(data, programId)),
            HttpStatus.OK
        );
    }

    @GetMapping("/lookup/{program-id}")
    public ResponseEntity findProgram() {
        return new ResponseEntity(
            HttpStatus.OK
        );
    }

    @GetMapping("/lookup/list")
    public ResponseEntity findAllProgram() {
        return new ResponseEntity(
            HttpStatus.OK
        );
    }

    @DeleteMapping("/delete/{program-id}")
    public ResponseEntity deleteProgram() {
        return new ResponseEntity(
            HttpStatus.NO_CONTENT
        );
    }
}
