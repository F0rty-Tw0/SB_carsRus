package cars.rus.Utils.Converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import cars.rus.DTO.ReservationDTO.ReservationDTO;
import cars.rus.DTO.ReservationDTO.ExtendedReservationDTO;
import cars.rus.Entities.Reservation;

public class ReservationDTOconverter {
  private ModelMapper modelMapper;

  @Autowired
  public ReservationDTOconverter() {
    this.modelMapper = new ModelMapper();
  }

  public <OBJ> ReservationDTO convertToReservationDto(OBJ obj) {
    return modelMapper.map(obj, ReservationDTO.class);
  }

  public <OBJ> ExtendedReservationDTO convertToExtendedReservationDto(OBJ obj) {
    return modelMapper.map(obj, ExtendedReservationDTO.class);
  }

  public <OBJ> Reservation convertToEntity(OBJ obj) {
    return modelMapper.map(obj, Reservation.class);
  }
}
