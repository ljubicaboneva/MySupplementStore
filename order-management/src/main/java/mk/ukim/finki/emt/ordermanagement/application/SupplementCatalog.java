package mk.ukim.finki.emt.ordermanagement.application;


import mk.ukim.finki.emt.ordermanagement.domain.model.Supplement;
import mk.ukim.finki.emt.ordermanagement.domain.model.SupplementId;

import java.util.List;

public interface SupplementCatalog {

    List<Supplement> findAll();

    Supplement findById(SupplementId id);
}