package in.akd.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class MapValidationErrorService {
	
	public ResponseEntity<?> mapValidationService(BindingResult result){
		System.out.println(">>6>>"+this.getClass());
		if(result.hasErrors()) {
			Map<String, String> map = new HashMap<String,String>();
			for(FieldError error:result.getFieldErrors()) {
				map.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<Map<String,String>>(map, HttpStatus.BAD_REQUEST);
		}
		return null;
	}

}
