package tp1.control.commands;

import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

public class ExitCommand extends NoParamsCommand{

	// Forman parte de atributos de estado
	private static final String NAME = Messages.COMMAND_EXIT_NAME;
	private static final String SHORTCUT = Messages.COMMAND_EXIT_SHORTCUT;
	private static final String DETAILS = Messages.COMMAND_EXIT_DETAILS;
	private static final String HELP = Messages.COMMAND_EXIT_HELP;

	public ExitCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP); 
	}

	@Override
	public void execute(GameModel game, GameView view){
		// if it needs to close something, it can close it and finish.
	    game.exit(); 	
	}

	@Override
	public String toString() {
		return "ExitCommand [getName()=" + getName() + ", getShortcut()=" + getShortcut() + ", getDetails()="
				+ getDetails() + ", getHelp()=" + getHelp() + ", helpText()=" + helpText() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	
	
}
