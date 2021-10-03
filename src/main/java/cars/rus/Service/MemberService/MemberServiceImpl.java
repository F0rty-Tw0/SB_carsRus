package cars.rus.Service.MemberService;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cars.rus.DTO.MemberDTO.ExtendedMemberDTO;
import cars.rus.DTO.MemberDTO.MemberDTO;
import cars.rus.Entities.Member;
import cars.rus.ExceptionHandler.ResourceNotFoundException;
import cars.rus.Repositories.MemberRepository;
import cars.rus.Utils.Converters.MemberDTOconverter;

@Service
public class MemberServiceImpl implements MemberService {
  private MemberRepository memberRepository;
  private MemberDTOconverter memberDTOconverter = new MemberDTOconverter();

  private String errorMessage(long id) {
    return "Could not find the Member with the id: " + id;
  }

  private String errorMessage() {
    return "Could not find any Members";
  }

  public MemberServiceImpl(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  @Override
  public Collection<ExtendedMemberDTO> findAllMembers(boolean extended) {
    Collection<Member> allMembers = memberRepository.findAll();
    if (!allMembers.isEmpty()) {
      return extended
          ? allMembers.stream().map(member -> memberDTOconverter.convertToExtendedMemberDto(member))
              .collect(Collectors.toList())
          : allMembers.stream().map(
              member -> memberDTOconverter.convertToExtendedMemberDto(memberDTOconverter.convertToMemberDto(member)))
              .collect(Collectors.toList());
    }
    throw new ResourceNotFoundException(errorMessage());
  };

  @Override
  public ExtendedMemberDTO findMemberByEmail(String email, boolean extended) {
    Member foundMember = memberRepository.findMemberByEmail(email)
        .orElseThrow(() -> new ResourceNotFoundException(errorMessage()));
    return extended ? memberDTOconverter.convertToExtendedMemberDto(foundMember)
        : memberDTOconverter.convertToExtendedMemberDto(memberDTOconverter.convertToMemberDto(foundMember));
  }

  @Override
  public Collection<ExtendedMemberDTO> findMembersByApproved(boolean isApproved, boolean extended) {
    Collection<Member> foundMember = memberRepository.findMembersByApproved(isApproved);
    if (!foundMember.isEmpty()) {
      return extended
          ? foundMember.stream().map(member -> memberDTOconverter.convertToExtendedMemberDto(member))
              .collect(Collectors.toList())
          : foundMember.stream().map(
              member -> memberDTOconverter.convertToExtendedMemberDto(memberDTOconverter.convertToMemberDto(member)))
              .collect(Collectors.toList());
    }
    throw new ResourceNotFoundException(errorMessage());

  }

  @Override
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

  @Override
  public ExtendedMemberDTO findMemberById(Long id, boolean extended) {
    Member foundMember = memberRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(errorMessage(id)));
    return extended ? memberDTOconverter.convertToExtendedMemberDto(foundMember)
        : memberDTOconverter.convertToExtendedMemberDto(memberDTOconverter.convertToMemberDto(foundMember));
  }

  @Override
  public void deleteMemberById(Long id) {
    memberRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(errorMessage(id)));
    memberRepository.deleteMemberById(id);
  }

  @Override
  public MemberDTO addMember(MemberDTO memberDTO) {
    Member newMember = memberRepository.save(memberDTOconverter.convertToEntity(memberDTO));
    return memberDTOconverter.convertToMemberDto(newMember);
  }

}
