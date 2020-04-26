package dz.djezzy.site.acceptance.business.data.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "role", schema = "DEPDATA")
public class Role implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLE_SEQ")
    @SequenceGenerator(sequenceName = "ROLE_SEQUENCE_ID", allocationSize = 1, name = "ROLE_SEQ")
    private Long id;

    @NotNull
    @Column(name = "label", unique = true)
    private String label;

    @JsonBackReference
    @ManyToMany(mappedBy = "roles")
    private List<User> userList;

    public Role(String label) {
        this.label = label;
    }
}
