import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;

public class Main {
    //Text
    final static String LogName = "Лог";
    final static String Error = "Ошибка";
    final static String StatusName = "Статус";
    final static String SendName = "Отправить";
    final static String RefreshName = "Обновить";
    final static String Registration = "Регистрация";
    final static String Authentication = "Аутентификация";
    final static String ConnectName = "Подключиться";
    final static String ClientsName = "Пользователи";
    final static String EnterLogin = "Введите логин:";
    final static String DisconnectName = "Отключиться";
    final static String Identification = "Идентификация";
    final static String EnterPassword = "Введите пароль:";
    final static String ClientsNo = "Нет пользователей...";
    final static String NeedRegistration = "Необходимо пройти регистрацию";
    final static String NeedIdentification = "Необходимо пройти идентификацию";
    final static String WrongProtocol = "Для подключения необходим протокол: ";
    final static String BusyServerConnection = "Искомый пользователь уже подключен";
    final static String[] ErrorLogin = new String[]{"Неверный логин или пароль",
            "Попробуйте пройти идентификацию заново"};
    final static String[] SuccessRegistration = new String[]{"Регистрация прошла успешно",
            "Пройдите идентификацию"};
    final static String[] NeedChooseUser = new String[]{"Не выбран пользователь для подключения",
            "Нажмите \"" + Main.RefreshName + "\" для получения списка пользователей",
            "или выберите пользователя"};

    //Regex
    final static String LoginRegex = "([a-z]|[A-Z])([a-z]|[A-Z]|[0-9]){2,19}";
    final static String PasswordRegex = "([a-z]|[A-Z]|[0-9]){3,20}";

    final static int limitLoops = 100;
    final static Long[] SimpleNumbers = simpleGen(1000);

    //Status
    final static String rsaStatusNames = "P: \nQ: \nМодуль: \nФункция Эйлера: \nОткрытая экспонента: \nD: \nПолученная экспонента: \nПолученный модуль: ";
    final static String srpStatusNames = "A: \nB: \nu: \nK: \nM: \nR: ";

    //Ports
    final static int EnvironmentPort = 5999;
    final static int BeginPortInterval = 6000;
    final static int EndPortInterval = 6500;

    //Files
    static String UsersFile = ".\\res\\users.txt";

    final static boolean LogEnv = false;

    public static void main(String[] args) {
        localizationGUI();
        new Environment(EnvironmentPort);
        new Client("Alica", 6001);
        new Client("Bob", 6007);
        //new Client("Kevin", 6005);
    }

    private static void localizationGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        UIManager.put("OptionPane.yesButtonText", "Да");
        UIManager.put("OptionPane.noButtonText", "Нет");
        UIManager.put("OptionPane.cancelButtonText", "Отмена");
        UIManager.put("OptionPane.okButtonText", "Готово");

        FontUIResource f = new FontUIResource(new Font("Verdana", Font.PLAIN, 12));
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object val = UIManager.get(key);
            if (val instanceof FontUIResource) {
                FontUIResource orig = (FontUIResource) val;
                Font font = new Font(f.getFontName(), orig.getStyle(), f.getSize());
                UIManager.put(key, new FontUIResource(font));
            }
        }
    }

    private static Long[] simpleGen(int limitNumber) {
        Long[] simpleNumbers = new Long[limitNumber];
        ArrayList<Long> sns = new ArrayList<>();

        for (int i = 2; i * i < limitNumber; i++) {
            if (simpleNumbers[i] == null) {
                for (int j = i * i; j < limitNumber; j += i) {
                    simpleNumbers[j] = Integer.toUnsignedLong(0);
                }
            }
        }
        for (int i = 2; i < simpleNumbers.length; i++) {
            if (simpleNumbers[i] == null) {
                sns.add(Integer.toUnsignedLong(i));
            }
        }
        return sns.toArray(new Long[0]);
    }
}