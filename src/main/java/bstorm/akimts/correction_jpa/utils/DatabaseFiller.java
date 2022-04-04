package bstorm.akimts.correction_jpa.utils;

import bstorm.akimts.correction_jpa.models.entities.Chambre;
import bstorm.akimts.correction_jpa.models.entities.Gerant;
import bstorm.akimts.correction_jpa.models.entities.Hotel;
import bstorm.akimts.correction_jpa.data.repo.GerantRepository;
import bstorm.akimts.correction_jpa.data.repo.HotelRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DatabaseFiller implements InitializingBean {

    private final HotelRepository repository;
    @Autowired
    private GerantRepository gRepo;

    public DatabaseFiller(HotelRepository repository) {
        this.repository = repository;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        Gerant g = Gerant.builder()
                    .debutCarriere(LocalDate.now())
                    .prenom("Luc")
                    .nom("Dubois")
                    .build();
        
        gRepo.save(g);

        g = Gerant.builder()
                    .debutCarriere(LocalDate.now().minusDays(2))
                    .prenom("Marie")
                    .nom("Desmet")
                    .build();
        gRepo.save(g);
    }
}
