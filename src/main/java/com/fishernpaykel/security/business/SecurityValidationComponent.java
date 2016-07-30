/**
 * 
 */
package com.fishernpaykel.security.business;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author ashin.wilson
 *
 */
@Component
public class SecurityValidationComponent {
	
	private static final Logger log = LoggerFactory.getLogger(SecurityValidationComponent.class);
	
	public boolean validateSecurity(final Map<String, String> resultMap, final String responseSignature)
	{
		String signature = null;
		try
		{			
			signature = signData((HashMap) resultMap);
			log.info("signature : " + signature);
		}
		catch (final InvalidKeyException | NoSuchAlgorithmException | UnsupportedEncodingException e)
		{
			log.error(e.getMessage());
		}
		
		if (signature == null || !signature.equalsIgnoreCase(resultMap.get("responseSignature")))
		{
			return false;
		}
		return true;
	}

	private final String signData(final HashMap params) throws InvalidKeyException, NoSuchAlgorithmException,
			UnsupportedEncodingException
	{
		//load SECRET_KEY from system property
		return sign(buildDataToSign(params), "SECRET_KEY");
	}


	@SuppressWarnings("rawtypes")
	private String buildDataToSign(final HashMap params){

	    StringBuilder dataToSign = new StringBuilder();
		Iterator it = params.entrySet().iterator();
		while (it.hasNext()) 
		{
	        Map.Entry pair = (Map.Entry)it.next();
	        log.info(pair.getKey() + " = " + pair.getValue());
	        if(!pair.getKey().equals("responseSignature"))
	        {
	        	dataToSign.append(",").append(pair.getValue());
	        }
	    }
		log.info("dataToSign: " + dataToSign.substring(1));
		return dataToSign.substring(1);
	}
	
	private static final String sign(final String data, final String secretKey) throws InvalidKeyException, NoSuchAlgorithmException,
			UnsupportedEncodingException
	{
		final String HMAC_SHA256 = "HmacSHA256";
		final SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), HMAC_SHA256);
		final Mac mac = Mac.getInstance(HMAC_SHA256);
		mac.init(secretKeySpec);
		final byte[] rawHmac = mac.doFinal(data.getBytes("UTF-8"));
		return DatatypeConverter.printBase64Binary(rawHmac).replace("\n", "");
	}
}
