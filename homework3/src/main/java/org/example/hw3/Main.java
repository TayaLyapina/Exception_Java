package org.example.hw3;

//Напишите приложение, которое будет запрашивать у пользователя следующие данные в произвольном порядке, разделенные пробелом:
//Фамилия Имя Отчество датарождения номертелефона пол
//Форматы данных:
//фамилия, имя, отчество - строки
//
//дата_рождения - строка формата dd.mm.yyyy
//
//номер_телефона - целое беззнаковое число без форматирования
//
//пол - символ латиницей f или m.
//
//Приложение должно проверить введенные данные по количеству. Если количество не совпадает с требуемым, вернуть код ошибки, обработать его и показать пользователю сообщение, что он ввел меньше и больше данных, чем требуется.
//
//Приложение должно попытаться распарсить полученные значения и выделить из них требуемые параметры. Если форматы данных не совпадают, нужно бросить исключение, соответствующее типу проблемы. Можно использовать встроенные типы java и создать свои. Исключение должно быть корректно обработано, пользователю выведено сообщение с информацией, что именно неверно.
//
//Если всё введено и обработано верно, должен создаться файл с названием, равным фамилии, в него в одну строку должны записаться полученные данные, вида
//
//<Фамилия><Имя><Отчество><датарождения> <номертелефона><пол>
//
//Однофамильцы должны записаться в один и тот же файл, в отдельные строки.
//
//Не забудьте закрыть соединение с файлом.
//
//При возникновении проблемы с чтением-записью в файл, исключение должно быть корректно обработано, пользователь должен увидеть стектрейс ошибки.

import org.example.hw3.User;
import java.io.*;
import java.nio.file.FileSystemException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите, разделенные пробелом:\n" +
                    " - фамилию, имя, отчество,\n" +
                    " - дату рождения (формат dd.mm.yyyy),\n" +
                    " - номер телефона,\n" +
                    " - пол (латиницей f или m):");
            String input = scanner.nextLine();

            String[] inputData = input.split(" ");
            if (inputData.length != 6) {
                throw new IOException("Введено неверное количество параметров");
            }

            String lastName = inputData[0];
            String firstName = inputData[1];
            String patronymic = inputData[2];


            SimpleDateFormat format = new SimpleDateFormat("dd.mm.yyyy");
            Date birthdate;
            try {
                birthdate = format.parse(inputData[3]);
            }catch (ParseException e){
                try {
                    throw new ParseException ("Неверный формат даты рождения", e.getErrorOffset());
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }

            int phoneNumber;
            try {
                phoneNumber = Integer.parseInt(inputData[4]);
            } catch (NumberFormatException e){
                throw new NumberFormatException("Неверный формат телефона");
            }

            String gender = inputData[5];
            if (!gender.toLowerCase().equals("m") && !gender.toLowerCase().equals("f")){
                throw new RuntimeException("Неверно введен пол");
            }

            User user = new User(lastName, firstName, patronymic, birthdate, phoneNumber, gender);
            String fileName = lastName + ".txt";
            File file = new File(fileName);
            try (FileWriter fileWriter = new FileWriter(file, true)){
                if (file.length() > 0){
                    fileWriter.write('\n');
                }
                fileWriter.write(String.format("%s %s %s %s %s %s", lastName, firstName, patronymic, format.format(birthdate), phoneNumber, gender));
            }catch (IOException e){
                throw new FileSystemException("Возникла ошибка при работе с файлом");
            }

        }
    }
}