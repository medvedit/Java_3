package ru.medwedSa.Java_3.Lessen_2_SQLite.ClassWork;


import java.sql.*;

/**
 *  В классе DatabaseExample объявлены поля:
 *  1) connection от интерфейса Connection. Connection - это интерфейс, с помощью которого создается
 *     соединение для работы с таблицами sql.
 *  2) statement от интерфейса Statement. Statement - это запрос на выполнение команды, набор инструкций
 *     выполнения действий.
 *  3) preparedStatement от интерфейса PreparedStatement - предварительно скомпилированный запрос.
 *  Кроме выше описанных полей есть набор строчек с запросами в базу данных. Все эти запросы прописаны в
 *  тип данных String.
 *  ••••••••••••••
 *  • DB_CONNECTION_STRING - строка подключения.
 *  • INSERT_STATEMENT - строка реализации preparedStatement.
 *  • CREATE_REQUEST - строка запроса на создание полей таблицы.
 *  • DROP_REQUEST - строка удаления данных из таблицы.
 *  • SIMPLE_INSERT_REQUEST - строка прямого внесения данных в таблицу.
 *  • UPDATE_TABLE - строка изменения name по условию score.
 *  • READ_DATA_TABLE - Чтение данных из таблицы, по условию, с выводом в консоль.
 *  • DELETE_DATA_BY_CONDITION - удаление данных по условию.
 *  • EXAMPLE_CALL - предназначен для вызова процедур. Запускается аналогично prepareStatement, те вместо знаков
 *                   вопросов вставляем значения с указанием позиции. Обязательно создается процедура.
 *    Пример: CallableStatement cs = connection.prepareCall(EXAMPLE_CALL);
 *            cs.setObject(1, "name");
 *            cs.setObject(2, ...);
 *            cs.setObject(3, ...);
 *            cs.executeUpdate();
 *  ••••••••••••••
 *  Метод disconnect - вынесен в отдельный метод. Т.к. работа с БД - это ресурсы, то требуется обязательно!
 *  закрывать соединение с БД по завершению работы. В методе disconnect производятся проверки на активность
 *  соединения, и если соединение активно происходить его разрыв с помощью оператора .close().
 *  Сам метод disconnect активируется в методе main(в конкретном, данном примере), в блоке finally.
 *  ••••••••••••••
 *  Метод executeUpdate - Вернет int - можно увидеть количество внесенных в БД изменений.
 *  Пример: var count = statement.executeUpdate(тело запроса);
 *          System.out.printf("Update %d rows\n", count); - вывод в консоль количества изменений.
 *  Оператор execute - Метод execute(boolean) - ВЫПОЛНИТЬ ЗАПРОС. Выполняет оператор SQL и указывает форму первого результата. Затем вы должны
 *  использовать методы getResultSet или getUpdateCount для получения результата и getMoreResults для перехода к
 *  любому последующему результату(ам).
 *  Примечание. Этот метод нельзя вызывать для PreparedStatement или CallableStatement.
 *  Параметры: sql — любой оператор SQL
 *  Возвращает: Значение true, если первым результатом является объект ResultSet; false, если это количество
 *              обновлений или нет результатов
 *  ••••••••••••••
 *  ResultSet - Интерфейс, с помощью которого осуществляется чтение данных. С помощью executeQuery возвращается
 *              список ResultSet.
 */

public class DatabaseExample {
    public static Connection connection;
    public static Statement statement;
    public static PreparedStatement preparedStatement;
    public static final String DB_CONNECTION_STRING = "jdbc:sqlite:db/example.db";
    private static final String INSERT_STATEMENT = "INSERT INTO students (name, score) VALUES (?, ?);";
    public static final String EXAMPLE_CALL = "{call do_something_prc(?, ?, ?)}";

