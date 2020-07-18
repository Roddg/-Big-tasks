package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class BotClient extends Client{

    public static void main(String[] args) {
        new BotClient().run();
    }

    public class BotSocketThread extends SocketThread {

        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            // Вывести в консоль текст полученного сообщения message
            ConsoleHelper.writeMessage(message);

            // Получить из message имя отправителя и текст сообщения. Они разделены ": "
            if (!message.contains(": ")) return;

            String[] textArray = message.split(":");
            String nameOfUser = textArray[0];
            String data = textArray[1].trim();

            SimpleDateFormat dateFormat;
            String resultMessage = "Информация для " + nameOfUser +": ";
            Date time = new GregorianCalendar().getTime();

            // Отправить ответ в зависимости от текста принятого сообщения. Если текст сообщения:
            switch (data) {

                case ("дата"):
                    dateFormat = new SimpleDateFormat("d.MM.YYYY");
                    resultMessage += dateFormat.format(time);
                    sendTextMessage(resultMessage);
                    break;

                case ("день"):
                    dateFormat = new SimpleDateFormat("d");
                    resultMessage += dateFormat.format(time);
                    sendTextMessage(resultMessage);
                    break;

                case ("месяц"):
                    dateFormat = new SimpleDateFormat("MMMM");
                    resultMessage += dateFormat.format(time);
                    sendTextMessage(resultMessage);
                    break;

                case ("год"):
                    dateFormat = new SimpleDateFormat("YYYY");
                    resultMessage += dateFormat.format(time);
                    sendTextMessage(resultMessage);
                    break;

                case ("время"):
                    dateFormat = new SimpleDateFormat("H:mm:ss");
                    resultMessage += dateFormat.format(time);
                    sendTextMessage(resultMessage);
                    break;

                case ("час"):
                    dateFormat = new SimpleDateFormat("H");
                    resultMessage += dateFormat.format(time);
                    sendTextMessage(resultMessage);
                    break;

                case ("минуты"):
                    dateFormat = new SimpleDateFormat("m");
                    resultMessage += dateFormat.format(time);
                    sendTextMessage(resultMessage);
                    break;

                case ("секунды"):
                    dateFormat = new SimpleDateFormat("s");
                    resultMessage += dateFormat.format(time);
                    sendTextMessage(resultMessage);
                    break;
            }
        }
    }

    @Override
    protected String getUserName() {
        // метод должен генерировать новое имя бота, например: date_bot_XX, где XX – любое число от 0 до 99.
        // Этот метод должен возвращать каждый раз новое значение, на случай, если на сервере захотят зарегистрироваться несколько ботов, у них должны быть разные имена.
        return "date_bot_" + (int)(Math.random() * 100);
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }
}
