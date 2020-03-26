package dz.djezzy.site.acceptance.business.data.entities;

import dz.djezzy.site.acceptance.tools.BooleanToCharConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "CATEGORY", schema = "DEPDATA")
public class Categories implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORY_SEQ")
    @SequenceGenerator(sequenceName = "CATEGORY_SEQUENCE_ID", allocationSize = 1, name = "CATEGORY_SEQ")
    private Integer id;

    @NotNull
    @Column(name = "LABEL", unique = true)
    private String label;

    @Column(name = "POSITION")
    private Integer position;

    @Column(name = "STATUS")
    private char status;

    @Convert(converter = BooleanToCharConverter.class)
    @Column(name = "first")
    private Boolean first;

    @Convert(converter = BooleanToCharConverter.class)
    @Column(name = "last")
    private Boolean last;

    @ManyToOne
    @JoinColumn(name = "NEXT_CATEGORY_ID")
    private Categories next;

    @ManyToOne
    @JoinColumn(name = "PREVIOUS_CATEGORY_ID")
    private Categories previous;

    @OneToMany(mappedBy = "categories")
    private List<SubCategories> subCategoriesList;

    @OneToMany(mappedBy = "categories")
    private List<AuditSiteLine> auditSiteLines;

    public Categories() {
    }

    public Categories(String label, int position, char status) {
        this.label = label;
        this.position = position;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categories that = (Categories) o;
        return id.equals(that.id) &&
                label.equals(that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label);
    }

}
