package bstorm.akimts.correction_jpa.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import bstorm.akimts.correction_jpa.exception.ElementNotFoundException;
import bstorm.akimts.correction_jpa.metier.service.GerantService;
import bstorm.akimts.correction_jpa.models.dtos.ErrorDTO;
import bstorm.akimts.correction_jpa.models.dtos.GerantDTO;
import bstorm.akimts.correction_jpa.models.forms.GerantForm;

@RestController
@RequestMapping("/gerant")
public class GerantController {
    @Autowired
    private GerantService service;

    @GetMapping()
    public List<GerantDTO> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    //@ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getOne(@PathVariable(name = "id") Long ident){
        try{
            //GerantDTO dto = service.getOne(ident);
            //return ResponseEntity.status(HttpStatus.OK).header("form-controller", "GerantController").body(service.getOne(ident));
            return ResponseEntity.ok(service.getOne(ident));
        }
        catch(ElementNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .header("header-key", "value1","value2")
                    .body(new ErrorDTO(ex.getMessage(),404,"/gerant/"+ident));
        }
    }

    @PostMapping({"", "/add"})
    public ResponseEntity<?> insert(@RequestBody GerantForm form){
        try{
            return ResponseEntity.ok(service.insert(form));
        }
        catch(IllegalArgumentException ex){
            return ResponseEntity.badRequest().body(new ErrorDTO(ex.getMessage(), "/gerant/add"));
        }
    }

    @PutMapping({"", "/update/{id}"})
    public ResponseEntity<?> update(@PathVariable(name = "id") Long ident,  @RequestBody GerantForm form){
        try{
            return ResponseEntity.ok(service.update(ident,form));
        }
        catch(IllegalArgumentException ex){
            return ResponseEntity.badRequest().body(new ErrorDTO(ex.getMessage(), 404, "/gerant/update/" + ident));
        }
    }

    @DeleteMapping({"", "/delete/{id}"})
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long ident){
        try{
            return ResponseEntity.ok(service.delete(ident));
        }
        catch(IllegalArgumentException ex){
            return ResponseEntity.badRequest().body(new ErrorDTO(ex.getMessage(), "/gerant/delete/" + ident));
        }
    }

    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleElementNotFound(ElementNotFoundException ex, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(
                ErrorDTO.builder()
                    .message(ex.getMessage())
                    .method(HttpMethod.resolve(request.getMethod()))
                    .status(404)
                    .uri(request.getRequestURI())
                    .build()
            );
    }
}
