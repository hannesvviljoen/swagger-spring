package io.swagger.model.db;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.threeten.bp.OffsetDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "individual")
@Getter
@Setter

public class IndividualEntity extends AbstractBaseEntity {

    public IndividualEntity(){
        super();
    }
    public IndividualEntity(String pk,String fullName){
        super(pk);
        this.fullName = fullName;
    }

//    private String id = null;

    @Column(name = "href")
    private String href = null;

    @Column(name = "aristocratic_title")
    private String aristocraticTitle = null;

    @Column(name = "birth_date")
    private OffsetDateTime birthDate = null;

    @Column(name = "country_of_birth")
    private String countryOfBirth = null;

    @Column(name = "death_date")
    private OffsetDateTime deathDate = null;

    @Column(name = "family_name")
    private String familyName = null;

    @NotBlank
    @NotNull(message = "Full Name cannot be empty")
    @Column(name = "full_name")
    private String fullName;


    @Transient
    private String gender = null;

    @Transient
    private String baseType = null;

    @Transient
    private String schemaLocation = null;

    @Transient
    private String type = null;

}
