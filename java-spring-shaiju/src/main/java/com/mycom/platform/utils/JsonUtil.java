package com.mycom.platform.utils;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonUtil {
		
	@Autowired
	private ObjectMapper mapper;
		
	public  String convertToJson(Object o) throws JsonProcessingException{			
		return mapper.writeValueAsString(o);		
	}
	public  <T> Object convertToObject(String content,Class<T> valueType) throws IOException{			
		Object obj=mapper.readValue(content, valueType);
		return obj;
	}
	
	public List<Object> convertToListOfObjects(String jsonInput,Class<?> type) throws JsonParseException, JsonMappingException, IOException{
		List<Object> myObjects = mapper.readValue(jsonInput, mapper.getTypeFactory().constructCollectionType(List.class, type));
		return myObjects;
	}
}
