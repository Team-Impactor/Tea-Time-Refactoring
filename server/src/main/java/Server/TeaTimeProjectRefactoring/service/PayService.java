package Server.TeaTimeProjectRefactoring.service;

import Server.TeaTimeProjectRefactoring.dto.PayDto;
import Server.TeaTimeProjectRefactoring.entity.Member;
import Server.TeaTimeProjectRefactoring.entity.Pay;
import Server.TeaTimeProjectRefactoring.global.error.BusinessException;
import Server.TeaTimeProjectRefactoring.global.error.ErrorCode;
import Server.TeaTimeProjectRefactoring.repository.PayRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PayService {

    private final PayRepository payRepository;
    private final MemberService memberService;


    public Pay createPayLogic(PayDto.Post data) {
        Member member = memberService.findVerifyMemberByMemberId(data.getMemberId());

        Pay pay = Pay.createOf(
            data.getCardOwner(),
            data.getCardNum(),
            data.getCvvNum(),
            data.getExpirationTime(),
            member.getMemberId()
        );

        return payRepository.save(pay);
    }

    public Pay updatePayLogic(PayDto.Patch data, Long payId) {
        Pay pay = payRepository.getReferenceById(payId);

        pay.update(
            data.getCardOwner(),
            data.getCardNum(),
            data.getCvvNum(),
            data.getExpirationTime()
        );

        return payRepository.save(pay);
    }

    public Pay findVerityPayByPayId(Long payId) {
        Optional<Pay> optionalPay = payRepository.findById(payId);

        Pay findPay = optionalPay.orElseThrow(
            () -> new BusinessException(ErrorCode.TEST)
        );

        return findPay;
    }

    public Page<Pay> getAllPayLogic(int page, int size) {
        return payRepository.findAll(
            PageRequest.of(page, size, Sort.by("payId").descending())
        );
    }

    public void deletePayLogic(Long payId) {
        Pay pay = payRepository.getReferenceById(payId);
        payRepository.delete(pay);
    }
}
