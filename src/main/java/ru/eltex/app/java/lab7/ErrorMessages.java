package ru.eltex.app.java.lab7;

import ru.eltex.app.java.lab5.Main;

public enum ErrorMessages {

    ERROR1_MESSAGE {
        public String toString() {
            return "Заказ не найден!";
        }
    },

    ERROR2_MESSAGE {
        public String toString() {
            return "Файл " + Main.getJsonOrders() + " повреждён!";
        }
    },

    ERROR3_MESSAGE {
        public String toString() {
            return "Неправильная команда!";
        }
    }

}