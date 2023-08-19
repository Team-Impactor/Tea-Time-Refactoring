package Server.TeaTimeProjectRefactoring.controller;

import Server.TeaTimeProjectRefactoring.dto.MultiResponseDto;
import Server.TeaTimeProjectRefactoring.dto.PayDto;
import Server.TeaTimeProjectRefactoring.entity.Pay;
import Server.TeaTimeProjectRefactoring.service.PayService;
import java.util.List;
import javax.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pays")
@RequiredArgsConstructor
public class PayController {

    private final PayService payService;

    /**
     *
     * @param data : cardOwner, cardNum, cvvNum, expirationTime, memberId
     * @return : PayDto.Response
     * @desc : 결제 등록
     */
    @PostMapping("/post")
    public ResponseEntity<PayDto.Response> postPay(
        @RequestBody PayDto.Post data
    ) {
        return new ResponseEntity<>(
            PayDto.Response.fromEntity(payService.createPayLogic(data
            )),
            HttpStatus.CREATED
        );
    }

    /**
     *
     * @param data : cardOwner, cardNum, cvvNum, expirationTime
     * @param payId
     * @return : PayDto.Response
     * @desc : 결제 수정
     */
    @PatchMapping("/patch/{pay-id}")
    public ResponseEntity<PayDto.Response> patchPay(
        @RequestBody PayDto.Patch data,
        @PathVariable("pay-id") @Positive Long payId
    ) {

        return new ResponseEntity<>(
            PayDto.Response.fromEntity(payService.updatePayLogic(data, payId)),
            HttpStatus.OK
        );
    }

    /**
     *
     * @param payId
     * @return : PayDto.Response
     * @desc : 결제 개별 조회
     */
    @GetMapping("/lookup/{pay-id}")
    public ResponseEntity<PayDto.Response> findPay(
        @PathVariable("pay-id") @Positive Long payId
    ) {
        return new ResponseEntity<>(
            PayDto.Response.fromEntity(payService.findVerityPayByPayId(payId)),
            HttpStatus.OK
        );
    }

    /**
     *
     * @param page
     * @param size
     * @return : MultiResponseDto
     * @desc : 결제 전체 조회
     */
    @GetMapping("/lookup/list")
    public ResponseEntity findAllPay(
        @RequestParam(defaultValue = "1") @Positive int page,
        @RequestParam(defaultValue = "10") @Positive int size
    ) {
        Page<Pay> payPage = payService.getAllPayLogic(page - 1, size);

        List<Pay> payList = payPage.getContent();
        List<PayDto.PayPageResponse> responseList = PayDto.PayPageResponse.fromAllPayEntity(
            payList);
        return new ResponseEntity<>(
            new MultiResponseDto<>(responseList, payPage),
            HttpStatus.OK
        );
    }

    /**
     *
     * @param payId
     * @return : HttpStatus.NO_CONTENT
     * @desc : 결제 개별 삭제
     */
    @DeleteMapping("/delete/{pay-id}")
    public ResponseEntity deletePay(
        @PathVariable("pay-id") @Positive Long payId
    ) {

        payService.deletePayLogic(payId);

        return new ResponseEntity<>(
            HttpStatus.NO_CONTENT
        );
    }
}
