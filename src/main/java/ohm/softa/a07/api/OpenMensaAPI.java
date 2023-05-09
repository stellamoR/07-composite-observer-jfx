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
	@GET("/canteens/{canteenId}/days/{date}/meals")
	Call<List<Meal>> mealOnDay(@Path("canteenId") String canteenId, @Path("date") String date);

}
