package cars.rus.Service.MemberService;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cars.rus.DTO.MemberDTO.ExtendedMemberDTO;
import cars.rus.DTO.MemberDTO.MemberDTO;
import cars.rus.Entities.Member;
import cars.rus.Repositories.MemberRepository;
import cars.rus.Utils.Converters.MemberDTOconverter;

@Service
public class MemberServiceImpl implements MemberService {
  private MemberRepository memberRepository;
  private MemberDTOconverter memberDTOconverter = new MemberDTOconverter();

  public MemberServiceImpl(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  public Collection<ExtendedMemberDTO> findAllMembers(boolean extended) {
    Collection<Member> allMembers = memberRepository.findAll();
    return extended
        ? allMembers.stream().map(member -> memberDTOconverter.convertToExtendedMemberDto(member))
            .collect(Collectors.toList())
        : allMembers.stream()
            .map(member -> memberDTOconverter.convertToExtendedMemberDto(memberDTOconverter.convertToMemberDto(member)))
            .collect(Collectors.toList());
  };

  public ExtendedMemberDTO findMemberByEmail(String email, boolean extended) {
    Optional<Member> foundMember = memberRepository.findMemberByEmail(email);
    return extended ? memberDTOconverter.convertToExtendedMemberDto(foundMember.get())
        : memberDTOconverter.convertToExtendedMemberDto(memberDTOconverter.convertToMemberDto(foundMember.get()));
  }

  public Collection<ExtendedMemberDTO> findMembersByApproved(boolean isApproved, boolean extended) {
    Collection<Member> foundMember = memberRepository.findMembersByApproved(isApproved);
    return extended
        ? foundMember.stream().map(member -> memberDTOconverter.convertToExtendedMemberDto(member))
            .collect(Collectors.toList())
        : foundMember.stream()
            .map(member -> memberDTOconverter.convertToExtendedMemberDto(memberDTOconverter.convertToMemberDto(member)))
            .collect(Collectors.toList());
  }

  public MemberDTO updateOrAddMember(MemberDTO memberDTO, Long id) {
    Optional<Member> foundMember = memberRepository.findById(id);
    Member newMember;
    if (!foundMember.isPresent()) {
      newMember = memberRepository.save(memberDTOconverter.convertToEntity(memberDTO));
    } else {
      foundMember.get().setFirstName(memberDTO.getFirstName());
      foundMember.get().setLastName(memberDTO.getLastName());
      foundMember.get().setStreet(memberDTO.getStreet());
      foundMember.get().setCity(memberDTO.getCity());
      foundMember.get().setZip(memberDTO.getZip());
      foundMember.get().setEmail(memberDTO.getEmail());
      newMember = memberRepository.save(foundMember.get());
    }
    return memberDTOconverter.convertToMemberDto(newMember);
  }

  public ExtendedMemberDTO findMemberById(Long id, boolean extended) {
    Optional<Member> foundMember = memberRepository.findById(id);
    return extended ? memberDTOconverter.convertToExtendedMemberDto(foundMember.get())
        : memberDTOconverter.convertToExtendedMemberDto(memberDTOconverter.convertToMemberDto(foundMember.get()));
  }

  public void deleteMemberById(Long id) {
    Optional<Member> foundMember = memberRepository.findById(id);
    if (!foundMember.isPresent()) {
      return;
    }
    memberRepository.deleteMemberById(id);
  }

  public MemberDTO addMember(MemberDTO memberDTO) {
    Member newMember = memberRepository.save(memberDTOconverter.convertToEntity(memberDTO));
    return memberDTOconverter.convertToMemberDto(newMember);
  }

}
