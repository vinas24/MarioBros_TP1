//Grupo 24: HugoLozanoRuiz - SergioViñasGonzalez
package tp1.control.commands;

public abstract class NoParamsCommand extends AbstractCommand {

	public NoParamsCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help);
	}

    //TODO: no sé como hacerlo con lo del update si el comando esta vacio
	@Override
	public Command parse(String[] commandWords) {
         if (commandWords.length == 1 ) {
             if (matchCommandName(commandWords[0])) return this;
             if (this.getClass() == UpdateCommand.class && commandWords[0].isEmpty()) return new UpdateCommand();
             else return null;
         }
         else return null;
	}
}
