package kata.tennis;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameTest {

	private Player server;
	private Player receiver;
	private Game game;

	@Before
	public void before() {
		server = new Player("Server");
		receiver = new Player("Receiver");
		game = new Game(server, receiver);
	}

	@Test
	public void should_have_0_A_score_at_new_game() {
		// Then
		String score = game.printScore();
		Optional<Player> winner = game.getWinner();

		assertEquals("0/A", score);
		assertFalse(winner.isPresent());
	}

	@Test
	public void should_have_15_0_score_when_server_scores_once() {
		// When
		game.serverScores();

		// Then
		String score = game.printScore();
		Optional<Player> winner = game.getWinner();

		assertEquals("15/0", score);
		assertFalse(winner.isPresent());
	}

	@Test
	public void should_have_30_0_score_when_server_scores_twice() {
		// When
		game.serverScores();
		game.serverScores();

		// Then
		String score = game.printScore();
		Optional<Player> winner = game.getWinner();

		assertEquals("30/0", score);
		assertFalse(winner.isPresent());
	}

	@Test
	public void should_have_40_0_score_when_server_scores_three_times() {
		// When
		game.serverScores();
		game.serverScores();
		game.serverScores();

		// Then
		String score = game.printScore();
		Optional<Player> winner = game.getWinner();

		assertEquals("40/0", score);
		assertFalse(winner.isPresent());
	}

	@Test
	public void should_have_0_15_score_when_receiver_scores_once() {
		// When
		game.receiverScores();

		// Then
		String score = game.printScore();
		Optional<Player> winner = game.getWinner();

		assertEquals("0/15", score);
		assertFalse(winner.isPresent());
	}

	@Test
	public void should_have_equality_when_both_players_score_less_than_four_times_and_the_same_number_of_times() {
		// When
		game.serverScores();
		game.receiverScores();
		game.serverScores();
		game.receiverScores();
		game.serverScores();
		game.receiverScores();

		// Then
		String score = game.printScore();
		Optional<Player> winner = game.getWinner();

		assertEquals("40/A", score);
		assertFalse(winner.isPresent());
	}

	@Test
	public void should_have_deuce_when_both_players_score_at_least_four_times_and_the_same_number_of_times() {
		// When
		game.serverScores();
		game.receiverScores();
		game.serverScores();
		game.receiverScores();
		game.serverScores();
		game.receiverScores();
		game.serverScores();
		game.receiverScores();

		// Then
		String score = game.printScore();
		Optional<Player> winner = game.getWinner();

		assertEquals("Deuce", score);
		assertFalse(winner.isPresent());
	}

	@Test
	public void should_have_advantage_when_both_player_score_at_least_three_times_and_one_lead_the_other_by_one_point() {
		// When
		game.serverScores();
		game.receiverScores();
		game.serverScores();
		game.receiverScores();
		game.serverScores();
		game.receiverScores();
		game.serverScores();

		// Then
		String score = game.printScore();
		Optional<Player> winner = game.getWinner();

		assertEquals("Advantage " + server.getName(), score);
		assertFalse(winner.isPresent());
	}

	@Test
	public void should_have_a_winner_when_one_player_scores_at_least_four_times_and_lead_the_other_by_two_points() {
		// When
		game.serverScores();
		game.serverScores();
		game.receiverScores();
		game.receiverScores();
		game.receiverScores();
		game.receiverScores();

		// Then
		String score = game.printScore();
		Optional<Player> winner = game.getWinner();

		assertTrue(winner.isPresent());
		assertEquals(receiver, winner.get());
		assertEquals(receiver.getName() + " won", score);
	}

	@Test(expected = IllegalStateException.class)
	public void should_not_be_able_to_play_if_there_is_a_winner() {
		// When
		game.serverScores();
		game.serverScores();
		game.serverScores();
		game.serverScores();
		game.receiverScores();

		// Then throw exception
	}

}