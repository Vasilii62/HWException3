import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class UserData {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите данные (Фамилия Имя Отчество ДатаРождения НомерТелефона Пол): ");
            String input = scanner.nextLine();

            String[] data = input.split(" ");
            if (data.length != 6) {
                throw new IllegalArgumentException("Введено недостаточно данных или превышено количество требуемых параметров.");
            }

            String surname = data[0];
            String name = data[1];
            String patronymic = data[2];
            String dateOfBirth = data[3];
            String phoneNumber = data[4];
            String gender = data[5];

            validateDateOfBirth(dateOfBirth);
            validatePhoneNumber(phoneNumber);
            validateGender(gender);

            String formattedData = surname + " " + name + " " + patronymic + " " + dateOfBirth + " " + phoneNumber + " " + gender;
            saveUserData(surname, formattedData);

            System.out.println("Данные успешно сохранены в файл.");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void validateDateOfBirth(String dateOfBirth) throws IllegalArgumentException {
        try {
            LocalDate.parse(dateOfBirth, java.time.format.DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Неверный формат даты рождения. Ожидается формат dd.mm.yyyy.");
        }
    }

    private static void validatePhoneNumber(String phoneNumber) throws IllegalArgumentException {
        if (phoneNumber.length() != 10) {
            throw new IllegalArgumentException("Номер телефона должен содержать 10 цифр.");
        }
        try {
            Long.parseLong(phoneNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Номер телефона должен состоять только из цифр.");
        }
    }

    private static void validateGender(String gender) throws IllegalArgumentException {
        if (!gender.equalsIgnoreCase("ж") && !gender.equalsIgnoreCase("м")) {
            throw new IllegalArgumentException("Неверное значение пола. Допустимые значения: ж, м.");
        }
    }

    private static void saveUserData(String surname, String data) throws IOException {
        try (FileWriter writer = new FileWriter(surname + ".txt", true)) {
            writer.write(data + System.lineSeparator());
        }
    }
}

