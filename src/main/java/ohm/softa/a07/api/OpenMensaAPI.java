package ohm.softa.a07.api;

import ohm.softa.a07.model.Meal;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

/**
 * Created by Peter Kurfer on 11/19/17.
 */

public interface OpenMensaAPI {
	// TODO add method to get meals of a day
	@GET("canteens/269/days/{date}/meals")
	Call<Meal[]> mealOnDay(@Path("date") String date);

}
