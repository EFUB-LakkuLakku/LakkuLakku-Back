package com.efub.lakkulakku.domain.users.service;

import com.efub.lakkulakku.domain.users.dao.CertificationDao;
import com.efub.lakkulakku.domain.users.dto.CertificationReqDto;
import com.efub.lakkulakku.domain.users.exception.CertificationCodeMismatchException;
import com.efub.lakkulakku.domain.users.vo.MailVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MailSendService {
	private final JavaMailSender mailSender;
	private final CertificationDao certificationDao;

	private static final String TITLE = "라꾸라꾸 인증 코드 안내 이메일입니다.";
	private static final String MESSAGE = "라꾸라꾸 인증 코드 안내 메일입니다. " + "\n"
			+ "\n" + "안녕하세요. 웹 기반 공유 다이어리 서비스, 라꾸라꾸입니다. :) " + "\n"
			+ "\n" + "회원님의 임시 인증 번호는 아래와 같습니다. 인증 코드로 인증 후 반드시 비밀번호를 변경해주세요." + "\n \n";
	private static final String FROM_ADDRESS = "noreply.lakkulakku@gmail.com";

	/**
	 * 이메일 생성
	 **/


	public MailVo createMail(String tempPwd, String email) {

		MailVo mailVo = MailVo.builder()
				.toAddress(email)
				.title(TITLE)
				.message(MESSAGE + tempPwd)
				.fromAddress(FROM_ADDRESS)
				.build();

		log.info("메일 생성 완료");
		return mailVo;
	}

	/**
	 * 이메일 전송
	 **/

	public void sendMail(MailVo mailVo) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(mailVo.getToAddress());
		mailMessage.setSubject(mailVo.getTitle());
		mailMessage.setText(mailVo.getMessage());
		mailMessage.setFrom(mailVo.getFromAddress());
		mailMessage.setReplyTo(mailVo.getFromAddress());

		mailSender.send(mailMessage);

		log.info("메일 전송 완료");
	}

	//사용자가 입력한 인증번호가 Redis에 저장된 인증번호와 동일한지 확인
	public void verifyEmail(CertificationReqDto reqDto) {
		if (isVerify(reqDto)) {
			throw new CertificationCodeMismatchException();
		}
		certificationDao.removeEmailCertification(reqDto.getEmail());
	}

	private boolean isVerify(CertificationReqDto reqDto) {
		return !(certificationDao.hasKey(reqDto.getEmail()) &&
				certificationDao.getEmailCertification(reqDto.getEmail())
						.equals(reqDto.getCertiCode()));
	}

}
