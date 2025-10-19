package tp1.control.commands;

import tp1.logic.Game;
import tp1.view.GameView;
import tp1.view.Messages;

public class ResetCommand extends AbstractCommand {
    private static final String NAME = Messages.COMMAND_RESET_NAME;
    private static final String SHORTCUT = Messages.COMMAND_RESET_SHORTCUT;
    private static final String DETAILS = Messages.COMMAND_RESET_DETAILS;
    private static final String HELP = Messages.COMMAND_RESET_HELP;
    private final int numLevel;
    private final String nivelNoNum;

    public ResetCommand() {
        super(NAME, SHORTCUT, DETAILS, HELP);
        this.numLevel = -1;
        this.nivelNoNum = null;
    }

    public ResetCommand(int numLevel, String nivelNoNum) {
        super(NAME, SHORTCUT, DETAILS, HELP);
        this.numLevel = numLevel;
        this.nivelNoNum = nivelNoNum;
    }

    @Override
    public void execute(Game game, GameView view) {
        if(this.nivelNoNum == null) {
            game.resetLevel(this.numLevel);
            view.showGame();
        } else {
            view.showMessage(Messages.LEVEL_NOT_A_NUMBER_ERROR.formatted(nivelNoNum));
        }
    }

    @Override
    public Command parse(String[] commandWords) {
        if(matchCommandName(commandWords[0])) {
            //Si no hay parámetros
            if(commandWords.length == 1) {
                return new ResetCommand();
            } else if (commandWords.length == 2) {
                //si el parametro es un numero
                if(commandWords[1].matches("^[0-9]+"))
                    return new ResetCommand(Integer.parseInt(commandWords[1]), null);
                //si no es un num, nivelNoNum será la entrada, para el mensaje de error
                else return new ResetCommand(0, commandWords[1]);
            }
        }
        return null;
    }
}
