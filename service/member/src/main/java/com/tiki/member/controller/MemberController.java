package com.tiki.member.controller;

import com.tiki.member.configuration.CryptAES256;
import com.tiki.member.domain.MemberDTO;
import com.tiki.member.service.MemberService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.HttpResource;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Member;
import java.util.Date;
import java.util.Random;

@Slf4j
@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public JavaMailSender javaMail;


    /**
     * 계정탈퇴
     * @param id
     * @return
     */
    @DeleteMapping("/mbr/Auth/{id}")
    public int deleteMember(@PathVariable("id") String id){
        return memberService.deleteMember(id);
    }


    /**
     * 이메일 인증
     * @param id
     * @return
     */
    @GetMapping("/mbr/Auth/emailrollcheck/{id}") // 이메일 보내기
    public String sendMail(@PathVariable("id") String id) {

        MemberDTO dto = memberService.selectMemberDetail(id);
        String email = dto.getMbrEmail();

        int leftLimit = 65; // A
        int rightLimit = 90; // Z
        int stringLength = 5;
        Random random = new Random();
        String emailKey = random.ints(leftLimit, rightLimit + 1)
                .limit(stringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        SimpleMailMessage simpleMessage = new SimpleMailMessage();
        simpleMessage.setSubject("TIKITAKA 이메일 인증");
        simpleMessage.setText("인증번호 : " + emailKey);
        simpleMessage.setTo(email);
        simpleMessage.setFrom("movegun1027@gmail.com");
        javaMail.send(simpleMessage);

        return emailKey;

    }


    /**
     * 상세정보 조회
     * @param id
     * @return
     */
    @GetMapping("/mbr/unAuth/{id}")
    public MemberDTO selectMemberById(@PathVariable("id") String id){
        return memberService.selectMemberDetail(id);
    }

    /**
     * 아이디 중복 유무 확인
     * @param id
     * @return
     */
    @GetMapping("/mbr/unAuth/existence/id/{id}")
    public boolean existIdCheck(@PathVariable("id") String id){
        if(memberService.existId(id)!=null){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 전화번호 주복 유무 확인
     * @param phone
     * @return
     */
    @GetMapping("/mbr/unAuth/existence/phone/{phone}")
    public boolean existPhoneCheck(@PathVariable("phone") String phone){
        if(memberService.existPhone(phone)!=null){
            return true;
        }else {
            return false;
        }
    }

    @GetMapping("/mbr/unAuth/existence/email/{email}")
    public boolean existEmailCheck(@PathVariable("email") String email){
        if(memberService.existEmail(email)!=null){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 비밀번호 찾기
     * @param id
     * @param name
     * @param phone
     * @return
     */
    @GetMapping("/mbr/unAuth/{id}/{name}/{phone}")
    public Boolean memberPasswordChecking(@PathVariable("id") String id,
                                          @PathVariable("name") String name,
                                          @PathVariable("phone") String phone){
        MemberDTO dto = new MemberDTO(name, id, phone);
        String exists = memberService.existPwd(dto);


        if(exists==null){
            return false;
        }
        else {
            return true;
        }

    }
    /*회원가입*/
    @PostMapping("/mbr/unAuth")
    public int insertMember(@RequestBody MemberDTO memberDTO){
        MemberDTO dto = memberDTO;
        dto.setMbrPwd(passwordEncoder.encode(dto.getMbrPwd()));

        return memberService.insertMember(dto);
    }


    /**
     * 로그인
     * @param memberDTO
     * @return
     */
    @PostMapping("/mbr/unAuth/login")
    public String loginChecking(@RequestBody MemberDTO memberDTO, HttpServletResponse response) throws Exception {
        MemberDTO dto = memberService.selectMemberDetail(memberDTO.getMbrId());
        if (dto != null && passwordEncoder.matches( memberDTO.getMbrPwd(),dto.getMbrPwd())) {
            String mbrId= dto.getMbrId();


            String token = Jwts.builder()
                    .setSubject(mbrId)
                    .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                    .signWith(SignatureAlgorithm.HS512,"tiki")
                    .compact();


            String result = CryptAES256.encryptAES256(token,mbrId);

            return result;
        } else {
            return null;
        }
    }

    /**
     * ID 확인
     * @param memberDTO
     * @return
     */
    @PostMapping("/mbr/unAuth/idcheck")
    public String idChecking(@RequestBody MemberDTO memberDTO) {
        MemberDTO dto = memberService.selectMemberDetail(memberDTO.getMbrId());
        if (dto != null) {
            return dto.getMbrId();
        } else {
            log.info("id not exit!!");
            return null;
        }
    }



    /**
     * ID찾기 기능
     * @param memberDTO
     * @return
     */
    @PostMapping("/mbr/unAuth/id")
    public String findMemberId(@RequestBody MemberDTO memberDTO){
        return  memberService.findMemberId(memberDTO);
    }


    /**
     * 판매권한 획득
     * @param memberDTO
     * @return mybatis는 update완료 시 성공한 행의 개수 반환
     */
    @PatchMapping("/mbr/Auth/role")
    public int updateEmailRole(@RequestBody MemberDTO memberDTO){
        return memberService.updateRole(memberDTO);
    }

    /**
     * 비밀번호 수정
     * @param memberDTO
     * @return mybatis는 update완료 시 성공한 행의 개수 반환
     */
    @PatchMapping("/mbr/unAuth/password")
    public int updatePassword(@RequestBody MemberDTO memberDTO){
        memberDTO.setMbrPwd(passwordEncoder.encode(memberDTO.getMbrPwd()));
        return memberService.updateMemberPwd(memberDTO);
    }

    /**
     * 개인정보 수정
     * @param memberDTO
     * @return mybatis는 update완료 시 성공한 행의 개수 반환
     */
    @PatchMapping("/mbr/Auth/info")
    public int updateMemberInformation(@RequestBody MemberDTO memberDTO){
        return memberService.updateMemberInfo(memberDTO);
    }



    /**
     * 포인트 획득
     * @param memberDTO
     * @return mybatis는 update완료 시 성공한 행의 개수 반환
     */
    @PatchMapping("/mbr/Auth/points")
    public int updateMemberPoints(@RequestBody MemberDTO memberDTO){
        return memberService.updateMemberPoints(memberDTO);
    }

    /**
     * 등급변경 가능 시, 자동 변경
     * @param memberDTO
     * @return ybatis는 update완료 시 성공한 행의 개수 반환
     */
    @PatchMapping("/mbr/Auth/grade")
    public int updateMemberGrade(@RequestBody MemberDTO memberDTO){
        return memberService.updateMemberGrade(memberDTO);
    }
}
