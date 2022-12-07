package com.conferenceTaskManager.conferenceTaskManager;

import com.conferenceTaskManager.conferenceTaskManager.Task.Task;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootApplication
public class ConferenceTaskManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConferenceTaskManagerApplication.class, args);

		Task task1 = new Task("Writing Fast Tests Against Enterprise Rails", 60);
		Task task2 = new Task("Overdoing it in Python", 45);
		Task task3 = new Task("Lua for the Masses", 30);
		Task task4 = new Task("Ruby Errors from Mismatched Gem Versions", 45);
		Task task5 = new Task("Common Ruby Errors", 45);
		Task task6 = new Task("Rails for Python Developers lightning", 5);
		Task task7 = new Task("Communicating Over Distance", 60);
		Task task8 = new Task("Accounting-Driven Development", 45);
		Task task9 = new Task("Woah", 30);
		Task task10 = new Task("Sit Down", 30);
		Task task11 = new Task("Pair Programming", 45);
		Task task12 = new Task("Rails Magic", 60);
		Task task13 = new Task("Ruby on Rails: Why We Should Move On", 60);
		Task task14 = new Task("Clojure Ate Scala", 45);
		Task task15 = new Task("Programming in the Boondocks of Seattle", 30);
		Task task16 = new Task("Ruby vs. Clojure for Back-End Development", 30);
		Task task17 = new Task("Ruby on Rails Legacy App Maintenance", 60);
		Task task18 = new Task("A World Without HackerNews", 30);
		Task task19 = new Task("User Interface CSS in Rails Apps", 30);
		List<Task> tasks = new ArrayList<Task>(Arrays.asList(task1,task2,task3,task4,task5,task6,task7,task8,
				task9,task10,task11,task12,task13,task14,task15,task16,task17,task18,task19));


		System.out.println("###Test input: ");
		for(int i = 0; i < tasks.size(); i++) {
			System.out.println(tasks.get(i).getDescription() + " " + tasks.get(i).getDuration() + "min \n");
		}

		rescheduleTasks(tasks);



	}

	public static void rescheduleTasks(List<Task> tasks) {
		Random rand = new Random();
		try {
			List<Task> nextDayTask = new ArrayList<Task>();
			if(tasks != null && tasks.size() > 0) {
				String startTime = "09:00 am";
				SimpleDateFormat taskDateFormat = new SimpleDateFormat("HH:mm aa");
				Date taskDate = taskDateFormat.parse(startTime); //defining start time and using calendar.add operation for addition
				Calendar cal = Calendar.getInstance();
				cal.setTime(taskDate);
				int minutes = 0;
				int supposedMinutes = 0;
				System.out.println("Track 1: \n");
				while(!tasks.isEmpty()) {
					int randomIndex = rand.nextInt(tasks.size());
					Task scheduledTask = tasks.get(randomIndex);
					if(scheduledTask.getDuration() == 5) {
						supposedMinutes = minutes + 60;
					} else {
						supposedMinutes = minutes + scheduledTask.getDuration();
					}
					if(((minutes == 120 && scheduledTask.getDuration() == 60) || (minutes == 135 && scheduledTask.getDuration() == 45) || (minutes == 150 && scheduledTask.getDuration() == 30)
							|| (minutes == 120 && scheduledTask.getDuration() == 5)) && supposedMinutes == 180) { //this condition means if we are approaching lunch time, fit something in that will not breach 12pm point
						if(scheduledTask.getDuration() == 5) {  //this condition is used in output in task description as 1 hour duration task
							minutes += 60;
							System.out.println(taskDateFormat.format(cal.getTime()) + " " + scheduledTask.getDescription());
							cal.add(Calendar.MINUTE, 60);
						} else {
							minutes += scheduledTask.getDuration();
							System.out.println(taskDateFormat.format(cal.getTime()) + " " + scheduledTask.getDescription());
							cal.add(Calendar.MINUTE, scheduledTask.getDuration());
						}
						tasks.remove(tasks.get(randomIndex));
					} else if (((minutes == 420 && scheduledTask.getDuration() == 60) || (minutes == 435 && scheduledTask.getDuration() == 45) || (minutes == 450 && scheduledTask.getDuration() == 30)
							|| (minutes == 420 && scheduledTask.getDuration() == 5)) && supposedMinutes == 480) { // same as previous condition, approching networking event hour mark
						if(scheduledTask.getDuration() == 5) {
							minutes += 60;
							System.out.println(taskDateFormat.format(cal.getTime()) + " " + scheduledTask.getDescription());
							cal.add(Calendar.MINUTE, 60);
						} else {
							minutes += scheduledTask.getDuration();
							System.out.println(taskDateFormat.format(cal.getTime()) + " " + scheduledTask.getDescription());
							cal.add(Calendar.MINUTE, scheduledTask.getDuration());
						}
						tasks.remove(tasks.get(randomIndex));
					} else if (supposedMinutes > 240  && supposedMinutes != 465 && supposedMinutes < 480) { // avoiding calculation of unmanagable time to be calculated and fiting the task inside afternoon schedule
						if(scheduledTask.getDuration() == 5) {
							minutes += 60;
							System.out.println(taskDateFormat.format(cal.getTime()) + " " + scheduledTask.getDescription());
							cal.add(Calendar.MINUTE, 60);
						} else {
							minutes += scheduledTask.getDuration();
							System.out.println(taskDateFormat.format(cal.getTime()) + " " + scheduledTask.getDescription());
							cal.add(Calendar.MINUTE, scheduledTask.getDuration());
						}
						tasks.remove(tasks.get(randomIndex));
					}  else if (minutes == 180) { //lunch time
						minutes += 60;
						System.out.println(taskDateFormat.format(cal.getTime()) + " " + "Lunch");
						cal.add(Calendar.MINUTE, 60);
					} else if (minutes == 480) { // networking event, end of working day, redefining values for next work day
						System.out.println(taskDateFormat.format(cal.getTime()) + " " + "Network Event \n");
						startTime = "09:00 am";
						taskDate = taskDateFormat.parse(startTime);
						cal.setTime(taskDate);
						minutes = 0;
						supposedMinutes = 0;
						System.out.println("Track 2: \n");
						continue;
					} else if (supposedMinutes == 165){ //unmanagable minutes for calculation
						continue;
					} else if (supposedMinutes == 465){ //unmanagable minutes for calculation
						continue;
					} else if (supposedMinutes != 165 && supposedMinutes < 180) { //fit in tasks before lunch
						minutes += scheduledTask.getDuration();
						System.out.println(taskDateFormat.format(cal.getTime()) + " " + scheduledTask.getDescription());
						cal.add(Calendar.MINUTE, scheduledTask.getDuration());
						tasks.remove(tasks.get(randomIndex));
					}
					if(tasks.size() == 0) {
						System.out.println(taskDateFormat.format(cal.getTime()) + " " + "Network Event \n");
					}
				}

			}


		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
