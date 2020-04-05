package dz.djezzy.site.acceptance.business.data.entities;

import dz.djezzy.site.acceptance.tools.BooleanToCharConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "SUB_CATEGORIES", schema = "DEPDATA")
public class SubCategories implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUB_CATEGORY_SEQ")
    @SequenceGenerator(sequenceName = "SUB_CATEGORIES_SEQUENCE_ID", allocationSize = 1, name = "SUB_CATEGORY_SEQ")
    private int id;

    @NotNull
    @Column(name = "LABEL", unique = true)
    private String label;

    @Column(name = "POSITION")
    private int position;

    @Column(name = "VALUE_TYPE")
    private Integer valueType;

    @Convert(converter = BooleanToCharConverter.class)
    @Column(name = "STATUS")
    private boolean status;

    @Convert(converter = BooleanToCharConverter.class)
    @Column(name = "BLOCKING")
    private boolean blocking;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Categories categories;

    @ManyToMany
    @JoinTable(
            name = "sub_categories_decision", schema = "DEPDATA",
            joinColumns = @JoinColumn(name = "SUB_CATEGORIES_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "DECISION_ID", referencedColumnName = "id"))
    private List<Decision> decisions = new ArrayList<>();

    public SubCategories(String label) {
        this.label = label;
    }
}
