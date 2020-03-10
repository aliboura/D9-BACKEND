package dz.djezzy.site.acceptance.tools;

public class AppConverter {

    public String asString(Boolean b) {
        return b == null ? "N" : (b ? "Y" : "N");
    }

    public Character booleanToChart(Boolean b) {
        return b == null ? 'N' : (b ? 'Y' : 'N');
    }

    public boolean chartToBoolean(Character b) {
        return b == null ? false : (b == 'Y' ? true : false);
    }

    public Character asChart(Integer b) {
        return b == null ? 'N' : (b == 1 ? 'Y' : 'N');
    }
}
