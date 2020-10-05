package dz.djezzy.site.acceptance.web;

import dz.djezzy.site.acceptance.business.data.dto.FileResponseDto;
import dz.djezzy.site.acceptance.business.data.dto.ResponseMessage;
import dz.djezzy.site.acceptance.business.data.dto.SubCategoriesDto;
import dz.djezzy.site.acceptance.business.services.SubCategoriesService;
import dz.djezzy.site.acceptance.exception.ApplicationException;
import dz.djezzy.site.acceptance.tools.ApiConstant;
import dz.djezzy.site.acceptance.tools.CSVUtils;
import dz.djezzy.site.acceptance.tools.ExcelUtils;
import dz.djezzy.site.acceptance.tools.FileTypes;
import lombok.AllArgsConstructor;
import org.apache.poi.util.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping(ApiConstant.FILES_UPLOAD_API)
public class FilesUploadController implements Serializable {

    private final SubCategoriesService subCategoriesService;

    @PostMapping
    public ResponseEntity<ResponseMessage> uploadFile(@RequestBody MultipartFile file) {
        String fileNameSrc = ".\\uploads\\";
        String message = "";
        if (file.getContentType().equals(FileTypes.APPLICATION_EXCEL)
                || file.getContentType().equals(FileTypes.APPLICATION_SHEET)) {
            fileNameSrc += "forms-d9.XLSX";
        } else if (file.getContentType().equals(FileTypes.TEXT_CSV)) {
            fileNameSrc += "forms-d9.CSV";
        } else {
            throw new ApplicationException("Not supported format");
        }

        try {
            Files.copy(file.getInputStream(),
                    Paths.get(fileNameSrc),
                    StandardCopyOption.REPLACE_EXISTING);
            CSVUtils.convertExcelToCSV(fileNameSrc);
            message = "Le fichier : " + file.getOriginalFilename() + " a téléchargé  avec succès: ";
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (IOException e) {
            message = "Impossible de télécharger le fichier : " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping(params = {"filename"})
    public Resource downloadFile(@RequestParam("filename") String filename) {
        return getResources(filename);
    }

    @GetMapping("/templates")
    public FileResponseDto downloadTemplates() throws IOException {
        List<SubCategoriesDto> list = subCategoriesService.findAll(Sort.by(Sort.Direction.ASC, "categories.id")).stream().filter(sub -> sub.isStatus()).collect(Collectors.toList());
        ExcelUtils.getUpdatedTemplate(list);
        return new FileResponseDto(IOUtils.toByteArray(getResources("template-d9.xlsx").getInputStream()));
    }

    private Resource getResources(String filename) {
        Path pathFile = Paths.get(".\\uploads\\" + filename);
        UrlResource resource = null;
        try {
            resource = new UrlResource(pathFile.toUri());
        } catch (MalformedURLException e) {
            throw new ApplicationException(e.getMessage());
        }
        return resource;
    }
}
