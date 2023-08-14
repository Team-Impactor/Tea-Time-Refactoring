package Server.TeaTimeProjectRefactoring.controller;

import Server.TeaTimeProjectRefactoring.service.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/programs")
@RequiredArgsConstructor
public class ProgramController {
    private final ProgramService programService;

    @PostMapping("/post")
    public ResponseEntity postProgram() {
        return new ResponseEntity(
            HttpStatus.CREATED
        );
    }

    @PatchMapping("/patch/{program-id}")
    public ResponseEntity patchProgram() {
        return new ResponseEntity(
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
