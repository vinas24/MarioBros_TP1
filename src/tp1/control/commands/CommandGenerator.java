//Grupo 24: HugoLozanoRuiz - SergioViñasGonzalez
package tp1.control.commands;

import java.util.Arrays;
import java.util.List;

import tp1.view.Messages;

public class CommandGenerator {

	private static final List<Command> availableCommands = Arrays.asList(
            new ActionCommand(null), //modificado a usar el constructor con param
            new UpdateCommand(),
            new ResetCommand(),
			new HelpCommand(),
			new ExitCommand()
	);

	public static Command parse(String[] commandWords) {
        Command com = null;
        int i = 0;
        boolean parseCorrecto = false;
        //comprueba el parse de cada comando, hasta encontrar uno que no devuelva null
        while(i < availableCommands.size() && !parseCorrecto) {
            com = availableCommands.get(i).parse(commandWords);
            if(com != null) parseCorrecto = true;
            i++;
        }

		return com;
	}
		
	public static String commandHelp() {
		StringBuilder commands = new StringBuilder();
		
		commands.append(Messages.HELP_AVAILABLE_COMMANDS).append(Messages.LINE_SEPARATOR);
		
		for (Command c: availableCommands) {
			commands.append(c.helpText());
		}
		
		return commands.toString();
	}

}
