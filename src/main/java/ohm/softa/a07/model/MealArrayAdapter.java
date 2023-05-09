package ohm.softa.a07.model;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;


public class MealArrayAdapter extends TypeAdapter<Meal[]> {


	@Override
	public void write(JsonWriter out, Meal[] value) throws IOException {

	}

	@Override
	public Meal[] read(JsonReader in) throws IOException {
		return new Meal[0];
	}
}
