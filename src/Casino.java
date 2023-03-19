import java.util.ArrayList;


public class Casino {

    Dealer dealer;

    ArrayList<Player> playersArray = new ArrayList<>();

    public static void main(String[] args) {

        openConsole();
        Casino currentCasino = new Casino();
        currentCasino.startGame(6, 3);
    }

    static void openConsole(){
        //открываем консоль и ждем команд
        //startGame(cardsPerPlayer,prayersQuantity);
    }

    public void startGame(int cardsPerPlayer, int prayersQuantity){

        //создадим дилера с перемешанной колодой карт
        dealer = new Dealer();

        //создадим нужное количество игроков
        playersArray.clear();
        for (int i=1; i<=prayersQuantity; i++){
            playersArray.add(new Player());
        }

        //добавим каждому игроку нужное количество карт
        //заставим сделать это нашего дилера
        dealer.dealCards(playersArray, cardsPerPlayer);

        //выведем содержимое колод каждого игрока
        for (Player currentPlayer:playersArray
        ) {

            printPlayersCards(currentPlayer);
        }

    }

    void printPlayersCards(Player currentPlayer){


        String messageToPrint = Integer.toString(playersArray.indexOf(currentPlayer) + 1);

        for (Card currentCard: currentPlayer.playerDeck
        ) {
            messageToPrint = messageToPrint + " " + currentCard.cardColor + Integer.toString(currentCard.cardNumber);
        }

        System.out.println(messageToPrint);

    }

}