    public static final String CREATE_REQUEST = "CREATE TABLE IF NOT EXISTS students" +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, score INTEGER);)";
    public static final String READ_DATA_TABLE = "select name, id, score from students order by score desc;"; /* Если
    // при создании запроса используется оператор * , то все данные возвращаются по порядку созданных столбцов в таблице,
    а если прописываются конкретные имена столбцов, то при выводе данных (в данном случае в консоль) указывается
    порядок считывания относительно запроса. Те id в запросе стоит на втором месте и по-этому, при выводе, в методе
    simpleRead() указана вторая позиция для считывания данных, цифра 2 в коде. Если считывание происходит по имени
    столбца, то позиция не указывается.*/
    public static final String DELETE_DATA_BY_CONDITION = "delete from students where score < 100";
    public static final String UPDATE_TABLE = "update students set name = 'looser' where score < 100;";
    public static final String DROP_REQUEST = "DROP TABLE IF EXISTS students;"; //Запрос на удаление таблицы.
    public static final String SIMPLE_INSERT_REQUEST =
                    "INSERT INTO students (name, score) VALUES ('Vitya Pupkin', 80)," +
                    " ('Kiril Ivanov', 93), ('Denis Petrov', 100);";

    public static void main(String[] args) {
        try {
            connect();
            statement = connection.createStatement(); // В statement = И нашего подключения connection
            // создаем запрос createStatement(). И далее уже к statement добавляем строки наших запросов.
            createTable(); // Через statement отправили запрос на создание таблицы с нужными параметрами.
//            simpleInsertExample();
//            simpleDropTable();
//            simpleUpdate();
//            simpleRead();
//            simpleDelete();

//            notReallyCorrectInsert("Pavel Rogov", 200); // Можно так, но лучше использовать preparedStatement !
//            notReallyCorrectInsert("Pavel Rogov', 200); drop table students;", 200); // sql injection !!!
                                                                                   // Этот код дропнет всю таблицу!!!!
//            var name = "Vadim Kotov";
//            var score = 210;
//            var evilName = "Pavel Rogov', 200); drop table students;"; // При реализации через preparedStatement кода
//            стирающего всю базу данных - удаление базы данных не произойдет! preparedStatement - нужно использовать!
//            preparedInsert(evilName, score);

//            massInsert();
//            massInsertTransaction();
//            massInsertWithBatch();



        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public static void connect() throws SQLException { // Метод подключения к БД.
//        try {             // В предыдущих версиях IDEA требовалось писать так... Сейчас это писать не обязательно.
//            Class.forName("org.sqlite.JDBC");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        connection = DriverManager.getConnection(DB_CONNECTION_STRING); // К connection присваиваем
        // результат = DriverManager(класс, который регистрирует JDBC драйвера и позволяет ими управлять).
        // .getConnection(статический метод класса DriverManager, который ожидает в себя некую строчку
        // для подключения).DB_CONNECTION_STRING - строчка для подключения, путь, что-то ти-по URL для
        // подключения и работы с БД.
    }

    private static void massInsertWithBatch() throws SQLException { // Метод пакетной отправки данных в БД.
        var start = System.currentTimeMillis();
        connection.setAutoCommit(false);
        preparedStatement = connection.prepareStatement(INSERT_STATEMENT);
        for (int i = 0; i < 5000; i++) {
            preparedStatement.setString(1, "Student #" + i);
            preparedStatement.setInt(2, i);
            preparedStatement.addBatch(); // Вначале все вставляемые данные набираются, добавляются в "паке", а потом..
        }
        preparedStatement.executeBatch(); // весь собранный пакет запросов на вставку данных, в виде int[] улетает в БД.
        connection.setAutoCommit(true);
        System.out.println(System.currentTimeMillis() - start); // 33 мили сек
    }

    private static void massInsertTransaction() throws SQLException { // Метод вставки с отключением коммитов каждой
                                                                      // вставляемой строки данных.
        var start = System.currentTimeMillis();
        connection.setAutoCommit(false); // отключил автокоммит перед циклом.
        for (int i = 0; i < 5000; i++) {
            preparedInsert("Student #" + i, i);
        }
//        connection.commit(); // Принудительный коммит, сохранение данных БД.
        connection.setAutoCommit(true);// включил автокоммит после вставки всех данных.
        System.out.println(System.currentTimeMillis() - start); // 112 мили сек
    }


    private static void massInsert() throws SQLException { // Метод вставки данных с автокоммитом после каждой
                                                           // вставленной строки.
        var start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            preparedInsert("Student #" + i, i);
        }
        System.out.println(System.currentTimeMillis() - start); // 5288 мили сек
    }

    private static void preparedInsert(String name, int score) throws SQLException { // Метод предкомпилированного
                                                                                     // зпроса на вставку данных.
        preparedStatement = connection.prepareStatement(INSERT_STATEMENT); // Создал параметризованный запрос на
                                                                               // вставку данных из созданного запроса
                                                                               // INSERT_STATEMENT.
        preparedStatement.setString(1, name); // На индекс 1 в строке запроса (вместо знака ? в строке
                                                               // INSERT_STATEMENT) заносим имя. Методом set
        preparedStatement.setInt(2, score); // На индекс 2 в строке запроса (вместо знака ? в строке
                                                             // INSERT_STATEMENT) заносим успеваемость. Методом set
        preparedStatement.executeUpdate(); // Отправил запрос на вставку.
    }

    /**
     * Ниже приведен метод вставки данных, которым не стоит пользоваться. Очень ненадежный способ. Можно удалить
     * все данные из таблицы и саму таблицу не желая этого!!! В методе main есть закомментированные, два запуска этого
     * кода с комментариями. МОЖНО ПОЛУЧИТЬ SQL ИНЬЕКЦИЮ !!!
     */
    public static void notReallyCorrectInsert(String name, int score) throws  SQLException{ // Не очень надежно !!!
                                                                              // Вставка данных в таблицу базы данных.
        var count = statement.executeUpdate("insert into students (name, score) values ('" + name + "', "
                + score + " );");
        System.out.printf("Update %d rows\n", count);
    }

    private static void simpleDelete() throws SQLException { // Метод удаления данных по условию.
        statement.executeUpdate(DELETE_DATA_BY_CONDITION);
    }

    private static void simpleRead() throws SQLException { // Метод чтения данных из таблицы с выводом в консоль.
        try (ResultSet resultScore = statement.executeQuery(READ_DATA_TABLE)) { // в блоке try с ресурсами создаем
            // переменную, в которую складывается результат запроса.
            while (resultScore.next()) { // Пока есть следующая запись (иначе закрытие)
                System.out.printf("Student:\n ID: %d\n Name: %s\n Score: %d\n\n", // выводим данные в консоль.
                   resultScore.getInt(2), // Методы get позволяют получить данные из полученной строки.
                   resultScore.getString("name"),// Методы get позволяют получить данные из полученной строки.
                   resultScore.getInt("score"));// Методы get позволяют получить данные из полученной строки.
            }
        }
    }

    private static void simpleUpdate() throws SQLException { // Метод внесения изменений в таблицу "по условию...",
                                                             // выводом количества изменений в консоль.
        var count = statement.executeUpdate(UPDATE_TABLE);
        System.out.printf("Updated %d rows\n", count);
    }

    private static void simpleDropTable() throws SQLException { // Метод на удаление всей, полностью таблицы БД.
        statement.execute(DROP_REQUEST);
    }

    private static void simpleInsertExample() throws SQLException {
        var count = statement.executeUpdate(SIMPLE_INSERT_REQUEST);
        System.out.printf("Inserted %d rows\n", count);
    }

    public static void createTable() throws SQLException { // Метод создания таблицы.
        statement.execute(CREATE_REQUEST);
    }

    private static void disconnect() { // Метод закрытия соединения БД.
        try {
            if (statement != null) statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (preparedStatement != null) preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}















