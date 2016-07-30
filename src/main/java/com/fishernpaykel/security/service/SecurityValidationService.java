/**
 * 
 */
package com.fishernpaykel.security.service;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fishernpaykel.security.business.SecurityValidationComponent;

/**
 * @author ashin.wilson
 *
 */
@RestController
public class SecurityValidationService {
	
	private static final Logger log = LoggerFactory.getLogger(SecurityValidationService.class);

	@Autowired
	private SecurityValidationComponent securityValidationComponent;
	
	@RequestMapping(value="/security/validateUser", 
			method=RequestMethod.POST, 
			consumes=MediaType.APPLICATION_JSON_VALUE, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> validateUser(@RequestBody Map<String,String> userDetails)
	{	
		log.info("In SecurityValidationService.validateUser()...!!!");
		log.info("userDetails.size(): " + userDetails.size());			
		
		if(securityValidationComponent.validateSecurity(userDetails, null)){
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		
		return new ResponseEntity<Boolean>(true, HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping(value="/security/testGetMethod/{id}", 
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> testGetMethod(@PathVariable("id") String id)
	{
		log.info("In SecurityValidationService.testGetMethod()...!!!");
		return new ResponseEntity<String>("Response = " + id, HttpStatus.OK);
	}
}
