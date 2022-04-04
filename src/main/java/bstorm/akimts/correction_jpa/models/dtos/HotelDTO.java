package bstorm.akimts.correction_jpa.models.dtos;

import java.util.ArrayList;
import java.util.List;

import bstorm.akimts.correction_jpa.models.entities.Chambre;
import bstorm.akimts.correction_jpa.models.entities.Gerant;
import lombok.*;

@Data
@Builder
public class HotelDTO {
    private Long id;
    private byte nbrEtoile;
    private String nom;
    private String adresse;
    private List<ChambreDTO> chambres;
    private GerantDTO gerant;

    @Data
    @AllArgsConstructor
    public static class GerantDTO{
        private Long id;
        private String nom;
        private String prenom;
    }

    @Data
    @AllArgsConstructor
    public static class ChambreDTO{
        private int numChambre;
        private float prix;
    }
}
