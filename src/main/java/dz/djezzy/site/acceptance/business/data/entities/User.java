package dz.djezzy.site.acceptance.business.data.entities;

import dz.djezzy.site.acceptance.tools.BooleanToCharConverter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = "roles")
@Entity
@Table(name = "user", schema = "DEPDATA")
public class User implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
    @SequenceGenerator(sequenceName = "USER_SEQUENCE_ID", allocationSize = 1, name = "USER_SEQ")
    private Long id;

    @NotNull
    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "matricule")
    private String matricule;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Convert(converter = BooleanToCharConverter.class)
    @Column(name = "enabled")
    private boolean enabled;

    @Convert(converter = BooleanToCharConverter.class)
    @Column(name = "expired")
    private boolean expired;

    @Convert(converter = BooleanToCharConverter.class)
    @Column(name = "credentials")
    private boolean credentials;

    @Convert(converter = BooleanToCharConverter.class)
    @Column(name = "locked")
    private boolean locked;

    @Column(name = "REGION_ID")
    private String regionId;

    @Column(name = "IMG_PROFILE")
    private byte[] imgProfile;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", schema = "DEPDATA",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_wilaya", schema = "DEPDATA",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "wilaya_id", referencedColumnName = "id")})
    private Set<WilayaRegion> wilayas = new HashSet<>();
}
