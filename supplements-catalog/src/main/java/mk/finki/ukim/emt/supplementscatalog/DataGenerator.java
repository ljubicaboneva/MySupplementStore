package mk.finki.ukim.emt.supplementscatalog;

import mk.finki.ukim.emt.supplementscatalog.domain.model.*;
import mk.finki.ukim.emt.supplementscatalog.domain.repository.SupplementRepository;
import mk.finki.ukim.emt.supplementscatalog.domain.repository.TypeRepository;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.sharedkernel.domain.identity.Name;
import mk.ukim.finki.emt.sharedkernel.domain.measurement.Quantity;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;

@Component
class DataGenerator {

    private final SupplementRepository supplementRepository;
    private final TypeRepository typeRepository;

    DataGenerator(SupplementRepository supplementRepository, TypeRepository typeRepository) {
        this.supplementRepository = supplementRepository;
        this.typeRepository = typeRepository;
    }

    @PostConstruct
    @Transactional
    public void generateData() {
        if (typeRepository.findAll().size() == 0) {
            var types = new ArrayList<Type>();
            types.add(new Type(new TypeId("1"),"protein"));
            types.add(new Type(new TypeId("2"),"sports-nutrition"));
            types.add(new Type(new TypeId("3"),"diet"));
            types.add(new Type(new TypeId("4"),"health"));
            types.add(new Type(new TypeId("5"),"vitamins"));
            typeRepository.saveAll(types);

        }
            if (supplementRepository.findAll().size() == 0) {
                var supplements = new ArrayList<Supplement>();
                supplements.add(new Supplement(new SupplementId("1"), Name.valueOf("Meal"), Brand.ANIMAL,
                        new Money(Currency.USD, 55), 2300, new Quantity(10)));
                supplements.add(new Supplement(new SupplementId("2"), Name.valueOf("Creatine"), Brand.A1PLUS,
                        new Money(Currency.USD, 13), 318, new Quantity(15)));
                supplements.add(new Supplement(new SupplementId("3"), Name.valueOf("C4 Ripped"), Brand.CELLUCOR,
                        new Money(Currency.USD, 40), 180, new Quantity(10)));
                supplements.add(new Supplement(new SupplementId("4"), Name.valueOf("Fish Oil"), Brand.CELLUCOR,
                        new Money(Currency.USD, 11), 200, new Quantity(12)));
                supplements.add(new Supplement(new SupplementId("5"), Name.valueOf("Cholesterol Pro"), Brand.NOW,
                        new Money(Currency.USD, 28), 180, new Quantity(20)));
                supplements.add(new Supplement(new SupplementId("6"), Name.valueOf("Vitamin B-12"), Brand.SOLARAY,
                        new Money(Currency.USD, 9), 180, new Quantity(25)));
                supplementRepository.saveAll(supplements);


            add("1","1");
            add("2","2");
            add("3","2");
            add("4","3");
            add("5","4");
            add("6","5");
            }
        }


    private void add(String supplementId, String typeId) {
        Supplement supplement = supplementRepository.findById(new SupplementId(supplementId)).get();
        Type type = typeRepository.findById(new TypeId(typeId)).get();
        type.addSupplement(supplement);
        typeRepository.save(type);
    }
}