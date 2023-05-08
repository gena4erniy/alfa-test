package helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.stream.Collectors;

//Класс помощник для выполнения bash команд
public class DeviceHelper {

    //Выполняет bash скрипт с возвратом полной информации из консоли
    public static String executeSh(String command) throws IOException, ExecutionException, InterruptedException {
        Process p = Runtime.getRuntime().exec(command); //Получаем инстанс терминала и выполняем скрипт
        FutureTask<String> future = new FutureTask<>(() -> { //Создаем FutureTask
            return new BufferedReader(new InputStreamReader(p.getInputStream())) //Читаем поток информации из консоли
                    .lines().map(Object::toString) //Информацию преобразуем в строку
                    .collect(Collectors.joining("\n")); //Все строки собираем в одну с разделением в виде новой строки
        });
        new Thread(future).start(); //Запускаем поток
        return future.get(); //Ждем завершения CallBack для получения полной конечной информации из консоли
    }

    //Выполняет bash скрипт через терминал с обработкой exception
    public static String executeBash(String command) {
        Process p;
        try {
            p = Runtime.getRuntime().exec(command); //Получаем инстанс терминала и посылаем скрипт
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        final String[] message = {""}; //Массив с 1 элементом для записи строк из терминала

        new Thread(() -> { //Запускаем новый поток чтобы не было сообщения "Процесс не отвечает", в случае если команда будет выполнятся бесконечно
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream())); //Получаем поток информации
            String line = null;
            while (true) {
                try {
                    if ((line = input.readLine()) == null) { //Читаем строки пока они есть
                        break;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                message[0] += line + "\n"; //Записываем строки в первый элемент массива
            }
            System.out.println(message[0]); //Выводим в консоль для дебагинга
        }).start(); //Стартуем поток
        try {
            p.waitFor(); //Ждем завершения потока
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return message[0];
    }
}