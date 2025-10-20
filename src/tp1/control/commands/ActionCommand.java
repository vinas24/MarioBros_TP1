//Grupo 24: HugoLozanoRuiz - SergioViñasGonzalez
package tp1.control.commands;

import tp1.logic.Action;
import tp1.logic.Game;
import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ActionCommand extends AbstractCommand {
    private static final String NAME = Messages.COMMAND_ACTION_NAME;
    private static final String SHORTCUT = Messages.COMMAND_ACTION_SHORTCUT;
    private static final String DETAILS = Messages.COMMAND_ACTION_DETAILS;
    private static final String HELP = Messages.COMMAND_ACTION_HELP;
    private final List<String> acciones;

    public ActionCommand() {
        super(NAME, SHORTCUT, DETAILS, HELP);
        this.acciones = null;
    }

    public ActionCommand(List<String> acciones) {
        super(NAME, SHORTCUT, DETAILS, HELP);
        this.acciones = acciones;
    }

    @Override
    public void execute(GameModel game, GameView view) {
        if (this.acciones != null) {
            //Pide las acciones que realizará mario, pueden ser varias
            Iterator<String> i = acciones.iterator();
            boolean actionIncorrecta = false;
            i.next(); //limpiamos el primero (nombre del comando)
            while (i.hasNext()) {
                String s = i.next();
                switch (s.toLowerCase()) {
                    case "u", "up" -> game.addAction(Action.UP);
                    case "d", "down" -> game.addAction(Action.DOWN);
                    case "l", "left" -> game.addAction(Action.LEFT);
                    case "right", "r" -> game.addAction(Action.RIGHT);
                    case "stop", "s" -> game.addAction(Action.STOP);
                    default -> {
                        /*
                        NO hace falta ahora aparentemente ????
                        view.showError(Messages.UNKNOWN_ACTION.formatted(s));
                        actionIncorrecta = true;
                         */
                    }
                }
            }
            if (!actionIncorrecta) {
                game.update();
                view.showGame();
            }
        }
    }

    @Override
    public Command parse(String[] commandWords) {
        if(matchCommandName(commandWords[0]) && commandWords.length != 1) {
            //Añadimos las acciones
            List <String> listaAcciones =Arrays.stream(commandWords).toList();
            return new ActionCommand(listaAcciones);
        }
        return null;
    }
}
