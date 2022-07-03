package creational.factory.dialog;

import creational.factory.buttons.Button;
import creational.factory.buttons.HtmlButton;

public class HtmlDialog extends Dialog {

    @Override
    public Button createButton() {
        return new HtmlButton();
    }
}