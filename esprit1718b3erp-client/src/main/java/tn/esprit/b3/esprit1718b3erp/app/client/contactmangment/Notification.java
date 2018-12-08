package tn.esprit.b3.esprit1718b3erp.app.client.contactmangment;
import org.controlsfx.control.Notifications;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
public class Notification {
	void notf_right(String s, String s2) {
        javafx.scene.image.Image image = new javafx.scene.image.Image(getClass().getResource("check.png").toExternalForm());
        ImageView iv = new ImageView(image);
        Notifications notificationbuilder = Notifications.create()
                .title(s)
                .text(s2)
                .graphic(iv)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);

        notificationbuilder.darkStyle();
        notificationbuilder.show();
    }

    void notf_false(String s, String s2) {
        javafx.scene.image.Image image = new javafx.scene.image.Image(getClass().getResource("uncheck.png").toExternalForm());
        ImageView iv = new ImageView(image);
        Notifications notificationbuilder = Notifications.create()
                .title(s)
                .text(s2)
                .graphic(iv)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
        notificationbuilder.darkStyle();
        notificationbuilder.show();
    }

}
