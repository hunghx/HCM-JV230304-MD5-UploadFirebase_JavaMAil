package ra.springmail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FormRequest {
    private String email;
    private String cc;
    private String subject;
    private String content;
    private List<MultipartFile> file;
}
