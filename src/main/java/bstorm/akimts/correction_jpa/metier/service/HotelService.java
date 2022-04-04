package bstorm.akimts.correction_jpa.metier.service;

import java.util.List;

import bstorm.akimts.correction_jpa.models.dtos.HotelDTO;
import bstorm.akimts.correction_jpa.models.forms.HotelForm;

public interface HotelService {
    // CREATE
    public HotelDTO insert(HotelForm form);

    // READ
    public HotelDTO getOne(Long id);
    public List<HotelDTO> getAll();

    // UPDATE
    public HotelDTO update( Long id, HotelForm form );

    // DELETE
    public HotelDTO delete ( Long id );
}
