package mk.finki.ukim.emt.supplementscatalog.domain.model;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.measurement.Quantity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@Table(name = "types")
public class Type extends AbstractEntity<TypeId> {

    @Column(name = "type_name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Supplement> supplements;

    private Type() {
    }

    public Type(TypeId typeId, String name) {
        super(typeId);
        this.name = name;
        this.supplements = new HashSet<>();
    }

    @Override
    public TypeId id() {
        return id;
    }

    public Supplement add(Supplement supplement) {
        supplements.add(supplement);
        return supplement;
    }
}