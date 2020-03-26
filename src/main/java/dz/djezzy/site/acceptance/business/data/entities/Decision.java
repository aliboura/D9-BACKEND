package dz.djezzy.site.acceptance.business.data.entities;

import dz.djezzy.site.acceptance.tools.BooleanToCharConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "DECISION", schema = "DEPDATA")
public class Decision implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DECISION_SEQ")
    @SequenceGenerator(sequenceName = "DECISION_SEQUENCE_ID", allocationSize = 1, name = "DECISION_SEQ")
    private Integer id;

    @NotNull
    @Column(name = "LABEL")
    private String label;

    @Column(name = "POSITION")
    private Integer position;

    @Convert(converter = BooleanToCharConverter.class)
    @Column(name = "STATUS")
    private Boolean status;

    @Convert(converter = BooleanToCharConverter.class)
    @Column(name = "CLOSED")
    private Boolean closed;

}
