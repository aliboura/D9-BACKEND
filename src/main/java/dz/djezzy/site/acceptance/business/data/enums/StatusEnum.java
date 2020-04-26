package dz.djezzy.site.acceptance.business.data.enums;

public enum StatusEnum {

    None("N/A"),
    In_Progress("En Cours"),
    In_Progress_Validate("En cours de validation V1"),
    In_Progress_Validate_V2("En cours de validation V2"),
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
