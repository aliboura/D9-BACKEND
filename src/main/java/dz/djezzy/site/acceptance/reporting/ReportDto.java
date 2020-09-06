package dz.djezzy.site.acceptance.reporting;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportDto {

    private String title;
    private String description;
    private Date date;
    private String user;
    private String wilaya;
    private Integer groupBy;
    private String sort;
    private String format;
    private String qrCode;
}
