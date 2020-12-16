package dz.djezzy.site.acceptance.tools;

import dz.djezzy.site.acceptance.business.data.entities.Site;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SiteUtils {

    public static String getUserV1(Site source) {
        if (!source.getVisitPlanningList().isEmpty()) {
            return source.getVisitPlanningList().get(0).getEngineerSiteV1();
        }
        return "-";
    }

    public static String getUserOMV1(Site source) {
        if (!source.getVisitPlanningList().isEmpty()) {
            return source.getVisitPlanningList().get(0).getEngineerOMV1();
        }
        return "-";
    }


    public static String getUserV2(Site source) {
        if (!source.getVisitPlanningList().isEmpty()) {
            return source.getVisitPlanningList().get(0).getEngineerSiteV2();
        }
        return "-";
    }

    public static String getUserOMV2(Site source) {
        if (!source.getVisitPlanningList().isEmpty()) {
            return source.getVisitPlanningList().get(0).getEngineerOMV2();
        }
        return "-";
    }

    public static Date getUserDateV1(Site source) {
        if (!source.getVisitPlanningList().isEmpty()) {
            return source.getVisitPlanningList().get(0).getEngineerSiteDateV1();
        }
        return null;
    }

    public static String getUserDateV1String(Site source) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        if (!source.getVisitPlanningList().isEmpty()) {
            Date date = source.getVisitPlanningList().get(0).getEngineerSiteDateV1();
            return date != null ? dateFormat.format(date) : null;
        }
        return null;
    }

    public static Date getUserDateV2(Site source) {
        if (!source.getVisitPlanningList().isEmpty()) {
            return source.getVisitPlanningList().get(0).getEngineerSiteDateV2();
        }
        return null;
    }

    public static String getUserDateV2String(Site source) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        if (!source.getVisitPlanningList().isEmpty()) {
            Date date = source.getVisitPlanningList().get(0).getEngineerSiteDateV2();
            return date != null ? dateFormat.format(date) : null;
        }
        return null;
    }

    public static Date getUserOMDateV1(Site source) {
        if (!source.getVisitPlanningList().isEmpty()) {
            return source.getVisitPlanningList().get(0).getEngineerOMDateV1();
        }
        return null;
    }

    public static Date getUserOMDateV2(Site source) {
        if (!source.getVisitPlanningList().isEmpty()) {
            return source.getVisitPlanningList().get(0).getEngineerOMDateV1();
        }
        return null;
    }

    public static String getUserOMDateV1String(Site source) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        if (!source.getVisitPlanningList().isEmpty()) {
            Date date = source.getVisitPlanningList().get(0).getEngineerOMDateV1();
            return date != null ? dateFormat.format(date) : null;
        }
        return null;
    }

    public static String getUserOMDateV2String(Site source) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        if (!source.getVisitPlanningList().isEmpty()) {
            Date date = source.getVisitPlanningList().get(0).getEngineerOMDateV1();
            return date != null ? dateFormat.format(date) : null;
        }
        return null;
    }
}
