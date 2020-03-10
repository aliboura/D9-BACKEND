package dz.djezzy.site.acceptance.business.data.entities;

import dz.djezzy.site.acceptance.tools.BooleanToCharConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

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

    @Convert(converter = BooleanToCharConverter.class)
    @Column(name = "STATUS")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Categories categories;

    public SubCategories(String label) {
        this.label = label;
    }
}
