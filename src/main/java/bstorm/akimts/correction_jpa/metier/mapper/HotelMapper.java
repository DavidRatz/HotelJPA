package bstorm.akimts.correction_jpa.metier.mapper;

import bstorm.akimts.correction_jpa.models.dtos.HotelDTO;
import bstorm.akimts.correction_jpa.models.entities.Chambre;
import bstorm.akimts.correction_jpa.models.entities.Gerant;
import bstorm.akimts.correction_jpa.models.entities.Hotel;
import bstorm.akimts.correction_jpa.models.forms.HotelForm;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class HotelMapper {

    public HotelDTO entityToDTO(Hotel entity){

        if( entity == null )
            return null;

        Gerant gerantEntity = entity.getGerant();
        HotelDTO.GerantDTO gerant = gerantEntity == null ? null :
                new HotelDTO.GerantDTO( gerantEntity.getId(), gerantEntity.getNom(), gerantEntity.getPrenom());

        List<Chambre> chambresEntity = entity.getChambres();
        List<HotelDTO.ChambreDTO> chambres = new ArrayList<HotelDTO.ChambreDTO>();

        for (Chambre chambre : chambresEntity) {
            chambres.add(new HotelDTO.ChambreDTO(chambre.getNumChambre(),chambre.getPrix()));
        }
    
        return HotelDTO.builder()
                .id( entity.getId() )
                .nom( entity.getNom() )
                .nbrEtoile(entity.getNbrEtoile())
                .adresse(entity.getAdresse())
                .gerant(gerant)
                .chambres(chambres)
                .build();

    }

    public Hotel formToEntity(HotelForm form) {

        if( form == null )
            return null;

        return  Hotel.builder()
                .nbrEtoile(form.getNbrEtoile())
                .nom( form.getNom() )
                .adresse(form.getAdresse())
                .chambres( form.getChambres() )
                .gerant(form.getGerant())
                .build();
    }
}

