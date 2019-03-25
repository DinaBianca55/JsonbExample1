package com.dev.car.jsonb_examples;

import java.io.InputStreamReader;
import java.io.Reader;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.annotation.JsonbProperty;

/**
 * Hello world!
 *
 */
class MyInterestingObject {
	// name & property match
	@JsonbProperty("name")
	protected String name;

	// name & property mismatch
	@JsonbProperty("addressPO")
	protected String address;
	
	@JsonbProperty("stuff")
	protected String[] stuff;

	public void display() {
		System.out.println("Name:    " + name);
		System.out.println("Address: " + address);
		for (String stuffEntry : stuff) {
			System.out.println("Stuff:   " + stuffEntry);
		}
	}
}
public class App 
{
	Jsonb jsonb;
	public App() {
        jsonb = JsonbBuilder.create();
	}
	public void go() throws Exception {
//		File file = new File("c:\\temp\\sample.json");
//		String contents = FileUtils.readFileToString(file);
//		StringReader sr = new StringReader(contents);
		Reader sr = new InputStreamReader(App.class.getResourceAsStream("/com/dev/car/jsonb_examples/sample.json"));
		JsonReader jsonReader = Json.createReader(sr);
		JsonObject json = jsonReader.readObject();
		JsonArray arr = json.getJsonArray("myInterestingField");
		System.out.println("SIZE: " + arr.size());
		for (int i=0;i<arr.size();i++) {
			JsonObject jsonItem = arr.getJsonObject(i);
			MyInterestingObject object = jsonb.fromJson(jsonItem.toString(), MyInterestingObject.class);
			object.display();

		}
	}
    public static void main( String[] args ) throws Exception
    {
        System.out.println( "Hello World!" );
    	new App().go();
    }
}
