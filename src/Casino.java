import java.util.ArrayList;
import java.util.Scanner;

public class Casino {

    Dealer dealer;

    ArrayList<Player> playersArray = new ArrayList<>();

    public static void main(String[] args) {

        Casino currentCasino = new Casino();

        Scanner sc = new Scanner(System.in);
        //ожидаем ввода команды в бесконечном цикле
        while (true){
            String consoleCommand = sc.nextLine();

            //обрабатываем введенную команду
            currentCasino.processCommand(consoleCommand);
        }
    }

    void processCommand(String consoleCommand){

        //по условиям задачи не нужно проверять валидность введенной команды (только количество игроков и количество карт на игрока)

        //преобразуем строку consoleCommand в массив с пробелом в качестве разделителя
        String[] consoleCommandArray = consoleCommand.split(" ");

        //определим тип команды - start или get-cards
        if (consoleCommandArray[0].equals("start")){
            //проверяем валидность переданных N и C команды start, и если всё ок, раздаем карты игрокам
            //N * C может иметь диапазон от 1 (один игрок с одной картой) до 40 (количество карт в колоде дилера)
            //N для удобства назовем cardsPerPlayer
            //C для удобства назовем playersQuantity
            int cardsPerPlayer = Integer.parseInt(consoleCommandArray[1]);
            int playersQuantity = Integer.parseInt(consoleCommandArray[2]);

            if(cardsPerPlayer*playersQuantity < 1 || cardsPerPlayer*playersQuantity > 40){
                System.out.println("Введенное количество игроков и карт должно быть таким, чтобы всего было роздано от 1 до 40 карт");
                return;
            }

            //если пользователь ввел корректные данные, раздаем карты и не выводим никаких сообщений
            startGame(cardsPerPlayer, playersQuantity);

        }else{
            //первым делом проверим, вызывалась ли ранее команда start
            //смотрим, есть ли хотя бы один игрок за столом
            if (playersArray.size() == 0){
                System.out.println("Сначала задайте количество карт и игроков с помощью команды start N C");
                return;
            }

            //проверяем валидность переданного C команды get-cards, и если всё ок, выводим в консоль номер игрока и количество карт
            //C может иметь диапазон от 1 до количества игроков
            //C для удобства назовем playerNumber
            int playerNumber = Integer.parseInt(consoleCommandArray[1]);

            if(playerNumber < 1 || playerNumber > playersArray.size()){
                System.out.println("Введенный номер игрока должен находиться в диапазоне от 1 до " + playersArray.size());
                return;
            }

            //если пользователь ввел корректные данные, выводим в консоль номер игрока и количество карт
            printPlayersCards(playersArray.get(playerNumber-1));
        }

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

    }

    void printPlayersCards(Player currentPlayer){


        String messageToPrint = Integer.toString(playersArray.indexOf(currentPlayer) + 1);

        for (Card currentCard: currentPlayer.playerDeck
        ) {
            messageToPrint = messageToPrint + " " + currentCard.cardColor + currentCard.cardNumber;
        }

        System.out.println(messageToPrint);

    }

}
