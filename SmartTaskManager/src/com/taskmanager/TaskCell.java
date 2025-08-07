package com.taskmanager;

import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class TaskCell extends ListCell<Task> {
	private final TaskManager taskManager;

	public TaskCell(TaskManager taskManager) {
		this.taskManager = taskManager;
	}

	@Override
	protected void updateItem(Task task, boolean empty) {
		super.updateItem(task, empty);

		if (empty || task == null) {
			setGraphic(null);
			setText(null);
		} else {
			Text text = new Text(task.toString());

			Button doneButton = new Button(task.isCompleted() ? "Undo" : "Done");
			doneButton.setOnAction(e -> {
				task.toggleCompleted();
				taskManager.updateTask(task);
				updateItem(task, false); // Refresh the cell
			});

			Button deleteButton = new Button("Delete");
			deleteButton.setOnAction(e -> taskManager.removeTask(task));

			HBox box = new HBox(10, text, doneButton, deleteButton);
			setGraphic(box);
		}
	}
}
