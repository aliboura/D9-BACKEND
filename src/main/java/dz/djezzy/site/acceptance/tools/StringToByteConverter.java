package dz.djezzy.site.acceptance.tools;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import java.util.Base64;

@Converter
public class StringToByteConverter implements AttributeConverter<byte[], String> {

    @Override
    public String convertToDatabaseColumn(byte[] attribute) {
        return Base64.getEncoder().encodeToString(attribute);
    }

    @Override
    public byte[] convertToEntityAttribute(String dbData) {
        if (dbData != null) {
            return Base64.getMimeDecoder().decode(dbData.split(",")[1]);
        }
        return new byte[0];
    }
}
