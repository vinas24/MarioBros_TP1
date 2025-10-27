//Grupo 24: HugoLozanoRuiz - SergioViñasGonzalez
package tp1.control.commands;

import tp1.logic.Action;
import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

import java.util.ArrayList;
import java.util.List;

public class ActionCommand extends AbstractCommand {
    private static final String NAME = Messages.COMMAND_ACTION_NAME;
    private static final String SHORTCUT = Messages.COMMAND_ACTION_SHORTCUT;
    private static final String DETAILS = Messages.COMMAND_ACTION_DETAILS;
    private static final String HELP = Messages.COMMAND_ACTION_HELP;
    private List<Action> acciones;

    public ActionCommand(List<Action> acciones) {
        super(NAME, SHORTCUT, DETAILS, HELP);
        this.acciones = acciones;
    }

    @Override
    public void execute(GameModel game, GameView view) {
        if (this.acciones != null) {
            //añadimos las acciones parseadas anteriormente
            for (Action a : this.acciones) {
                game.addAction(a);
            }
            game.update();
            view.showGame();
        }
    }

    @Override
    public Command parse(String[] commandWords) {
        Command c = null;
        if(matchCommandName(commandWords[0]) && commandWords.length != 1) {
            acciones = new ArrayList<>(); //Lo declaramos como lista vacia
            //Parseamos todos las acciones de commandWords
            for(int i = 1; i < commandWords.length; ++i){
                Action a = Action.parse(commandWords[i]);
                if(a != null) acciones.add(a);
            }
            c = new ActionCommand(acciones);
        }
        return c;
    }
    
}
