package com.fourdsight.service.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class JsonUtil {
	static ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

	static ObjectMapper mapper = new ObjectMapper();

	/**
	 * 
	 * Object to Json
	 *
	 *
	 * @param src
	 *            - The type parameter Object
	 * @return This method return type String
	 * @author mkilic
	 */
	public static String objectToJson(Object src) {
		String json = null;
		try {
			json = ow.writeValueAsString(src);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;

	}

	/**
	 * Json to Object
	 *
	 * @param json
	 *            - The type parameter String
	 * @return This method return type Object
	 * @author mkilic
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object jsonToObject(String json, Class clazz) {
		Object obj = null;
		try {
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			obj = mapper.readValue(json, clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return obj;

	}

	/**
	 * Json to Parameterized Object
	 *
	 * @param json
	 *            - The type parameter String
	 * @return This method return type Parameterized Object
	 * @author kardar
	 */
	@SuppressWarnings("rawtypes")
	public static Object jsonToParameterizedObject(String json, TypeReference tr) {
		Object obj = null;
		try {
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			obj = mapper.readValue(json, tr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return obj;

	}


	
	
}
