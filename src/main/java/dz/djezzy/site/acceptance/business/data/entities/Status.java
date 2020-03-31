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
@Table(name = "STATUS", schema = "DEPDATA")
public class Status implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STATUS_SEQ")
    @SequenceGenerator(sequenceName = "STATUS_SEQUENCE_ID", allocationSize = 1, name = "STATUS_SEQ")
    private Integer id;

    @NotNull
    @Column(name = "LABEL", unique = true)
    private String label;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "STYLE_CSS")
    private String styleCSS;

    @Convert(converter = BooleanToCharConverter.class)
    @Column(name = "MOTIF")
    private boolean motif;

    @Convert(converter = BooleanToCharConverter.class)
    @Column(name = "FIRST")
    private boolean first;
}
