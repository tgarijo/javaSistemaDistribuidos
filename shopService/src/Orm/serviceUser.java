package Orm;

import DataClass.User;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;



public class serviceUser {

	private String UserNameFile = "user.json";

	public  boolean registerNewUser(User user) {
		List<User> listUser = new ArrayList();
		
		try {

			listUser = getDataFromFile(User.class, UserNameFile);
			
			listUser.add(user);
			
			Writer writer = Files.newBufferedWriter(Paths.get(UserNameFile));
			
			Gson gson = new Gson();
			gson.toJson(listUser, writer);
			
			writer.close();
			
			return true;
			
		} catch (IOException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
				
	}
	
	private  <T> List<T> getDataFromFile(Class<T> clazz, String fileName) {
	
		final Type REVIEW_TYPE = new TypeToken<List<T>>() {
		}.getType();
		
		List<T> list = null;
		
		JsonReader reader;
		GsonBuilder builder = new GsonBuilder();
		
		Gson gson = builder.create();
		
		System.out.println(System.getProperty("user.dir"));
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(fileName));     
		    try {
				if(br.readLine()  == null ) return  new ArrayList<T>();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
			reader = new JsonReader(new FileReader(fileName));
					
			list = gson.fromJson(reader, REVIEW_TYPE);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}			
		
		
		
		
		return list;
	}
	

}
