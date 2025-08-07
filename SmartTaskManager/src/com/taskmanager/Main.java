package com.taskmanager;

import java.time.LocalDate;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	private final TaskManager taskManager = new TaskManager();

	@SuppressWarnings("exports")
	@Override
	public void start(Stage primaryStage) {
		ListView<Task> listView = new ListView<>(taskManager.getTasks());
		listView.setCellFactory(param -> new TaskCell(taskManager));

		TextField titleField = new TextField();
		titleField.setPromptText("Title");

		TextField descField = new TextField();
		descField.setPromptText("Description");

		DatePicker datePicker = new DatePicker();

		Button addButton = new Button("Add Task");
		addButton.setOnAction(e -> {
			String title = titleField.getText().trim();
			String desc = descField.getText().trim();
			LocalDate date = datePicker.getValue();

			if (title.isEmpty() || date == null)
				return;

			Task task = new Task(title, desc, date, false);
			taskManager.addTask(task);

			titleField.clear();
			descField.clear();
			datePicker.setValue(null);
		});

		VBox inputBox = new VBox(10, titleField, descField, datePicker, addButton);
		inputBox.setPadding(new Insets(10));

		BorderPane root = new BorderPane();
		root.setCenter(listView);
		root.setBottom(inputBox);

		Scene scene = new Scene(root, 600, 400);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Smart Task Manager");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
