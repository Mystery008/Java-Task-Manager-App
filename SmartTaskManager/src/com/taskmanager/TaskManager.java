package com.taskmanager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TaskManager {
	private final ObservableList<Task> tasks = FXCollections.observableArrayList();
	private static final String FILE_NAME = "tasks.txt";

	public TaskManager() {
		loadTasks();
	}

	public ObservableList<Task> getTasks() {
		return tasks;
	}

	public void addTask(Task task) {
		tasks.add(task);
		saveTasks();
	}

	public void removeTask(Task task) {
		tasks.remove(task);
		saveTasks();
	}

	public void updateTask(Task task) {
		saveTasks();
	}

	private void loadTasks() {
		Path path = Path.of(FILE_NAME);
		if (!Files.exists(path))
			return;

		try (BufferedReader reader = Files.newBufferedReader(path)) {
			String line;
			while ((line = reader.readLine()) != null) {
				Task task = Task.fromFileString(line);
				if (task != null) {
					tasks.add(task);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void saveTasks() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
			for (Task task : tasks) {
				writer.write(task.toFileString());
				writer.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
