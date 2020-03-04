package dz.djezzy.site.acceptance.business.data.entities;

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
@Table(name = "SOUS_CAT", schema = "DEPDATA")
public class SubCategories implements Serializable {

    @Id
    @Column(name = "SOUS_CAT_ID")
    private Long id;

    @NotNull
    @Column(name = "SOUS_CAT_LIB", unique = true)
    private String label;

    @Column(name = "POSITION")
    private int position;

    @Column(name = "STATUS")
    private char status;

    @ManyToOne
    @JoinColumn(name = "CAT_ID")
    private Categories categories;

    public SubCategories(String label) {
        this.label = label;
    }
}
