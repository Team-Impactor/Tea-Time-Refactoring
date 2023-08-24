package Server.TeaTimeProjectRefactoring.service;

import Server.TeaTimeProjectRefactoring.dto.CounselorDto;
import Server.TeaTimeProjectRefactoring.entity.Counselor;
import Server.TeaTimeProjectRefactoring.global.error.BusinessException;
import Server.TeaTimeProjectRefactoring.global.error.ErrorCode;
import Server.TeaTimeProjectRefactoring.repository.CounselorRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CounselorService {

    private final CounselorRepository counselorRepository;

    public Counselor createCounselorLogic(CounselorDto.Post data) {

        Counselor counselor = Counselor.createOf(
            data.getEmail(),
            data.getPassword(),
            data.getCounselorName(),
            data.getBirth(),
            data.getGraduated(),
            data.getProfile(),
            data.getCareer(),
            data.getIntroduce(),
            data.getExpertiseField()
        );

        return counselorRepository.save(counselor);
    }

    public Counselor updateCounselorLogic(CounselorDto.Patch data, Long counselorId) {
        Counselor counselor = counselorRepository.getReferenceById(counselorId);

        counselor.update(
            data.getEmail(),
            data.getPassword(),
            data.getCounselorName(),
            data.getBirth(),
            data.getGraduated(),
            data.getProfile(),
            data.getCareer(),
            data.getIntroduce(),
            data.getExpertiseField()
        );

        return counselorRepository.save(counselor);
    }

    public Counselor findVerifyCounselorByCounselorId(Long counselorId) {
        Optional<Counselor> optionalCounselor = counselorRepository.findById(counselorId);

        Counselor findCounselor = optionalCounselor.orElseThrow(
            () -> new BusinessException(ErrorCode.TEST)
        );

        return findCounselor;
    }

    public Page<Counselor> getAllCounselorLogic(int page, int size) {
        return counselorRepository.findAll(
            PageRequest.of(page, size, Sort.by("counselorId").descending())
        );
    }

    public void deleteCounselorLogic(Long counselorId) {
        Counselor counselor = counselorRepository.getReferenceById(counselorId);
        counselorRepository.delete(counselor);
    }
}
