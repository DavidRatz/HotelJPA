package bstorm.akimts.correction_jpa.models.dtos;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpMethod;

import lombok.*;

@Getter
@AllArgsConstructor
@Builder
public class ErrorDTO {
    private String message;
    private int status;
    private String uri;
    private HttpMethod method;
    public final Map<String,Object> infos = new HashMap<>();
    
    public ErrorDTO(String message, int status, String uri, Map<String, Object> infos) {
        this.message = message;
        this.status = status;
        this.uri = uri;
        this.infos.putAll(infos);
    }

    public ErrorDTO(String message, String uri) {
        this.message = message;
        this.status = 400;
        this.uri = uri;
    }

    public ErrorDTO(String message, int status, String uri) {
        this.message = message;
        this.status = status;
        this.uri = uri;
    }
    

    


    
    
}
