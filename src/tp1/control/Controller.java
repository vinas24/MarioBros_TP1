//Grupo 24: HugoLozanoRuiz - SergioViñasGonzalez
package tp1.control;

import tp1.logic.Action;
import tp1.logic.ActionList;
import tp1.logic.Game;
import tp1.view.GameView;
import tp1.view.Messages;

import java.util.Arrays;
import java.util.Iterator;

/**
 *  Accepts user input and coordinates the game execution logic
 */
public class Controller {

	private Game game;
	private GameView view;

	public Controller(Game game, GameView view) {
		this.game = game;
		this.view = view;
	}


	/**
	 * Runs the game logic, coordinate Model(game) and View(view)
	 *
	 */
	private boolean comando(String [] prompt) {
		boolean exit = false;
		if(prompt[0].equalsIgnoreCase("exit")||prompt[0].equalsIgnoreCase("e")) {
			//cierra el programa
			exit = true;
		}

		else if(prompt[0].equalsIgnoreCase("help")||prompt[0].equalsIgnoreCase("h")) {
			//Muestra una guia de los posibles comandos
			if(prompt.length != 1)  view.showError(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
			else view.showMessage(Messages.HELP);
		}

		else if(prompt[0].equalsIgnoreCase("reset")||prompt[0].equalsIgnoreCase("r")) {
			//super rudimentario, por ahora mira si el el promt son solo nums
			if (prompt.length == 1) {
				game.resetLevel();
				view.showGame();

			}
			else if( !prompt[1].matches("^[0-9]+")) view.showMessage(Messages.LEVEL_NOT_A_NUMBER_ERROR.formatted(prompt[1]));
			else {
				//Si el lvl es un nivel, cargará ese lvl
				int lvl = Integer.parseInt(prompt[1]);
				// No sé como cargar un nuevo juego sobre el actual, claramente esto esta mal
				game.resetLevel(lvl);
				view.showGame();

			}
		}
		else if(prompt[0].equalsIgnoreCase("action") || prompt[0].equalsIgnoreCase("a")) {
			if(prompt.length == 1) view.showError(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
			else {
				//Pide las acciones que realizará mario, pueden ser varias
				Iterator<String> i = Arrays.stream(prompt).iterator();
				i.next(); //limpiamos el primero (prompt[0]);
				while (i.hasNext()) {
					String s = i.next();
					switch (s.toLowerCase()) {
						case "u", "up" -> game.addAction(Action.UP);
						case "d", "down" -> game.addAction(Action.DOWN);
						case "l", "left" -> game.addAction(Action.LEFT);
						case "right", "r" -> game.addAction(Action.RIGHT);
						case "stop", "s" -> game.addAction(Action.STOP);
						default -> view.showError(Messages.UNKNOWN_ACTION.formatted(s));
					}
				}
				game.update();
				view.showGame();
			}
		}
		else if(prompt[0].equalsIgnoreCase("update") || prompt[0].equalsIgnoreCase("u") || prompt[0].equalsIgnoreCase("")) {
			game.update();
			view.showGame();
		}
		
		else view.showError(Messages.UNKNOWN_COMMAND.formatted(prompt[0]));

		return exit;
	}


	public void run() {
		String [] prompt;
		boolean exit = false;
		view.showWelcome();
		view.showGame();
		while (!game.isFinished() && !exit) {
			// Pedir una línea al usuario (getPromt)
			prompt = view.getPrompt();
			exit = comando(prompt);
			// Ejecutar el comando del usuario (métod) (por ahora solo muestra
		}
		view.showEndMessage();
	}
}



//
 /*
 package tp1.control;

import tp1.control.commands.Command;
import tp1.control.commands.CommandGenerator;
import tp1.logic.Game;
import tp1.view.GameView;
import tp1.view.Messages;

/**
 *  Accepts user input and coordinates the game execution logic
 */
/*
public class Controller {

    private Game game;
    private GameView view;

    public Controller(Game game, GameView view) {
        this.game = game;
        this.view = view;
    }


    /**
     * Runs the game logic, coordinate Model(game) and View(view)
     */
/*
    public void run() {

        view.showWelcome();

        view.showGame();

        while ( !game.isFinished()) {
            String[] words = view.getPrompt();
            Command command = CommandGenerator.parse(words);

            if (command != null)
                command.execute(game, view);
            else
                view.showError(Messages.UNKNOWN_COMMAND.formatted(String.join(" ", words)));
        }
        view.showEndMessage();
    }
}
 */
