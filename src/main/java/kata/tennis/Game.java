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

	private void assertGameNotOver() {
		if (getWinner().isPresent()) {
			throw new IllegalStateException("Game is over");
		}
	}

	public void serverScores() {
		assertGameNotOver();
		serverScore++;
	}

	public void receiverScores() {
		assertGameNotOver();
		receiverScore++;
	}

	public String printScore() {
		Optional<Player> winner = getWinner();
		if (winner.isPresent()) {
			return winner.get().getName() + " won";
		}

		Optional<Player> advantagePlayer = getAdvantagePlayer();
		if (advantagePlayer.isPresent()) {
			return "Advantage " + advantagePlayer.get().getName();
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
		if ((serverScore >= 4 || receiverScore >= 4) && Math.abs(serverScore - receiverScore) >= 2) {
			return serverScore > receiverScore ? Optional.of(server) : Optional.of(receiver);
		}
		return Optional.empty();
	}

	private Optional<Player> getAdvantagePlayer() {
		if (serverScore >= 3 && receiverScore >= 3 && Math.abs(serverScore - receiverScore) == 1) {
			return serverScore > receiverScore ? Optional.of(server) : Optional.of(receiver);
		}
		return Optional.empty();
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
