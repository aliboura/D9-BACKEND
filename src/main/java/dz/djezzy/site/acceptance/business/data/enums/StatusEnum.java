package dz.djezzy.site.acceptance.business.data.enums;

public enum StatusEnum {

    In_Progress("En Cours"),
    Conform("Conforme"),
    No_Conform("Non Conforme"),
    Accepted("Accepter avec réserve"),
    Validate_Site("Valider ingénieur site"),
    Validate_OM("Valider ingénieur OM");

    private String name = "";

    StatusEnum(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
