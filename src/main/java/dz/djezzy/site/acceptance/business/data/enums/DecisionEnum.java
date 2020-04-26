package dz.djezzy.site.acceptance.business.data.enums;

public enum DecisionEnum {
    Accepted("Accepté avec réserves"),
    Conform("Accepté sans réserves"),
    No_Conform("Rejeté");

    private String name = "";

    DecisionEnum(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
