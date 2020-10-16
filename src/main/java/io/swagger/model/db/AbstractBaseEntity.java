package io.swagger.model.db;


import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.api.utils.JSONAPIUtils;
import io.swagger.model.db.interfaces.IPrimaryKey;
import lombok.EqualsAndHashCode;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

@MappedSuperclass
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class AbstractBaseEntity implements Serializable, IPrimaryKey {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull(message = "PK cannot be empty")
    @Size(min = 1, max = 38)
    @EqualsAndHashCode.Include
    @Column(name = "pk")
    @JsonView(JSONAPIUtils.View.class)
    protected String pk;

    public AbstractBaseEntity() {
        this.pk = UUID.randomUUID().toString();
    }

    public AbstractBaseEntity(@Size(min = 1, max = 38) String id) {

        if(id == null || id.isEmpty())
            this.pk = UUID.randomUUID().toString();
        else
            this.pk = id;

    }

    public AbstractBaseEntity(AbstractBaseEntity entity, boolean newPk) {
        if (newPk) {
            this.pk = UUID.randomUUID().toString();
        } else {
            this.pk = entity.getPk();
        }
    }


    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    @Override
    public String toString() {
        return getPk();
    }
}