package ohm.softa.a07.controllers;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import ohm.softa.a07.model.Meal;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

import static ohm.softa.a07.App.*;

public class MainController implements Initializable {


	// use annotation to tie to component in XML
	public Meal[] currentMeals;
	public String today;

	@FXML
	private CheckBox chkVegetarian;
	@FXML
	private Button btnRefresh;
	@FXML
	private Button btnClose;
	@FXML
	private ListView<String> mealsList;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		today = sdf.format(new Date());


	/*
		chkVegetarian.selectedProperty().addListener(new ChangeListener<Boolean>(){
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue){
				chkVegetarian.setSelected(!newValue);
			}
		});
*/
		chkVegetarian.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(chkVegetarian.isSelected()){
					ArrayList<String> vegMeals = new ArrayList<>();
					for(Meal meal : currentMeals){
						if(meal.isVegetarian()){
							vegMeals.add(meal.toString());
						}
					}
					ObservableList<String> obsList = FXCollections.observableArrayList(vegMeals);
					mealsList.setItems(obsList);
				}
				else{
					btnRefresh.fire();
					/*
					ArrayList<String> allMeals = new ArrayList<>();
					for(Meal meal : currentMeals){
						allMeals.add(meal.toString())
					}
					ObservableList<String> obsList = FXCollections.observableArrayList(allMeals);
					mealsList.setItems(obsList);
					*/
				}
			}
		});

		btnClose.setOnAction(
			new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					Platform.exit();
				}
			}


		);

		// set the event handler (callback)
		btnRefresh.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				//ObservableList<String> lv = new ListView<>();

				try {
					currentMeals = openMensaAPI.mealOnDay(today).execute().body();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
				ArrayList<String> mylist = new ArrayList<>();
				for (Meal meal : currentMeals) {
					mylist.add(meal.toString());
				}

				// create a new (observable) list and tie it to the view
				ObservableList<String> obsList = FXCollections.observableArrayList(mylist);
				mealsList.setItems(obsList);
			}
		});
	}
}
/*
HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
		loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


		OkHttpClient client = new OkHttpClient.Builder()
			.addInterceptor(loggingInterceptor)
			.build();

		Retrofit retrofit = new Retrofit.Builder()
			.addConverterFactory(GsonConverterFactory.create())
			.baseUrl("https://openmensa.org/api/v2/")
			.client(client)
			.build();

		openMensaAPI = retrofit.create(OpenMensaAPI.class);

		ListView<Meal> lv = new ListView<Meal>();
		lv.setItems((ObservableList<Meal>) openMensaAPI.mealOnDay("2023-05-09").execute().body() );


 */
