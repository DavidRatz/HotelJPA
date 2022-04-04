package bstorm.akimts.correction_jpa.metier.service;

import java.util.List;

import org.springframework.stereotype.Service;

import bstorm.akimts.correction_jpa.models.dtos.GerantDTO;
import bstorm.akimts.correction_jpa.models.forms.GerantForm;

// @Service
public class GerantServiceMock implements GerantService {

    @Override
    public GerantDTO insert(GerantForm form) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GerantDTO getOne(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<GerantDTO> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GerantDTO update(Long id, GerantForm form) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GerantDTO delete(Long id) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
