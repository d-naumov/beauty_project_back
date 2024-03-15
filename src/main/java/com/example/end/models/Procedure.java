package com.example.end.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "procedures")
public class Procedure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Pattern(regexp = "[A-Z][a-z]{3,}")
    private String name;

    @Column(name = "price")
    @Max(90000)
    @Min(10)
    private double price;

    @Column(name = "is_active")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category categories;

//    @ManyToMany(mappedBy = "procedures")
//    @JsonIgnore
//    private Set<Category> categories = new HashSet<>();

//    @ManyToMany(mappedBy = "procedures")
//    @JsonIgnore
//    private Set<User> userMaster = new HashSet<>();
    @ManyToMany(mappedBy = "procedures")
    @JsonIgnore
    @ToString.Exclude
    private Set<User> userMaster ;


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Procedure procedure = (Procedure) o;
        return getId() != null && Objects.equals(getId(), procedure.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}

