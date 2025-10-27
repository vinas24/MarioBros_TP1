//Grupo 24: HugoLozanoRuiz - SergioViñasGonzalez
package tp1.control.commands;

public abstract class NoParamsCommand extends AbstractCommand {

	public NoParamsCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help);
	}

	@Override
	public Command parse(String[] commandWords) {
        Command command = null;
        //Si el tamaño es uno, y es un comando valido, devolverá dicho comando
         if (commandWords.length == 1 && matchCommandName(commandWords[0])) command = this;
         return command;
	}
}
