package com.example.mygreendao;


import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class MyGreenDao {
    public static void main(String[] args) throws Exception {

        Schema schema = new Schema(1,"db");

        Entity usuarios = schema.addEntity("Usuarios");

        usuarios.addLongProperty("id_user").primaryKey().autoincrement();
        usuarios.addStringProperty("usu_nombre");
        usuarios.addStringProperty("usu_nick").unique();
        usuarios.addStringProperty("usu_fecha");
        usuarios.addStringProperty("usu_email");
        usuarios.addStringProperty("usu_password");

        new DaoGenerator().generateAll(schema, "./app/src/main/java");
    }

}
