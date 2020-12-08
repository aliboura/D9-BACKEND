package dz.djezzy.site.acceptance.business.data.entities;

import dz.djezzy.site.acceptance.business.data.audit.Auditable;
import dz.djezzy.site.acceptance.tools.StringToByteConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SITE_FORMS", schema = "DEPDATA")
public class SiteForms extends Auditable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SITE_FORMS_SEQ")
    @SequenceGenerator(sequenceName = "SITE_FORMS_ID", allocationSize = 1, name = "SITE_FORMS_SEQ")
    private Integer id;

    @NotNull
    @Column(name = "CODE_SITE")
    private String codeSite;

    @Column(name = "FILE_NAME", length = 200)
    private String fileName;

    @Column(name = "FILE_TYPE", length = 100)
    private String fileType;

    //  @Convert(converter = StringToByteConverter.class)
    @Column(name = "FORMS_FILE")
    private byte[] formsFile;

    @Column(name = "OBSERVATION", length = 255)
    private String observation;

    @ManyToOne
    @JoinColumn(name = "DECISION_ID")
    private Decision decision;

}
