package cars.rus.Utils.Converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import cars.rus.DTO.MemberDTO.MemberDTO;
import cars.rus.DTO.MemberDTO.ExtendedMemberDTO;
import cars.rus.Entities.Member;

public class MemberDTOconverter {
  private ModelMapper modelMapper;

  @Autowired
  public MemberDTOconverter() {
    this.modelMapper = new ModelMapper();
  }

  public <OBJ> MemberDTO convertToMemberDto(OBJ obj) {
    return modelMapper.map(obj, MemberDTO.class);
  }

  public <OBJ> ExtendedMemberDTO convertToExtendedMemberDto(OBJ obj) {
    return modelMapper.map(obj, ExtendedMemberDTO.class);
  }

  public <OBJ> Member convertToEntity(OBJ obj) {
    return modelMapper.map(obj, Member.class);
  }
}
