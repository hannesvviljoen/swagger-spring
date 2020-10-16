package io.swagger.model.interfaces;

import io.swagger.annotations.ApiModelProperty;
import org.threeten.bp.OffsetDateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface IIndividual {
    @ApiModelProperty(required = true, value = "Unique identifier of the organization")
    @NotNull
    String getId();

    @ApiModelProperty(value = "Hyperlink to access the organization")
    String getHref();

    @ApiModelProperty(value = "e.g. Baron, Graf, Earl,…")
    String getAristocraticTitle();

    @ApiModelProperty(value = "Birth date")

    OffsetDateTime getBirthDate();

    @ApiModelProperty(value = "Country where the individual was born")
    String getCountryOfBirth();

    @ApiModelProperty(value = "Date of death")

    OffsetDateTime getDeathDate();

    @ApiModelProperty(value = "Contains the non-chosen or inherited name. Also known as last name in the Western context")
    String getFamilyName();

    @ApiModelProperty(value = "Full name flatten (first, middle, and last names)")
    String getFullName();

    @ApiModelProperty(value = "Gender")
    String getGender();

    @ApiModelProperty(value = "When sub-classing, this defines the super-class")
    String getBaseType();

    @ApiModelProperty(value = "A URI to a JSON-Schema file that defines additional attributes and relationships")
    String getSchemaLocation();

    @ApiModelProperty(value = "When sub-classing, this defines the sub-class entity name")
    String getType();
}
