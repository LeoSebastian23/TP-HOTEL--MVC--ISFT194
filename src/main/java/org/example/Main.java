package org.example;

import Controllers.HabitacionController;
import Views.HabitacionView;

public class Main {
    public static void main(String[] args) {
        HabitacionController habitacionController = new HabitacionController();
        HabitacionView habitacionesView = new HabitacionView(habitacionController);
    }
}
