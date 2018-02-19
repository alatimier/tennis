package kata.tennis;

import java.util.Optional;

public class Game {

	private Player server;
	private Player receiver;

	private int serverScore = 0;
	private int receiverScore = 0;

	public Game(Player server, Player receiver) {
		this.server = server;
		this.receiver = receiver;
	}

	private void assertNoWinner() {
		if (getWinner().isPresent()) {
			throw new IllegalStateException("Game is over");
		}
	}

	public void serverScores() {
		assertNoWinner();
		serverScore++;
	}

	public void receiverScores() {
		assertNoWinner();
		receiverScore++;
	}

	public String printScore() {
		Optional<Player> winner = getWinner();
		if (winner.isPresent()) {
			return winner.get().getName() + " won";
		}

		Optional<Player> advantage = getAdvantage();
		if (advantage.isPresent()) {
			return "Advantage " + advantage.get().getName();
		}

		if (serverScore == receiverScore) {
			if (serverScore > 3) {
				return "Deuce";
			} else {
				return translateScore(serverScore) + "/A";
			}
		}

		return translateScore(serverScore) + "/" + translateScore(receiverScore);
	}

	public Optional<Player> getWinner() {
		if (hasWon(serverScore, receiverScore)) {
			return Optional.of(server);
		}
		if (hasWon(receiverScore, serverScore)) {
			return Optional.of(receiver);
		}
		return Optional.empty();
	}

	private boolean hasWon(int score, int opponentScore) {
		return score > 3 && score - opponentScore >= 2;
	}

	private Optional<Player> getAdvantage() {
		if (hasAdvantage(serverScore, receiverScore)) {
			return Optional.of(server);
		}
		if (hasAdvantage(receiverScore, serverScore)) {
			return Optional.of(receiver);
		}
		return Optional.empty();
	}

	private boolean hasAdvantage(int score, int opponentScore) {
		return score > 3 && score - opponentScore == 1;
	}

	private String translateScore(int score) {
		switch (score) {
			case 0:
				return "0";
			case 1:
				return "15";
			case 2:
				return "30";
			case 3:
				return "40";
			default:
				throw new IllegalArgumentException("No translation for score " + score);
		}
	}

}
