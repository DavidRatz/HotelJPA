package bstorm.akimts.correction_jpa.metier.service;

import bstorm.akimts.correction_jpa.data.repo.HotelRepository;
import bstorm.akimts.correction_jpa.exception.ElementNotFoundException;
import bstorm.akimts.correction_jpa.metier.mapper.HotelMapper;
import bstorm.akimts.correction_jpa.models.dtos.HotelDTO;
import bstorm.akimts.correction_jpa.models.entities.Hotel;
import bstorm.akimts.correction_jpa.models.forms.HotelForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService{

    @Autowired
    private HotelRepository repository;
    @Autowired
    private HotelMapper mapper;

    // private final HotelRepository repository;
    // private final HotelMapper mapper;

    // public HotelServiceImpl(HotelRepository repository, HotelMapper mapper) {
    //     this.repository = repository;
    //     this.mapper = mapper;
    // }

    @Override
    public HotelDTO insert(HotelForm form) {
        Hotel entity = mapper.formToEntity(form);
        entity = repository.save(entity);
        return mapper.entityToDTO(entity);
    }

    @Override
    public HotelDTO getOne(Long id) {
        return repository.findById(id)
                    .map(mapper::entityToDTO)
                    .orElseThrow(() -> new ElementNotFoundException(id, Hotel.class));
    }

    @Override
    public List<HotelDTO> getAll() {
        return repository.findAll()
            .stream()
            .map(mapper::entityToDTO)
            .toList();
    }

    @Override
    public HotelDTO update(Long id, HotelForm form) {
        Hotel entity = repository.findById(id)
                            .orElseThrow(() -> new ElementNotFoundException(id, HotelDTO.class));
        entity.setNbrEtoile(form.getNbrEtoile());   
        entity.setNom(form.getNom());
        entity.setAdresse(form.getAdresse());
        entity.setGerant(form.getGerant());
        entity.setChambres(form.getChambres());

        entity = repository.save(entity);

        return mapper.entityToDTO(entity);
    }

    @Override
    public HotelDTO delete(Long id) {
        HotelDTO dto = getOne(id);
        repository.deleteById(id);
        return dto;
    }
}
