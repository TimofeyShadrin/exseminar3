module ru.gb.exseminars.exseminarThree {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.gb.exseminars.exseminarThree to javafx.fxml;
    exports ru.gb.exseminars.exseminarThree;
    exports ru.gb.exseminars.exseminarThree.controller;
    opens ru.gb.exseminars.exseminarThree.controller to javafx.fxml;
}