package tp1.control.commands;

public abstract class NoParamsCommand extends AbstractCommand {

	public NoParamsCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help);
	}

    //TODO: no sé como hacerlo con lo del update si el comando esta vacio
    //Devuelve null o el tipo de comando sin parametros que corresponde
	@Override
	public Command parse(String[] commandWords) {
         if (matchCommandName(commandWords[0])) return this;
         else return null;
	}
}
