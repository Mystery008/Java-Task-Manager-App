package com.taskmanager;

import java.time.LocalDate;

public class Task {
    private String title;
    private String description;
	private LocalDate dueDate;
	private boolean completed;

	public Task(String title, String description, LocalDate dueDate, boolean completed) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
		this.completed = completed;
    }

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void toggleCompleted() {
		this.completed = !this.completed;
	}

	@Override
    public String toString() {
		return title + " | " + dueDate + (completed ? " [Done]" : " [Pending]");
	}

	public String toFileString() {
        return title + ";" + description + ";" + dueDate + ";" + completed;
    }

	public static Task fromFileString(String line) {
		String[] parts = line.split(";", -1);
		if (parts.length < 4)
			return null;
		String title = parts[0];
		String description = parts[1];
		LocalDate date = LocalDate.parse(parts[2]);
		boolean completed = Boolean.parseBoolean(parts[3]);
		return new Task(title, description, date, completed);
	}
}
