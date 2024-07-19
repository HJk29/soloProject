package com.springboot.member.mapper;

import com.springboot.member.dto.MemberDto;
import com.springboot.member.entity.Member;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostDtoToMember(MemberDto.Post memberPostDto);
    MemberDto.Response memberToMemberResponseDto(Member member);
    Member memberPatchDtoToMember(MemberDto.Patch memberPatchDto);

    List<MemberDto.Response> membersToMemberResponses(List<Member> members);
}
