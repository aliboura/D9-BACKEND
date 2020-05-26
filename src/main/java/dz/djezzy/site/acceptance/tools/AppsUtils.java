package dz.djezzy.site.acceptance.tools;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import dz.djezzy.site.acceptance.business.data.dto.RoleDto;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
}
