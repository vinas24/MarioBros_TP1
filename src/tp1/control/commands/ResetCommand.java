//Grupo 24: HugoLozanoRuiz - SergioViñasGonzalez
package tp1.control.commands;

import tp1.logic.Game;
import tp1.logic.GameModel;
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
    public void execute(GameModel game, GameView view) {
        if(this.nivelNoNum == null) {
            if(this.numLevel == -1) {
                //llama al reset del nivel actual
                game.reset();
                view.showGame();
            }
            else {
                //Mierdón de if
                if(this.numLevel == 1 || this.numLevel == 0) {
                    // llama al reset del nivel en cuestion
                    game.reset(this.numLevel);
                    view.showGame();
                }
                else view.showError(Messages.INVALID_LEVEL_NUMBER);
            }
        } else {
            view.showMessage(Messages.LEVEL_NOT_A_NUMBER_ERROR.formatted(nivelNoNum));
        }
    }

    @Override
    public Command parse(String[] commandWords) {
    	Command c = null;
        if(matchCommandName(commandWords[0])) {
            //Si no hay parámetros
            if(commandWords.length == 1) {
                c = new ResetCommand();
            } else if (commandWords.length == 2) {
                //si el parametro es un numero
                if(commandWords[1].matches("^[0-9]+"))
                    c = new ResetCommand(Integer.parseInt(commandWords[1]), null);
                //si no es un num, nivelNoNum será la entrada, para el mensaje de error
                else c = new ResetCommand(0, commandWords[1]);
            }
        }
        return c;
    }

	@Override
	public String toString() {
		return "ResetCommand [numLevel=" + numLevel + ", nivelNoNum=" + nivelNoNum + ", getName()=" + getName()
				+ ", getShortcut()=" + getShortcut() + ", getDetails()=" + getDetails() + ", getHelp()=" + getHelp()
				+ ", helpText()=" + helpText() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
    
    
    
}
