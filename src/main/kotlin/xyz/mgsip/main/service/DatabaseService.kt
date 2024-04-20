package xyz.mgsip.main.service

import org.springframework.stereotype.Service
import xyz.mgsip.main.model.Database
import java.sql.DriverManager

interface DatabaseService {
    abstract fun dbConnectStatus(database: Database): Boolean
}

@Service
class DatabaseServiceImpl:DatabaseService{

    override fun dbConnectStatus(database: Database): Boolean {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            val con = DriverManager.getConnection(database.url,database.userName,database.password);
            return true
        }
        catch(e: Exception){
            return false
        }
    }


}