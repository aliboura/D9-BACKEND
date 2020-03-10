package dz.djezzy.site.acceptance.business.data.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    private int position;

    @Column(name = "STATUS")
    private char status;

    @OneToMany(mappedBy = "categories")
    private List<SubCategories> subCategoriesList = new ArrayList<>();

    public Categories() {
    }

    public Categories(String label, int position, char status) {
        this.label = label;
        this.position = position;
        this.status = status;
    }
}
