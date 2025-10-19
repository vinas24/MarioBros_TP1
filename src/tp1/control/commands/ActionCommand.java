package tp1.control.commands;

import tp1.logic.Action;
import tp1.logic.Game;
import tp1.view.GameView;
import tp1.view.Messages;

import java.util.Arrays;
import java.util.List;

public class ActionCommand extends AbstractCommand {
    private static final String NAME = Messages.COMMAND_ACTION_NAME;
    private static final String SHORTCUT = Messages.COMMAND_ACTION_SHORTCUT;
    private static final String DETAILS = Messages.COMMAND_ACTION_DETAILS;
    private static final String HELP = Messages.COMMAND_ACTION_HELP;
    private final boolean parametrosIncorrectos;
    private final List<String> acciones;

    public ActionCommand() {
        super(NAME, SHORTCUT, DETAILS, HELP);
        this.parametrosIncorrectos = false;
        this.acciones = null;
    }

    public ActionCommand(List<String> acciones) {
        super(NAME, SHORTCUT, DETAILS, HELP);
        this.parametrosIncorrectos = false;
        this.acciones = acciones;
    }

    public ActionCommand(boolean parametrosIncorrectos) {
        super(NAME, SHORTCUT, DETAILS, HELP);
        this.parametrosIncorrectos = parametrosIncorrectos;
        this.acciones = null;
    }

    @Override
    public void execute(Game game, GameView view) {
        if (this.parametrosIncorrectos) { view.showError(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);}
        else {
            if (this.acciones != null) {
                for (String a : this.acciones) {
                    switch (a.toLowerCase()) {
                        case "u", "up" -> game.addAction(Action.UP);
                        case "d", "down" -> game.addAction(Action.DOWN);
                        case "l", "left" -> game.addAction(Action.LEFT);
                        case "right", "r" -> game.addAction(Action.RIGHT);
                        case "stop", "s" -> game.addAction(Action.STOP);
                        default -> view.showError(Messages.UNKNOWN_ACTION.formatted(a));
                    }
                }
            }
        }
    }

    @Override
    public Command parse(String[] commandWords) {
        if(matchCommandName(commandWords[0])) {
            if (commandWords.length == 1) return new ActionCommand(true);
            //Añadimos las acciones
            List <String> listaAcciones =Arrays.stream(commandWords).toList();
            listaAcciones.removeFirst();
            //Elimimamos el primero que es el nombre del comando y no una acción
            return new ActionCommand(listaAcciones);
        }
        return null;
    }
}
