package seedu.duke;

import seedu.duke.gym.ScheduleTracker;
import seedu.duke.gym.WorkoutTracker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class Storage {

    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveAllTasks(Fluid fluid, Meal meal, ScheduleTracker scheduleTracker, WorkoutTracker workoutTracker,
                             WeightTracker weightTracker) throws IOException {
        String currentDate;
        String currentMeal;
        String currentFluid;
        String header;
        String customMeal;
        String customFluid;
        String filePath = new File(this.filePath).getAbsolutePath();
        FileWriter fw = new FileWriter(filePath, false);
        int headerFlag;
        for (String date : DateTracker.dates) {
            currentDate = "Date: " + date + "\n";
            Files.write(Paths.get(filePath), currentDate.getBytes(), StandardOpenOption.APPEND);
            fw.close();
            headerFlag = 0;
            for (String m : meal.meals) {
                if (m.contains(date) && (headerFlag == 0)) {
                    header = "Meals" + "\n";
                    Files.write(Paths.get(filePath), header.getBytes(), StandardOpenOption.APPEND);
                    fw.close();
                    headerFlag = 1;
                }
                if (m.contains(date)) {
                    currentMeal = m + "\n";
                    Files.write(Paths.get(filePath), currentMeal.getBytes(), StandardOpenOption.APPEND);
                    fw.close();
                }
            }
            headerFlag = 0;
            for (String f : fluid.fluidArray) {
                if (f.contains(date) && (headerFlag == 0)) {
                    header = "Fluids" + "\n";
                    Files.write(Paths.get(filePath), header.getBytes(), StandardOpenOption.APPEND);
                    fw.close();
                    headerFlag = 1;
                }
                if (f.contains(date)) {
                    currentFluid = f + "\n";
                    Files.write(Paths.get(filePath), currentFluid.getBytes(), StandardOpenOption.APPEND);
                    fw.close();
                }
            }
        }
        headerFlag = 0;
        for (String m : FoodBank.meals) {
            if (headerFlag == 0) {
                header = "Meal Library" + "\n";
                Files.write(Paths.get(filePath), header.getBytes(), StandardOpenOption.APPEND);
                fw.close();
                headerFlag = 1;
            }
            customMeal = m + "\n";
            Files.write(Paths.get(filePath), customMeal.getBytes(), StandardOpenOption.APPEND);
            fw.close();
        }
        headerFlag = 0;
        for (String f : FoodBank.fluids) {
            if (headerFlag == 0) {
                header = "Fluid Library" + "\n";
                Files.write(Paths.get(filePath), header.getBytes(), StandardOpenOption.APPEND);
                fw.close();
                headerFlag = 1;
            }
            customFluid = f + "\n";
            Files.write(Paths.get(filePath), customFluid.getBytes(), StandardOpenOption.APPEND);
            fw.close();
        }
    }

    /*
    public void loadTasks(Fluid fluid, Meal meal, ScheduleTracker scheduleTracker, WorkoutTracker workoutTracker,
                          WeightTracker weightTracker) throws IOException {
        String newFilePath = new File(this.filePath).getAbsolutePath();
        File f = new File(newFilePath);
        Scanner s = new Scanner(f);
        String textFromFile;

        while (s.hasNext()) {
            if (s.nextLine().equals("Meals")) {

            }
        }
        while (s.hasNext()) {
            textFromFile = s.nextLine();
            loadedTasks.add(textFromFile);
        }
        System.out.println(Ui.STARTING_MESSAGE);
        return loadedTasks;
    }
     */
}