package dz.djezzy.site.acceptance.tools;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import dz.djezzy.site.acceptance.business.data.dto.RoleDto;
import dz.djezzy.site.acceptance.reporting.ReportDto;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class AppsUtils {

    public static Collection<GrantedAuthority> getAuthoritiesFromList(Collection<RoleDto> roles) {
        return roles.stream().map(role ->
                new SimpleGrantedAuthority("ROLE_" + role.getLabel())).collect(Collectors.toList());
    }

    public static Collection<GrantedAuthority> getAuthoritiesFromMaps(List<Map<String, String>> roles) {
        return roles.stream().map(x -> new SimpleGrantedAuthority(x.get("authority")))
                .collect(Collectors.toList());
    }

    public static BitMatrix generateMatrix(String data, int size) throws WriterException {
        BitMatrix bitMatrix = new QRCodeWriter().encode(data, BarcodeFormat.QR_CODE, size, size);
        return bitMatrix;
    }

    public static InputStream writeImage(String imageFormat, BitMatrix bitMatrix) throws FileNotFoundException, IOException {
        ByteArrayOutputStream fileOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, imageFormat, fileOutputStream);
        fileOutputStream.close();
        InputStream qrCode = new ByteArrayInputStream(fileOutputStream.toByteArray());
        return qrCode;
    }

    public static Sort.Direction getSortDirection(String sort) {
        return sort.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
    }

    public static Date getDate(String value) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.parse(value);
    }

    public static InputStream geQrCode(ReportDto report) throws WriterException, IOException {
        return AppsUtils.writeImage("png", AppsUtils.generateMatrix(report.getQrCode(), 400));
    }

    public static List<Integer> getIdList(String params) {
        String[] ids = params.split(",");
        return Arrays.stream(ids).map(x -> Integer.parseInt(x)).collect(Collectors.toList());
    }

    public static String getUserPrincipal() {
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        return (String) auth.getPrincipal();
    }

    public static String getMailContent(Boolean secondVisit, String code, String date, String siteAddress, String ingSite, String ingOM) {
        String secondTxt = secondVisit ? "deuxième" : "nouvelle";
        String mailContent = "<div " +
                "style='width:98%;height: 70px;background: #f1f1f1;border: 1px solid #e9e9e9;margin: 5px;font-size: 22px;font-weight: 500;font-family: Century Gothic'>" +
                "<img src='cid:logoImage' width='284' style='margin: 0px 15px 0px 5px;' />" +
                "</div>";
        mailContent += "<br/><div style='width:96%;padding:10px;border-bottom: 1px solid #d0d0d0;margin: 5px;height: auto;margin-top: 10px'>" +
                " <h1 style='color: #343a40;margin: 10px;font-family: Century Gothic'>Site N°: " + code + "</h1>";
        mailContent += "<h5 style='color: #343a40;margin: 10px;font-weight: 400;font-family: Century Gothic'>Bonjour,</h5>";
        mailContent += "<h5 style='color: #343a40;margin: 10px 10px 10px 15px;font-weight: 400;font-family: Century Gothic'>Vous avez une " + secondTxt + " visite programmée le " + date + " du site N°: <span style='color: #1E88E5;'>#" + code + "</span>.</h5>";
        mailContent += "<h5 style='color: #343a40;margin: 10px 10px 10px 15px;font-weight: 400;font-family: Century Gothic'><strong>Lieu du site :</strong> " + siteAddress + "</h5>";
        mailContent += "<h5 style='color: #343a40;margin: 10px 10px 10px 15px;font-weight: 400;font-family: Century Gothic'><strong>Ingénieur Site :</strong> " + ingSite + "</h5>";
        mailContent += "<h5 style='color: #343a40;margin: 10px 10px 0px 15px;font-weight: 400;font-family: Century Gothic'><strong>Ingénieur O&M :</strong> " + ingOM + "</h5><br/>";
        mailContent += "<h5 style='color: #343a40;margin: 10px;font-weight: 400;font-family: Century Gothic'>Cordialement.</h5></div>";
        mailContent += "<h5 style='color: #343a40;margin-left: 10px;font-family: Century Gothic;margin-top: -3px;font-weight: 400;font-size: 11px;'>Cet email est une notification automatique, merci de ne pas répondre à ce message. Connectez-vous sur la plateforme Site Transfert [ D9 ] pour retrouver tous les sites auxquels vous êtes affectés.</h5>";
        return mailContent;
    }

}
