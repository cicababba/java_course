package creational.factory.dialog;

import creational.factory.buttons.Button;

public abstract class Dialog {

    public void renderWindow() {

        Button okButton = createButton();
        okButton.render();
    }
    public abstract Button createButton();
}
