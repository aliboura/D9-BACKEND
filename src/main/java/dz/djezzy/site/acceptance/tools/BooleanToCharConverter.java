package dz.djezzy.site.acceptance.tools;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class BooleanToCharConverter implements AttributeConverter<Boolean, Character> {

    @Override
    public Character convertToDatabaseColumn(Boolean value) {
        return (value != null && value) ? 'Y' : 'N';
    }

    @Override
    public Boolean convertToEntityAttribute(Character value) {
        return value == null ? false : (value == 'Y' ? true : false);
    }
}
