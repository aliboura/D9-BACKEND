package dz.djezzy.site.acceptance.business.data.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
    @Column(name = "DECISION_ID")
    private int id;

    @NotNull
    @Column(name = "LABEL", unique = true)
    private String label;

    @Column(name = "POSITION")
    private int position;

    @Column(name = "STATUS")
    private char status;

    @Column(name = "CLOSED")
    private char closed;

}
