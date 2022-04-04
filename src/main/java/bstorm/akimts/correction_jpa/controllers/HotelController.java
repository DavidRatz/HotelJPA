package bstorm.akimts.correction_jpa.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import bstorm.akimts.correction_jpa.exception.ElementNotFoundException;
import bstorm.akimts.correction_jpa.metier.service.HotelService;
import bstorm.akimts.correction_jpa.models.dtos.ErrorDTO;
import bstorm.akimts.correction_jpa.models.dtos.HotelDTO;
import bstorm.akimts.correction_jpa.models.forms.HotelForm;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    private HotelService service;

    @GetMapping()
    public List<HotelDTO> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    //@ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getOne(@PathVariable(name = "id") Long ident){
        try{
            //HotelDTO dto = service.getOne(ident);
            //return ResponseEntity.status(HttpStatus.OK).header("form-controller", "HotelController").body(service.getOne(ident));
            return ResponseEntity.ok(service.getOne(ident));
        }
        catch(ElementNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .header("header-key", "value1","value2")
                    .body(new ErrorDTO(ex.getMessage(),404,"/hotel/"+ident));
        }
    }

    @PostMapping({"", "/add"})
    public ResponseEntity<?> insert(@RequestBody HotelForm form){
        try{
            return ResponseEntity.ok(service.insert(form));
        }
        catch(IllegalArgumentException ex){
            return ResponseEntity.badRequest().body(new ErrorDTO(ex.getMessage(), "/hotel/add"));
        }
    }

    @PutMapping({"", "/update/{id}"})
    public ResponseEntity<?> update(@PathVariable(name = "id") Long ident,  @RequestBody HotelForm form){
        try{
            return ResponseEntity.ok(service.update(ident,form));
        }
        catch(IllegalArgumentException ex){
            return ResponseEntity.badRequest().body(new ErrorDTO(ex.getMessage(), 404, "/hotel/update/" + ident));
        }
    }

    @DeleteMapping({"", "/delete/{id}"})
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long ident){
        try{
            return ResponseEntity.ok(service.delete(ident));
        }
        catch(IllegalArgumentException ex){
            return ResponseEntity.badRequest().body(new ErrorDTO(ex.getMessage(), "/Hotel/delete/" + ident));
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
