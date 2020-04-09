package dz.djezzy.site.acceptance.business.data.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "DECISION_TYPE", schema = "DEPDATA")
public class DecisionType implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DECISION_TYPE_SEQ")
    @SequenceGenerator(sequenceName = "DECISION_TYPE_SEQUENCE_ID", allocationSize = 1, name = "DECISION_TYPE_SEQ")
    private Integer id;

    @NotNull
    @Column(name = "LABEL")
    private String label;

    @OneToMany(mappedBy = "decisionType", cascade = CascadeType.MERGE)
    private List<Decision> decisions = new ArrayList<>();
}
