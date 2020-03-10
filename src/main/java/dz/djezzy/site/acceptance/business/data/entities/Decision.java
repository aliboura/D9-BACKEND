package dz.djezzy.site.acceptance.business.data.entities;

import dz.djezzy.site.acceptance.tools.BooleanToCharConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DECISION", schema = "DEPDATA")
public class Decision implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DECISION_SEQ")
    @SequenceGenerator(sequenceName = "DECISION_SEQUENCE_ID", allocationSize = 1, name = "DECISION_SEQ")
    private int id;

    @NotNull
    @Column(name = "LABEL", unique = true)
    private String label;

    @Column(name = "POSITION")
    private int position;

    @Convert(converter = BooleanToCharConverter.class)
    @Column(name = "STATUS")
    private boolean status;

    @Convert(converter = BooleanToCharConverter.class)
    @Column(name = "CLOSED")
    private boolean closed;

}
