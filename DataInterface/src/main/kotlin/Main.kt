import java.sql.Connection
import java.sql.DriverManager
import java.util.*
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


fun registerUser(connection: Connection, username: String, password: String) {
    try {
        val insertQuery = "INSERT INTO users (username, password) VALUES (?, ?)"
        val preparedStatement: PreparedStatement = connection.prepareStatement(insertQuery)
        preparedStatement.setString(1, username)
        preparedStatement.setString(2, password)
        preparedStatement.executeUpdate()
        println("Пользователь зарегистрирован успешно.")
    } catch (e: Exception) {
        println("Ошибка при регистрации пользователя: ${e.message}")
    }
}

fun loginUser(connection: Connection, username: String, password: String): Boolean {
    try {
        val selectQuery = "SELECT * FROM users WHERE username = ? AND password = ?"
        val preparedStatement: PreparedStatement = connection.prepareStatement(selectQuery)
        preparedStatement.setString(1, username)
        preparedStatement.setString(2, password)
        val resultSet: ResultSet = preparedStatement.executeQuery()
        return resultSet.next()
    } catch (e: Exception) {
        println("Ошибка при авторизации пользователя: ${e.message}")
        return false
    }
}

fun getUserActions(connection: Connection, userId: Int): List<UserAction> {
    val userActions = mutableListOf<UserAction>()

    try {
        val selectQuery = "SELECT * FROM user_actions WHERE user_id = ?"
        val preparedStatement: PreparedStatement = connection.prepareStatement(selectQuery)
        preparedStatement.setInt(1, userId)

        val resultSet: ResultSet = preparedStatement.executeQuery()

        while (resultSet.next()) {
            val actionId = resultSet.getInt("id")
            val action = resultSet.getString("action")
            val stockName = resultSet.getString("stock_name")
            val amount = resultSet.getDouble("amount")
            val actionTime = resultSet.getTimestamp("action_time")

            val userAction = UserAction(actionId, userId, action, stockName, amount, actionTime)
            userActions.add(userAction)
        }
    } catch (e: Exception) {
        println("Ошибка при получении данных о действиях пользователя: ${e.message}")
    }

    return userActions
}

fun recordStockPurchase(connection: Connection, userId: Int, stockName: String, quantity: Int, totalPrice: Double) {
    try {
        val insertQuery = "INSERT INTO stock_purchases (user_id, stock_name, quantity, total_price, purchase_time) VALUES (?, ?, ?, ?, NOW())"
        val preparedStatement: PreparedStatement = connection.prepareStatement(insertQuery)
        preparedStatement.setInt(1, userId)
        preparedStatement.setString(2, stockName)
        preparedStatement.setInt(3, quantity)
        preparedStatement.setDouble(4, totalPrice)
        preparedStatement.executeUpdate()
        println("Покупка акций записана успешно.")
    } catch (e: Exception) {
        println("Ошибка при записи покупки акций: ${e.message}")
    }
}


data class UserAction(
    val id: Int,
    val userId: Int,
    val action: String,
    val stockName: String,
    val amount: Double,
    val actionTime: java.sql.Timestamp
)


fun main() {
    // Параметры подключения к 1 БД
    val url1 = "jdbc:postgresql://localhost:5432/db1"
    val user1 = "postgres"
    val password1 = "Qwerty12345"

    // Параметры подключения ко 2 БД
    val url2 = "jdbc:postgresql://localhost:5432/db2"
    val user2 = "postgres"
    val password2 = "Qwerty12345"

    // Параметры подключения к 3 БД
    val url3 = "jdbc:postgresql://localhost:5432/db3"
    val user3 = "postgres"
    val password3 = "Qwerty12345"




    try {
        // Подключение к первой БД
        val connection1: Connection = DriverManager.getConnection(url1, user1, password1)
        // Ваш код для работы с первой БД

        // Создание таблицы пользователей, если её нет
        val createTableQuery1 = """
            CREATE TABLE IF NOT EXISTS users (
                id SERIAL PRIMARY KEY,
                username VARCHAR(50) UNIQUE NOT NULL,
                password VARCHAR(50) NOT NULL
            )
        """.trimIndent()
        connection1.createStatement().executeUpdate(createTableQuery1)

        // Регистрация пользователя
        registerUser(connection1, "user123", "password123")

        // Авторизация пользователя
        val isLoggedIn = loginUser(connection1, "user123", "password123")
        if (isLoggedIn) {
            println("Пользователь авторизован успешно.")
        } else {
            println("Неверные имя пользователя или пароль.")
        }

        // Подключение ко второй БД
        val connection2: Connection = DriverManager.getConnection(url2, user2, password2)
        // Ваш код для работы со второй БД

        // Замените userId на конкретный идентификатор пользователя
        val userId = 1
        val userActions = getUserActions(connection2, userId)

        if (userActions.isNotEmpty()) {
            println("Действия пользователя с userId=$userId:")
            userActions.forEach {
                println("ID: ${it.id}, Action: ${it.action}, Stock: ${it.stockName}, Amount: ${it.amount}, Time: ${it.actionTime}")
            }
        } else {
            println("Нет данных о действиях пользователя с userId=$userId.")
        }

        // Подключение к третьей БД
        val connection3: Connection = DriverManager.getConnection(url3, user3, password3)
        // Ваш код для работы с третьей БД

        // Создание таблицы для записи покупок акций, если её нет
        val createTableQuery2 = """
            CREATE TABLE IF NOT EXISTS stock_purchases (
                id SERIAL PRIMARY KEY,
                user_id INT NOT NULL,
                stock_name VARCHAR(50) NOT NULL,
                quantity INT NOT NULL,
                total_price DOUBLE NOT NULL,
                purchase_time TIMESTAMP NOT NULL
            )
        """.trimIndent()
        connection3.createStatement().executeUpdate(createTableQuery2)

        // Пример записи покупки акций
        recordStockPurchase(connection3, 1, "AAPL", 10, 1500.0)
        // Ваш код для работы с программами

        // Закрытие соединений
        connection1.close()
        connection2.close()
        connection3.close()

    } catch (e: Exception) {
        e.printStackTrace()
    }
}
//гг