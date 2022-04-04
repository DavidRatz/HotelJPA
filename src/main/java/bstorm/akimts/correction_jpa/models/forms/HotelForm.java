package bstorm.akimts.correction_jpa.models.forms;

import java.util.ArrayList;
import java.util.List;

import bstorm.akimts.correction_jpa.models.entities.Chambre;
import bstorm.akimts.correction_jpa.models.entities.Gerant;
import lombok.Data;

@Data
public class HotelForm {
    private byte nbrEtoile;
    private String nom;
    private String adresse;
    private List<Chambre> chambres;
    private Gerant gerant;
}
