package bstorm.akimts.correction_jpa.metier.service;

import bstorm.akimts.correction_jpa.data.repo.GerantRepository;
import bstorm.akimts.correction_jpa.exception.ElementNotFoundException;
import bstorm.akimts.correction_jpa.metier.mapper.GerantMapper;
import bstorm.akimts.correction_jpa.models.dtos.GerantDTO;
import bstorm.akimts.correction_jpa.models.entities.Gerant;
import bstorm.akimts.correction_jpa.models.forms.GerantForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GerantServiceImpl implements GerantService{

    private final GerantRepository repository;
    private final GerantMapper mapper;

    public GerantServiceImpl(GerantRepository repository, GerantMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public GerantDTO insert(GerantForm form) {
        Gerant entity = mapper.formToEntity(form);
        entity = repository.save(entity);
        return mapper.entityToDTO(entity);
    }

    @Override
    public GerantDTO getOne(Long id) {
        return repository.findById(id)
                    .map(mapper::entityToDTO)
                    .orElseThrow(() -> new ElementNotFoundException(id, Gerant.class));
    }

    @Override
    public List<GerantDTO> getAll() {
        return repository.findAll()
            .stream()
            .map(mapper::entityToDTO)
            .toList();
    }

    @Override
    public GerantDTO update(Long id, GerantForm form) {
        Gerant entity = repository.findById(id)
                            .orElseThrow(() -> new ElementNotFoundException(id, GerantDTO.class));   
        entity.setNom(form.getNom());
        entity.setPrenom(form.getPrenom());
        entity.setDebutCarriere(form.getDebutCarriere());

        entity = repository.save(entity);

        return mapper.entityToDTO(entity);
    }

    @Override
    public GerantDTO delete(Long id) {
        GerantDTO dto = getOne(id);
        repository.deleteById(id);
        return dto;
    }
}
