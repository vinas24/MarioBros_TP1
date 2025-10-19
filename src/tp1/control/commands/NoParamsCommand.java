//Grupo 24: HugoLozanoRuiz - SergioViñasGonzalez
package tp1.control.commands;

public abstract class NoParamsCommand extends AbstractCommand {

	public NoParamsCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help);
	}

    //TODO: no sé como hacerlo con lo del update si el comando esta vacio
	@Override
	public Command parse(String[] commandWords) {
         if (matchCommandName(commandWords[0])) return this;
         else return null;
	}
}
