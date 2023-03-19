import java.util.ArrayList;
import java.util.Collections;

public class Dealer {

    ArrayList<Card> dealerDeck = new ArrayList<>();

    public Dealer() {

        String availableCardColors = "RGBW";

        //создадим колодку карт
        for (int i = 0; i < availableCardColors.length(); i++){

            for (int j = 1; j <= 10; j++){
                this.dealerDeck.add(new Card(availableCardColors.substring(i,i+1),j));
            }
        }
        //перемешаем колоду
        Collections.shuffle(this.dealerDeck);
    }

    public void dealCards(ArrayList<Player>playersArray, int cardsPerPlayer){

        //берем верхнюю карту в колоде и выдаем ее следующему игроку
       for (int i=0; i<cardsPerPlayer; i++){
            for (Player currentPlayer:playersArray
            ) {

                //добавляем карту игроку
                currentPlayer.playerDeck.add(dealerDeck.get(0));

                //удаляем карту из колоды дилера
                dealerDeck.remove(0);
            }
        }

    }

}
