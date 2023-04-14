/**
 * This is a Java class definition for a thread that updates a GalagaLogic panel.
 * The GalagaLogic panel is passed to the constructor of the UpdateThread as an argument.
 * The run method of the UpdateThread class contains an infinite loop that calls
 * the updateScene method of the GalagaLogic panel.
 *
 * @author Anton Filippov || 000843198
 */

public class UpdateThread extends Thread {

    private final GalagaLogic panel;

    public UpdateThread(GalagaLogic p) {
        panel = p;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            panel.updateScene();
                try {
                    sleep(60);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
        }
    }

}
