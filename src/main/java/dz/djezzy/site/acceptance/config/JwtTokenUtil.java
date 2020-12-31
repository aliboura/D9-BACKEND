package dz.djezzy.site.acceptance.config;

import dz.djezzy.site.acceptance.exception.ApplicationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClock;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Log4j2
@Component
public class JwtTokenUtil implements Serializable {

    private Clock clock = DefaultClock.INSTANCE;
    private KeyStore keyStore;
    private static final String domain = "djezzy.dz";

    @Value("${jwt.token.expiration.in.seconds}")
    private Long expiration;

    @PostConstruct
    public void init() {
        try {
            keyStore = KeyStore.getInstance("JKS");
            InputStream resourceAsStream = getClass().getResourceAsStream("/djezzy_access.jks");
            keyStore.load(resourceAsStream, "access@djezzy.dz".toCharArray());
        } catch (CertificateException | NoSuchAlgorithmException | IOException | KeyStoreException e) {
            throw new ApplicationException("Exception occurred while loading keystore");
        }
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public List<Map<String, String>> getTokenAuthorities(String token) {
        return (ArrayList<Map<String, String>>) getAllClaimsFromToken(token).get("roles");
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(getPublickey())
                .parseClaimsJws(token).getBody();
    }

    public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(clock.now());
    }

    private Boolean ignoreTokenExpiration(String token) {
        return false;
    }

    public boolean validateToken(String jwt) {
        Jwts.parser().setSigningKey(getPublickey()).parseClaimsJws(jwt);
        return true;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        UserDetails user = (UserDetails) userDetails;
        final String username = getUsernameFromToken(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }

    private Date calculateExpirationDate(Date createdDate) {
        return new Date(createdDate.getTime() + expiration * 1000);
    }

    private PublicKey getPublickey() {
        try {
            return keyStore.getCertificate(domain).getPublicKey();
        } catch (KeyStoreException e) {
            throw new ApplicationException("Exception occured while " +
                    "retrieving public key from keystore");
        }
    }


}
