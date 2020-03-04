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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CATEGORIE", schema = "DEPDATA")
public class Categories implements Serializable {

    @Id
    @Column(name = "CAT_ID")
    private Long id;

    @NotNull
    @Column(name = "CAL_LIB", unique = true)
    private String label;

    @Column(name = "POSITION")
    private int position;

    @Column(name = "STATUS")
    private char status;

    @OneToMany(mappedBy = "categories")
    private List<SubCategories> subCategoriesList = new ArrayList<>();

}
