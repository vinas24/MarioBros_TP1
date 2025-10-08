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
			view.showMessage(Messages.HELP);
		}

		else if(prompt[0].equalsIgnoreCase("reset")||prompt[0].equalsIgnoreCase("r")) {
			//super rudimentario, por ahora mira si el el promt son solo nums
			if(prompt.length == 1 || !prompt[1].matches("^[0-9]+")) view.showMessage(Messages.LEVEL_NOT_A_NUMBER_ERROR);
			else {
				//Si el lvl es un nivel, cargará ese lvl
				int lvl = Integer.parseInt(prompt[1]);
				// No sé como cargar un nuevo juego sobre el actual, claramente esto esta mal
				game.resetLevel(lvl);
			}
		}
		else if(prompt[0].equalsIgnoreCase("action") || prompt[0].equalsIgnoreCase("a")) {
			//Pide las acciones que realizará mario, pueden ser varias
			Iterator<String> i = Arrays.stream(prompt).iterator();
			i.next(); //limpiamos el primero (prompt[0]);
			while(i.hasNext()){
				switch (i.next().toLowerCase()){
					case "u", "up" -> game.addAction(Action.UP);
					case "d", "down" -> game.addAction(Action.DOWN);
					case "l", "left" -> game.addAction(Action.LEFT);
					case "right", "r" -> game.addAction(Action.RIGHT);
					default  -> game.addAction(Action.STOP);
				}
			}
		}
		else if(prompt[0].equalsIgnoreCase("update") || prompt[0].equalsIgnoreCase("u") || prompt[0].equalsIgnoreCase("")) {
			game.update();
		}

		return exit;
	}


	public void run() {
		String [] prompt;
		boolean exit = false;
		view.showWelcome();
		view.showGame();
		//TODO fill your code: The main loop that displays the game, asks the user for input, and executes the action.
		while (!game.isFinished() && !exit) {
			// Pedir una línea al usuario (getPromt)
			prompt = view.getPrompt();
			exit = comando(prompt);
			// Ejecutar el comando del usuario (métod) (por ahora solo muestra
			view.showGame();
		}
		view.showEndMessage();
	}
}
